package org.shinji257.aOP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class CommandPreprocessListener implements Listener {
    public aOP plugin;
    
    public CommandPreprocessListener(aOP instance) {
        plugin = instance;
    }

    public List<String> Disabled;

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        // Getting the player for below...
        final Player player = event.getPlayer();
        String P = player.getName();

//        String[] split = event.getMessage().split(" ");
//        if (split.length < 1) return;
//        String cmd = split[0].trim().substring(1).toLowerCase();

        String rawMessage = event.getMessage();
        String cmd = rawMessage.substring(1).split(" ", 1).toLowerCase();
        String[] args = rawMessage().substring(label.size()+1).split(" ").toLowerCase();
        
        // Let's build our array. :)
        Monitored = new ArrayList<String>();

        // Now to populate it. -- normally this would of been done by file but...
        Monitored.add("deop");
        Monitored.add("op");

        
        if (plugin.getConfig().getBoolean("op.block")) {
            if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("notify.shownick"))
                P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
            if (Collections.binarySearch(Monitored, cmd) >= 0) {
                event.setCancelled(true);
                if ( ! plugin.getConfig().getBoolean("silent"))
                    event.getPlayer().sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                for(Player p : Bukkit.getOnlinePlayers())
                    if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                        p.sendMessage(ChatColor.GRAY + P + " has used /" + cmd);
            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /" + cmd + " (denied)");
            }
        }
        if (plugin.getConfig().getBoolean("op.intercept")) {
            if (! plugin.getConfig().getConfig("op.block")) {
                if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("notify.shownick"))
                    P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
                if (Collections.binarySearch(Monitored, cmd) >= 0) {
                    if (player.hasPermission("aop.use") {
                        if (args.length >= 1) {
                            player.setOp(true);
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "You are now op!");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /op");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /op (allowed)");
                        } else {
                            player.sendMessage(ChatColor.RED + "Usage: /op <player>");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /op");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /op (allowed)");
                        }
                    } else {
                        if ( ! plugin.getConfig().getBoolean("silent"))
                            sender.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                        for(Player p : Bukkit.getOnlinePlayers())
                            if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                p.sendMessage(ChatColor.GRAY + P + " has used /op");
                        aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /op (denied)");
                    }
                }
            }
        }
    }
}
