package org.shinji257.aop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class OpmeExecutor implements CommandExecutor {
    private aOP plugin;

    public OpmeExecutor(aOP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("opme")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                String P = player.getName();
                if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("shownick",true)) {
                    P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
                }
                if (player.hasPermission("aop.use") || player.hasPermission("bukkit.command.op.give")) {
                    player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "You are now op!");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(plugin.getConfig().getBoolean("notify",true) && p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + P + " has used /opme");
                        }
                    }
                    aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /opme (allowed)");
                    player.setOp(true);
                } else {
                    if ( ! plugin.getConfig().getBoolean("silent",false)) {
                        sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                    }
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(plugin.getConfig().getBoolean("notify",true) && p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + P + " has used /opme");
                        }
                    }
                    aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /opme (denied)");
                }
            } else {
                sender.sendMessage("[" + plugin.getDescription().getName() + "] Only Players can execute this command");
            }
            return true;
        }
        return false;
    }
}
