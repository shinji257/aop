package org.shinji257.aOP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class DeopmeExecutor implements CommandExecutor {
    private aOP plugin;

    public DeopmeExecutor(aOP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("deopme") && plugin.getConfig().getBoolean("op.me")) {
            if (sender instanceof Player) {
                final Player player = (Player) sender;
                String P = player.getName();
                if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("notify.shownick"))
                    P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
                if (player.isOp()) {
                    player.setOp(false);
                    player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "You are no longer op!");
                    for(Player p : Bukkit.getOnlinePlayers())
                        if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                            p.sendMessage(ChatColor.GRAY + P + " has used /deopme");
                    aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /deopme (allowed)");
                } else {
                    if ( ! plugin.getConfig().getBoolean("silent"))
                        sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                    for(Player p : Bukkit.getOnlinePlayers())
                        if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                            p.sendMessage(ChatColor.GRAY + P + " has used /deopme");
                    aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /deopme (denied)");
                }
            } else
                sender.sendMessage("[" + plugin.getDescription().getName() + "] Only Players can execute this command");
            return true;
        }
        return false;
    }
}
