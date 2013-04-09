package org.shinji257.aop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class OpmeExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("opme")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                if (player.hasPermission("aop.use")) {
                    player.sendMessage("[aOP] " + ChatColor.YELLOW + "You are now op!");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp() || p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + player.getName() + " has used /opme");
                        }
                    }
		    aOP.log.info(player.getName() + " has used /opme (allowed)");
                    player.setOp(true);
                } else {
                    sender.sendMessage("[aOP] " + ChatColor.RED + "Access Denied.");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp() || p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + player.getName() + " has used /opme");
                        }
                    }
       		    aOP.log.info(player.getName() + " has used /opme (denied)");
                }
            } else {
                sender.sendMessage("[aOP] Only Players can execute this command");
            }
            return true;
        }
        return false;
    }
}
