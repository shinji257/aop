aOP
===

aOP (alpha OP)

This plugin was developed out of a bit of need for a custom plugin that
could handle specific admin related functions without leaving ourselves
open to op attack attempts.  This plugin combines a couple of older
plugins (they are open source) and adds some logging capabilities
currently.

There are plans for more functions eventually so hopefully I will be
able to implement them later on.

Functions:
* Add /opme and /deopme
* Uses permissions to control /opme usage
* Catch and block /op and /deop usage even for ops
* Allow /op and /deop to be used in console
* Alerts ops of usage for /op, /deop, /opme, and /deopme
* Alerts console as well

(added in 2.0)
Configurable ability to ...
* Silence deny message sent to player
* Enable/disable notifications in chat
* Enable/disable op/deop command block
* Toggle if nick is shown in chat notification messages
* Automatically drop op on quit

Permissions
- aop.use (Allow usage of /opme command)
- aop.notify (Get notified when someone uses op,deop,etc)

(added in 2.0)
- aop.bypass.opdrop (Bypass automatic drop of op rights on quit)

Commands:
- /opme (Ops the calling user -- Currently permissions only)
- /deopme (Deops the calling user -- Ops only)

Todo:

* Add (optional) trap to opme command for those without permission --  kill on first usage, kick on second (config)
* Add/test additional command blocks... Allowed via perm node only.  Can be disabled via config (when implemented)
* Add new CommandExecutor handler for /aop command... Reload + version options (Target 2.1)
* Split off Listener classes to their own files. (Target 2.1)
* Remove herobrine. (Target ∞)

Credits:

* alexanderpas @ BukkitDev for making microOP/µOP.  Without it this plugin would not be possible
* Plague @ Bukkit Forums for making PlgDisableCmd.  Without it this plugin would not be possible.

For both plugins they are already open source so that's why I'm posting source for this plugin.
