package org.shinji257.aop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class DeopmeExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        if (command.getName().equalsIgnoreCase("deopme")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                String P = player.getName();
                if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName())))) {
                    P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
                }
                if (player.isOp()) {
                    player.setOp(false);
                    player.sendMessage("[aOP] " + ChatColor.YELLOW + "You are no longer op!");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp() || p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + P + " has used /deopme");
                        }
                    }
		    aOP.log.info(player.getName() + " has used /deopme (allowed)");
                } else {
                    sender.sendMessage("[aOP] " + ChatColor.RED + "Access Denied.");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp() || p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + P + " has used /deopme");
                        }
                    }
       		    aOP.log.info(player.getName() + " has used /deopme (denied)");
                }
            } else {
                sender.sendMessage("[aOP] Only Players can execute this command");
            }
            return true;
        }
        return false;
    }
}
