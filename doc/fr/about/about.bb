[h3]Qu'est ce que $Projectname?[/h3]
$Projectname est un ensemble d'applications et de services [b]libre et open source[/b] tournant sur un serveur qu'on appelle hub qui peuvent se connecter à d'autre hubs formant ensemble un réseau décentralisé. $Projectname offre des commnunications sophistiquées, une identités nomade et un contôle trés fin sur vos données.

Il permet à n'importe qui de publier de façon public ou [b]privé[/b] du contenu au travers de "canaux". Vos données sont chiffrés et sécurisées. Vous pouvez vous authentifier indépendament de votre hub. Cette libération révolutionnaire d'identité numérique peut aussi être appelé identité nomade. Le tout est permis grâce au protocole zot, un nouveau frameword pour un contrôle d'acces décentralisé qui permet une gestion des permission trés fine.


[h3]Concretement .... qu'est ce que $Projectname?[/h3]
Concretemetn voici ce qu'on peut faire avec $Projectname : 
[ul]
[li]Réseau social[/li]
[li]Stockage de fichier (cloud)[/li]
[li]Gestion d'agendas et de contacts (CalDAV / CardDAV)[/li]
[li]Gestion de contenu[/li]
[li]wiki[/li]
[li]et bien plus...[/li][/ul]
Bien que d'autres applications proposent déjà ces choses, seul $Projectname vous permet gérer des permissions par groupes ou individuellement même pour des utilisateurs qui n'ont pas de compte. Habituellement si vous voulez partager avec des gens de façon privée, il faut que les personnes avec lesquelles vous échangez aient un compte sur le même serveur que vous autrement il n'y a aucune solution efficace pour votre serveur d'authentifier le visiteur. $Projectname résout ce problème avec un système avancé d'authentification à distance qui valide l'identité du visiteur en employant des technique qui incluent un clé public de chiffrement.
 
[h3]Pile de logiciels[/h3]
$Projectname utilise des logiciels relativement standard comme application de serveur. Les application sont écrit premièrement en PHP/MySQL et [url=https://github.com/redmatrix/hubzilla/blob/master/install/INSTALL.txt] demande un peu plus qu'un serveur web. Une base de donnée compatible MySQL et le langage PHP[/url]. C'est conçu pour est facilement installé par des personnes ayant des compétance de base en administration web. Le tout est facilement extensible grâce à des plugins et des thèmes tiers. 

[h3]Glossaire[/h3]
[dl terms="b"]
[*= hub] Une instance avec ce logiciel tournant sur un serveur web

[*= grille] C'est le réseau global qui échange des informations entre eux avec le protocol de communication Zot.

[*= canal] L'identifé fondamental de la grille. Un canal peut représenté une personnem un blog ou bien un forum pour en citer quelques uns. Les canaux peuvent faire des connexion avec d'autres canaux pour échanger des informations avec un niveau de permission trés pointu.

[*= clone] Les canaux peuvent avoir des clones sur d'autres hubs. Les communications partagés avec un canal sont synchronisés entre les clones. Cela permet à un canal d'envoyer et recevoir des messages depuis différents hubs. Ce clonage est ainsi une solution contre des pannes de serveurs. Sur des hubs autohébergés ou avec des ressources trés limités, le risque de panne est d'autant plus grand. Ainsi grâce aux clones, si un serveur tombe, l'autre clone pourra continuer à être utilisé. Le clonage permet aussi de déménager un canal d'un serveur à un autre tout en gardant vos données. C'est ce qu'on appelle l'identité nomade.

[*= Identité nomade] C'est la possibilité de s'authentifier et de migrer facilement une identité à travers différents hubs et sites web. L'identité nomage fournit une vraie propriété à une identité en ligne parce que l'identité d'un canal n'est pas lié à un hub en particulier. Un hub est plus un hébergeur de canaux. Avec hubzilla vous n'avez pas un compte sur un serveur comme vous en avez un sur la plupart des sites web. Vous avez une identité que vous pouvez prendre avec vous à travers le réseaux en utilisant le clonage.


[*= [url=[baseurl]/help/developer/zot_protocol]Zot[/url]] C'est un protocol pour implémenter une communication décentralisé et sécurisé  ainsi que des services. Il est différent des autres protocoles de communication parce qu'il se base sur une authentification et une identité décentralisé pour construire ses communications. L'authentification est similaire à OpenId conceptuellement mais est n'est pas lié à un site web ou une adresse web. Si c'est possible une authentification à distance est silensieuse. 
[/dl]

[h3]Fonctionnalités[/h3]
Cette page liste quelques unes des fonctionnalités que propose le noyaux de $Projectname. $Projectname est une plateforme extensible . Ainsi d'autres fonctionnalités peuvent être ajouté au travers des thémes et des extensions.

[h4]Gestuuib de l'affinité[/h4]En ajoutant un contact les membres ont la possibilité d'y affecter un niveau d'affinité. (Quel est le niveau d'amitié). Par exemple, on pourrait mettre à un blog le niveau connaissance, une personne non connu un faible niveau et un membre de votre famille un haut niveau.

Le régulateur d'affinité permet instantanément de filtrer un grand nombre de contenu.

