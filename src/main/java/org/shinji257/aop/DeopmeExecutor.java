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
                if (player.isOp()) {
                    player.setOp(false);
                    player.sendMessage("[microOP] " + ChatColor.YELLOW + "You are no longer op!");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp()){
                            p.sendMessage(ChatColor.GRAY + player.getName() + " has used /deopme");
                        }
                    }
		    MicroOP.log.info(player.getName() + " has used /deopme");
                }
            } else {
                sender.sendMessage("[microOP] Only Players can execute this command");
            }
            return true;
        }
        return false;
    }
}
