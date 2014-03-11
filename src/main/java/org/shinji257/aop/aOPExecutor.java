package org.shinji257.aop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
// http://stackoverflow.com/questions/1518213/read-java-jvm-startup-parameters-eg-xmx
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

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
            } else if (args.length >= 1 && args[0].equalsIgnoreCase("debug")) {
                if (sender.isOp()) {
                    // http://stackoverflow.com/questions/1518213/read-java-jvm-startup-parameters-eg-xmx
                    RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
                    List<String> aList = bean.getInputArguments();
                    for (int i = 0; i < aList.size(); i++) {
                        sender.sendMessage(aList.get(i));
                    }
                }
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
