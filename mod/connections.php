<?php

require_once('include/Contact.php');
require_once('include/socgraph.php');
require_once('include/contact_selectors.php');
require_once('include/group.php');
require_once('include/contact_widgets.php');
require_once('include/zot.php');
require_once('include/widgets.php');

function connections_init(&$a) {

	if(! local_channel())
		return;

	$channel = $a->get_channel();
	if($channel)
		head_set_icon($channel['xchan_photo_s']);

}

function connections_post(&$a) {
	
	if(! local_channel())
		return;

	$contact_id = intval(argv(1));
	if(! $contact_id)
		return;

	$orig_record = q("SELECT * FROM abook WHERE abook_id = %d AND abook_channel = %d LIMIT 1",
		intval($contact_id),
		intval(local_channel())
	);

	if(! $orig_record) {
		notice( t('Could not access contact record.') . EOL);
		goaway(z_root() . '/connections');
		return; // NOTREACHED
	}

	call_hooks('contact_edit_post', $_POST);

	$profile_id = $_POST['profile_assign'];
	if($profile_id) {
		$r = q("SELECT profile_guid FROM profile WHERE profile_guid = '%s' AND `uid` = %d LIMIT 1",
			dbesc($profile_id),
			intval(local_channel())
		);
		if(! count($r)) {
			notice( t('Could not locate selected profile.') . EOL);
			return;
		}
	}

	$hidden = intval($_POST['hidden']);

	$priority = intval($_POST['poll']);
	if($priority > 5 || $priority < 0)
		$priority = 0;

	$closeness = intval($_POST['closeness']);
	if($closeness < 0)
		$closeness = 99;

	$abook_my_perms = 0;

	foreach($_POST as $k => $v) {
		if(strpos($k,'perms_') === 0) {
			$abook_my_perms += $v;
		}
	}			

	$new_friend = false;

	if(($_REQUEST['pending']) && intval($orig_record[0]['abook_pending'])) {
		$new_friend = true;
	}

	$r = q("UPDATE abook SET abook_profile = '%s', abook_my_perms = %d , abook_closeness = %d, abook_pending = %d
		where abook_id = %d AND abook_channel = %d",
		dbesc($profile_id),
		intval($abook_my_perms),
		intval($closeness),
		intval(1 - intval($new_friend)),
		intval($contact_id),
		intval(local_channel())
	);

	if($r)
		info( t('Connection updated.') . EOL);
	else
		notice( t('Failed to update connection record.') . EOL);

	if((x($a->data,'abook')) && $a->data['abook']['abook_my_perms'] != $abook_my_perms 
		&& (! intval($a->data['abook']['abook_self']))) {
		proc_run('php', 'include/notifier.php', 'permission_update', $contact_id);
	}

	if($new_friend) {
		$channel = $a->get_channel();		
		$default_group = $channel['channel_default_group'];
		if($default_group) {
			require_once('include/group.php');
			$g = group_rec_byhash(local_channel(),$default_group);
			if($g)
				group_add_member(local_channel(),'',$a->data['abook_xchan'],$g['id']);
		}



		// Check if settings permit ("post new friend activity" is allowed, and 
		// friends in general or this friend in particular aren't hidden) 
		// and send out a new friend activity
		// TODO

		// pull in a bit of content if there is any to pull in
		proc_run('php','include/onepoll.php',$contact_id);

	}

	// Refresh the structure in memory with the new data

	$r = q("SELECT abook.*, xchan.* 
		FROM abook left join xchan on abook_xchan = xchan_hash
		WHERE abook_channel = %d and abook_id = %d LIMIT 1",
		intval(local_channel()),
		intval($contact_id)
	);
	if($r) {
		$a->data['abook'] = $r[0];
	}

	if($new_friend) {
		$arr = array('channel_id' => local_channel(), 'abook' => $a->data['abook']);
		call_hooks('accept_follow', $arr);
	}

	connections_clone($a);

	return;

}

function connections_clone(&$a) {

		if(! array_key_exists('abook',$a->data))
			return;
		$clone = $a->data['abook'];

		unset($clone['abook_id']);
		unset($clone['abook_account']);
		unset($clone['abook_channel']);

		build_sync_packet(0 /* use the current local_channel */, array('abook' => array($clone)));
}


