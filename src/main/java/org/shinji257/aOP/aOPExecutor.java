package org.shinji257.aOP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class aOPExecutor implements CommandExecutor {
    private aOP plugin;

    public aOPExecutor(aOP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("aop")) {
            if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("aop.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "Configuration Reloaded.");
                } else
                    sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
            } else {
                if (sender.hasPermission("aop.use")) {
                   sender.sendMessage(plugin.getDescription().getName() + " " + plugin.getDescription().getVersion());
                   if (sender.hasPermission("aop.reload"))
                       sender.sendMessage("Available parameters: reload");
               } else
                   sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
            }
            return true;
        }
        return false;
    }
}
