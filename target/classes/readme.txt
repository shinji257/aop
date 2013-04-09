microOP 1.5 (Altair edition)

This is a modified version of microOP designed for use on AltairMC.com.

The following code and functions were implemented.

Most recent microOP (1.4) relinked against 1.5.1 R0.1
A portion of PlgDIsableCmd (2.0) also relinked against 1.5.1 R0.1

Second plugin was modified to specifically only block op and deop with
no ability to customize.

Current code has the following functions

/opme: Ops the calling player.  This assumes they have the permission node
       microop.give enabled for them.

/deopme: Deops the calling player.  This command only does something if they
      are already opped.

Permissions:
microop.give: Allow usage of the /opme command

Additional info:
When /op and /deop command is used an error is displayed to the user and the
command is cancelled.  Console is still able to use the command without issue.

When any of the commands handled in the plugin are used an alert is sent to
all active ops that the command was used and by whom.  It is also printed in
the console for logging purposes.

Possible future enhancements:
* Add a node to the alerts so that non-ops can get them too
* Maybe convert /op and /deop to fake op and fake deop instead
* Setup trap in /opme so that people without the node get a nasty surprise
  instead. :o
