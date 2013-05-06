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

Permissions
- aop.use (Allow usage of /opme command)
- aop.notify (Get notified when someone uses op,deop,etc)

Commands:
- /opme (Ops the calling user -- Currently permissions only)
- /deopme (Deops the calling user -- Ops only)

Todo:

* Add permission node so that non-ops can get alerts from the plugin.
  Currently alerts are only for ops. (DONE -- add aop.notify)
* Rename it aOP (remove confusion vs microop/uop) (DONE)
* Add (optional) trap to opme command for those without permission --
  kill on first usage, kick on second
* Add config file -- opme trap disable/enable
* Add option to config file for possible silence on command denial
* Add option to toggle if op/deop should be blocked in game or not
* Possible rename of the current opme permission node (again) (DONE)
* Add/test node possibility to use bukkit op node as alternative...
  Basically allow either or. (DONE)
* Add/test additional command blocks... Allowed via perm node only.
  Can be disabled via config (when implemented)
* Add option to toggle if nick is shown or not... (config)
* Remove herobrine.

Credits:

* alexanderpas @ BukkitDev for making microOP/µOP.  Without it this
  plugin would not be possible
* Plague @ Bukkit Forums for making PlgDisableCmd.  Without it this
  plugin would not be possible.

For both plugins they are already open source so that's why I'm
posting source for this plugin.