[h4]Filtre de connexion[/h4]
Vous avez la possibilité de contrôler précisément ce qui apparait dans votre flux en utilisant l'option 'filtre de connexion'. Quand il est activé, l'éditeur de relation permet de sélectionner des critères qui doivent correspondre pour inclure ou exclure des publications. Une fois qu'une publication est permise, tous les commentaires y sont liés sont évidement aussi accepté.


[h4]Permissions[/h4]

En partageant un contenu, les membres ont la possibilité de restreindre la portée de leur publication en choisissant qui peut lire. En cliquant sur le cadenas, vous pouvez choisir qui peut voir vos publications. Ces permissions peuvent être affecté non seulement à des posts mais aussi à des phtos, des événements, des pages web, salons de discussion et les fichiers.


[h4]Single Sign-on[/h4]

Access Control Lists work for all channels in the grid due to our unique single sign-on technology. Most internal links provide an identity token which can be verified on other $Projectname sites and used to control access to private resources. You login once to your home hub. After that, authentication to all $Projectname resources is "magic".


[h4]WebDAV enabled File Storage[/h4]

Files may be uploaded to your personal storage area using your operating system utilities (drag and drop in most cases). You may protect these files with Access Control Lists to any combination of $Projectname members (including some third party network members) or make them public.

[h4]Photo Albums[/h4]

Store photos in albums. All your photos may be protected by Access Control Lists.

[h4]Events Calendar[/h4]

Create and manage events and tasks, which may also be protected with Access Control Lists. Events can be imported/exported to other software using the industry standard vcalendar/iCal format and shared in posts with others. Birthday events are automatically added from your friends and converted to your correct timezone so that you will know precisely when the birthday occurs - no matter where you are located in the world in relation to the birthday person. Events are normally created with attendance counters so your friends and connections can RSVP instantly. 

[h4]Chatrooms[/h4]

You may create any number of personal chatrooms and allow access via Access Control Lists. These are typically more secure than XMPP, IRC, and other Instant Messaging transports, though we also allow using these other services via plugins.       

[h4]Webpage Building[/h4]

$Projectname has many "Content Management" creation tools for building webpages, including layout editing, menus, blocks, widgets, and page/content regions. All of these may be access controlled so that the resulting pages are private to their intended audience. 

[h4]Apps[/h4]

Apps may be built and distributed by members. These are different from traditional "vendor lockin" apps because they are controlled completely by the author - who can provide access control on the destination app pages and charge accordingly for this access. Most apps in $Projectname are free and can be created easily by those with no programming skills. 

[h4]Layout[/h4]

Page layout is based on a description language called Comanche. $Projectname is itself written in Comanche layouts which you can change. This allows a level of customisation you won't typically find in so-called "multi-user environments".

[h4]Bookmarks[/h4]

Share and save/manage bookmarks from links provided in conversations.    
 
 
[h4]Private Message Encryption and Privacy Concerns[/h4]

Private mail is stored in an obscured format. While this is not bullet-proof it typically prevents casual snooping by the site administrator or ISP.  

Each $Projectname channel has it's own unique set of private and associated public RSA 4096-bit keys, generated when the channels is first created. This is used to protect private messages and posts in transit.

Additionally, messages may be created utilising "end-to-end encryption" which cannot be read by $Projectname operators or ISPs or anybody who does not know the passcode. 

Public messages are generally not encrypted in transit or in storage.  

Private messages may be retracted (unsent) although there is no guarantee the recipient hasn't read it yet.

Posts and messages may be created with an expiration date, at which time they will be deleted/removed on the recipient's site.  


[h4]Service Federation[/h4]

In addition to addon "cross-post connectors" to a variety of alternate networks, there is native support for importation of content from RSS/Atom feeds and using this to create special channels. Plugins are also available to communicate with others using the Diaspora and GNU-Social (OStatus) protocols. These networks do not support nomadic identity or cross-domain access control; however basic communications are supported to/from Diaspora, Friendica, GNU-Social, Mastodon and other providers which use these protocols.   

There is also experimental support for OpenID authentication which may be used in Access Control Lists. This is a work in progress. Your $Projectname hub may be used as an OpenID provider to authenticate you to external services which use this technology. 

Channels may have permissions to become "derivative channels" where two or more existing channels combine to create a new topical channel. 

[h4]Privacy Groups[/h4]

Our implementation of privacy groups is similar to Google "Circles" and Diaspora "Aspects". This allows you to filter your incoming stream by selected groups, and automatically set the outbound Access Control List to only those in that privacy group when you post. You may over-ride this at any time (prior to sending the post).  


[h4]Directory Services[/h4]

We provide easy access to a directory of members and provide decentralised tools capable of providing friend "suggestions". The directories are normal $Projectname sites which have chosen to accept the directory server role. This requires more resources than most typical sites so is not the default. Directories are synchronised and mirrored so that they all contain up-to-date information on the entire network (subject to normal propagation delays).  
 

[h4]TLS/SSL[/h4]