function connections_content(&$a) {

	$sort_type = 0;
	$o = '';


	if(! local_channel()) {
		notice( t('Permission denied.') . EOL);
		return login();
	}

	$blocked     = false;
	$hidden      = false;
	$ignored     = false;
	$archived    = false;
	$unblocked   = false;
	$pending     = false;
	$unconnected = false;
	$all         = false;

	if(! $_REQUEST['aj'])
		$_SESSION['return_url'] = $a->query_string;

	$search_flags = '';
	$head = '';

	if(argc() == 2) {
		switch(argv(1)) {
			case 'blocked':
				$search_flags = " and abook_blocked = 1 ";
				$head = t('Blocked');
				$blocked = true;
				break;
			case 'ignored':
				$search_flags = " and abook_ignored = 1 ";
				$head = t('Ignored');
				$ignored = true;
				break;
			case 'hidden':
				$search_flags = " and abook_hidden = 1 ";
				$head = t('Hidden');
				$hidden = true;
				break;
			case 'archived':
				$search_flags = " and abook_archived = 1 ";
				$head = t('Archived');
				$archived = true;
				break;
			case 'pending':
				$search_flags = " and abook_pending = 1 ";
				$head = t('New');
				$pending = true;
				nav_set_selected('intros');
				break;
			case 'ifpending':
				$r = q("SELECT COUNT(abook.abook_id) AS total FROM abook left join xchan on abook.abook_xchan = xchan.xchan_hash where abook_channel = %d and abook_pending = 1 and abook_self = 0 and abook_ignored = 0 and xchan_deleted = 0 and xchan_orphan = 0 ",
					intval(local_channel())
				);
				if($r && $r[0]['total']) {
					$search_flags = " and abook_pending = 1 ";
					$head = t('New');
					$pending = true;
					nav_set_selected('intros');
					$a->argv[1] = 'pending';
				}
				else {
					$head = t('All');
					$search_flags = '';
					$all = true;
					$a->argc = 1;
					unset($a->argv[1]);
				}
				nav_set_selected('intros');
				break;
//			case 'unconnected':
//				$search_flags = " and abook_unconnected = 1 ";
//				$head = t('Unconnected');
//				$unconnected = true;
//				break;

			case 'all':
				$head = t('All');
			default:
				$search_flags = '';
				$all = true;
				break;

		}

		$sql_extra = $search_flags;
		if(argv(1) === 'pending')
			$sql_extra .= " and abook_ignored = 0 ";

	}
	else {
		$sql_extra = " and abook_blocked = 0 ";
		$unblocked = true;
	}

	$search = ((x($_REQUEST,'search')) ? notags(trim($_REQUEST['search'])) : '');

	$tabs = array(
		array(
			'label' => t('Suggestions'),
			'url'   => z_root() . '/suggest', 
			'sel'   => '',
			'title' => t('Suggest new connections'),
		),
		array(
			'label' => t('New Connections'),
			'url'   => z_root() . '/connections/pending', 
			'sel'   => ($pending) ? 'active' : '',
			'title' => t('Show pending (new) connections'),
		),
		array(
			'label' => t('All Connections'),
			'url'   => z_root() . '/connections/all', 
			'sel'   => ($all) ? 'active' : '',
			'title' => t('Show all connections'),
		),
		array(
			'label' => t('Unblocked'),
			'url'   => z_root() . '/connections',
			'sel'   => (($unblocked) && (! $search) && (! $nets)) ? 'active' : '',
			'title' => t('Only show unblocked connections'),
		),

		array(
			'label' => t('Blocked'),
			'url'   => z_root() . '/connections/blocked',
			'sel'   => ($blocked) ? 'active' : '',
			'title' => t('Only show blocked connections'),
		),

		array(
			'label' => t('Ignored'),
			'url'   => z_root() . '/connections/ignored',
			'sel'   => ($ignored) ? 'active' : '',
			'title' => t('Only show ignored connections'),
		),

		array(
			'label' => t('Archived'),
			'url'   => z_root() . '/connections/archived',
			'sel'   => ($archived) ? 'active' : '',
			'title' => t('Only show archived connections'),
		),

		array(
			'label' => t('Hidden'),
			'url'   => z_root() . '/connections/hidden',
			'sel'   => ($hidden) ? 'active' : '',
			'title' => t('Only show hidden connections'),
		),

//		array(
//			'label' => t('Unconnected'),
//			'url'   => z_root() . '/connections/unconnected',
//			'sel'   => ($unconnected) ? 'active' : '',
//			'title' => t('Only show one-way connections'),
//		),


	);

	$tab_tpl = get_markup_template('common_tabs.tpl');
	$t = replace_macros($tab_tpl, array('$tabs'=>$tabs));

	$searching = false;
	if($search) {
		$search_hdr = $search;
		$search_txt = dbesc(protect_sprintf(preg_quote($search)));
		$searching = true;
	}
	$sql_extra .= (($searching) ? protect_sprintf(" AND xchan_name like '%$search_txt%' ") : "");

	if($_REQUEST['gid']) {
		$sql_extra .= " and xchan_hash in ( select xchan from group_member where gid = " . intval($_REQUEST['gid']) . " and uid = " . intval(local_channel()) . " ) ";
	}
 	
	$r = q("SELECT COUNT(abook.abook_id) AS total FROM abook left join xchan on abook.abook_xchan = xchan.xchan_hash 
		where abook_channel = %d and abook_self = 0 and xchan_deleted = 0 and xchan_orphan = 0 $sql_extra $sql_extra2 ",
		intval(local_channel())
	);
	if($r) {
		$a->set_pager_total($r[0]['total']);
		$total = $r[0]['total'];
	}

	$r = q("SELECT abook.*, xchan.* FROM abook left join xchan on abook.abook_xchan = xchan.xchan_hash
		WHERE abook_channel = %d and abook_self = 0 and xchan_deleted = 0 and xchan_orphan = 0 $sql_extra $sql_extra2 ORDER BY xchan_name LIMIT %d OFFSET %d ",
		intval(local_channel()),
		intval($a->pager['itemspage']),
		intval($a->pager['start'])
	);

	$contacts = array();

	if(count($r)) {

		foreach($r as $rr) {
			if($rr['xchan_url']) {
				$contacts[] = array(
					'img_hover' => sprintf( t('%1$s [%2$s]'),$rr['xchan_name'],$rr['xchan_url']),
					'edit_hover' => t('Edit connection'),
					'id' => $rr['abook_id'],
					'alt_text' => $alt_text,
					'dir_icon' => $dir_icon,
					'thumb' => $rr['xchan_photo_m'], 
					'name' => $rr['xchan_name'],
					'username' => $rr['xchan_name'],
					'classes' => (intval($rr['abook_archived']) ? 'archived' : ''),
					'link' => z_root() . '/connedit/' . $rr['abook_id'],
					'edit' => t('Edit'),
					'url' => chanlink_url($rr['xchan_url']),
					'network' => network_to_name($rr['network']),
				);
			}
		}
	}
	

	if($_REQUEST['aj']) {
		if($contacts) {
			$o = replace_macros(get_markup_template('contactsajax.tpl'),array(
				'$contacts' => $contacts,
				'$edit' => t('Edit'),
			));
		}
		else {
			$o = '<div id="content-complete"></div>';
		}
		echo $o;
		killme();
	}
	else {
		$o .= "<script> var page_query = '" . $_GET['q'] . "'; var extra_args = '" . extra_query_args() . "' ; </script>";
		$o .= replace_macros(get_markup_template('connections.tpl'),array(
			'$header' => t('Connections') . (($head) ? ' - ' . $head : ''),
			'$tabs' => $t,
			'$total' => $total,
			'$search' => $search_hdr,
			'$desc' => t('Search your connections'),
			'$finding' => (($searching) ? t('Finding: ') . "'" . $search . "'" : ""),
			'$submit' => t('Find'),
			'$edit' => t('Edit'),
			'$cmd' => $a->cmd,
			'$contacts' => $contacts,
			'$paginate' => paginate($a),

		)); 
	}

	if(! $contacts)
		$o .= '<div id="content-complete"></div>';

	return $o;
}
