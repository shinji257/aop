aOP
===

aOP (Altair OP or alpha OP)

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

Commands:
- /opme (Ops the calling user -- Currently permissions only)
- /deopme (Deops the calling user -- Ops only)

Todo:
123456789012345678901234567890123456789012345678901234567890123456789012
* Add permission node so that non-ops can get alerts from the plugin.
  Currently alerts are only for ops.
* Rename it aOP (remove confusion vs microop/uop) (DONE)
* Add (optional) trap to opme command for those without permission --
  kill on first usage, kick on second
* Add config file -- opme trap disable/enable
* Add option to config file for possible silence on command denial (for
  op/deop block)
* Add option to toggle if op/deop should be blocked in game or not
* Possible rename of the current opme permission node (again) (DONE)
* Add/test node possibility to use bukkit op node as alternative...
  Basically allow either or.