For $Projectname hubs that use TLS/SSL, client to server communications are encrypted via TLS/SSL.  Given recent disclosures in the media regarding widespread, global surveillance and encryption circumvention by the NSA and GCHQ, it is reasonable to assume that HTTPS-protected communications may be compromised in various ways. Private communications are consequently encrypted at a higher level before sending offsite.

[h4]Channel Settings[/h4]

When a channel is created, a role is chosen which applies a number of pre-configured security and privacy settings. These are chosen for best practives to maintain privacy at the requested levels.  

If you choose a "custom" privacy role, each channel allows fine-grained permissions to be set for various aspects of communication.  For example, under the &quot;Security and Privacy Settings&quot; heading, each aspect on the left side of the page, has six (6) possible viewing/access options, that can be selected by clicking on the dropdown menu. There are also a number of other privacy settings you may edit.  

The options are:

 - Nobody except yourself.
 - Only those you specifically allow.
 - Anybody in your address book.
 - Anybody on this website.
 - Anybody in this network.
 - Anybody authenticated.
 - Anybody on the Internet.


[h4]Public and Private Forums[/h4]

Forums are typically channels which may be open to participation from multiple authors. There are currently two mechanisms to post to forums: 1) "wall-to-wall" posts and 2) via forum @mention tags. Forums can be created by anybody and used for any purpose. The directory contains an option to search for public forums. Private forums can only be posted to and often only seen by members.


[h4]Account Cloning[/h4]

Accounts in $Projectname are referred to as [i]nomadic identities[/i], because a member's identity is not bound to the hub where the identity was originally created.  For example, when you create a Facebook or Gmail account, it is tied to those services.  They cannot function without Facebook.com or Gmail.com.  

By contrast, say you've created a $Projectname identity called [b]tina@$Projectnamehub.com[/b].  You can clone it to another $Projectname hub by choosing the same, or a different name: [b]liveForever@Some$ProjectnameHub.info[/b]

Both channels are now synchronized, which means all your contacts and preferences will be duplicated on your clone.  It doesn't matter whether you send a post from your original hub, or the new hub.  Posts will be mirrored on both accounts.

This is a rather revolutionary feature, if we consider some scenarios:

 - What happens if the hub where an identity is based suddenly goes offline?  Without cloning, a member will not be able to communicate until that hub comes back online (no doubt many of you have seen and cursed the Twitter "Fail Whale").  With cloning, you just log into your cloned account, and life goes on happily ever after. 

 - The administrator of your hub can no longer afford to pay for his free and public $Projectname hub. He announces that the hub will be shutting down in two weeks.  This gives you ample time to clone your identity(ies) and preserve your$Projectname relationships, friends and content.

 - What if your identity is subject to government censorship?  Your hub provider may be compelled to delete your account, along with any identities and associated data.  With cloning, $Projectname offers [b]censorship resistance[/b].  You can have hundreds of clones, if you wanted to, all named different, and existing on many different hubs, strewn around the internet.  

$Projectname offers interesting new possibilities for privacy. You can read more at the &lt;&lt;Private Communications Best Practices&gt;&gt; page.

Some caveats apply. For a full explanation of identity cloning, read the &lt;HOW TO CLONE MY IDENTITY&gt;.

[h4]Multiple Profiles[/h4]

Any number of profiles may be created containing different information and these may be made visible to certain of your connections/friends. A "default" profile can be seen by anybody and may contain limited information, with more information available to select groups or people. This means that the profile (and site content) your beer-drinking buddies see may be different than what your co-workers see, and also completely different from what is visible to the general public. 

[h4]Account Backup[/h4]

$Projectname offers a simple, one-click account backup, where you can download a complete backup of your profile(s). Backups can then be used to clone or restore a profile.

[h4]Account Deletion[/h4]
Accounts can be immediately deleted by clicking on a link. That's it.  All associated content is then deleted from the grid (this includes posts and any other content produced by the deleted profile). Depending on the number of connections you have, the process of deleting remote content could take some time but it is scheduled to happen as quickly as is practical.

[h4]Deletion of content[/h4]
Any content created in $Projectname remains under the control of the member (or channel) that originally created it.  At any time, a member can delete a message, or a range of messages.  The deletion process ensures that the content is deleted, regardless of whether it was posted on a channel's primary (home) hub, or on another hub, where the channel was remotely authenticated via Zot ($Projectname communication and authentication protocol).

[h4]Media[/h4]
Similar to any other modern blogging system, social network, or a micro-blogging service, $Projectname supports the uploading of files, embedding of videos, linking web pages.

[h4]Previewing/Editing[/h4] 
Posts and comments can be previewed prior to sending and edited after sending.

[h4]Voting/Consensus[/h4]
Posts can be turned into "consensus" items which allows readers to offer feedback, which is collated into "agree", "disagree", and "abstain" counters. This lets you gauge interest for ideas and create informal surveys. 

[h4]Extending $Projectname[/h4]

$Projectname can be extended in a number of ways, through site customisation, personal customisation, option setting, themes, and addons/plugins. 

[h4]API[/h4]

An API is available for use by third-party services. A plugin also provides a basic implementation of the Twitter API (for which hundreds of third-party tools exist). Access may be provided by login/password or OAuth, and client registration of OAuth applications is provided.
