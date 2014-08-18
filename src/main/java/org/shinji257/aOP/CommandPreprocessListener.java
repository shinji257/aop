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

    public List<String> Monitored;
    String target = " ";

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        // Let's break this up now.
        String[] split = event.getMessage().split(" ");
        if (split.length < 1) return;
        String cmd = split[0].trim().substring(1).toLowerCase();

        // Getting the player for below...
        final Player player = event.getPlayer();
        String P = player.getName();

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
                    player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                for(Player p : Bukkit.getOnlinePlayers())
                    if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                        p.sendMessage(ChatColor.GRAY + P + " has used /" + cmd);
            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /" + cmd + " (denied)");
            }
        }
        if (plugin.getConfig().getBoolean("op.intercept") && !player.hasPermission("aop.bypass.intercept")) {
            if (! plugin.getConfig().getBoolean("op.block")) {
                if (event.getMessage().split(" ").length >= 2) {
                    target = split[1].trim().substring(0).toLowerCase();
                } else {
                    target = " ";
                }
                if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("notify.shownick"))
                    P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
                if (Collections.binarySearch(Monitored, cmd) >= 0) {
                    event.setCancelled(true);
                    if (cmd.equals("op") && (player.hasPermission("aop.use") || player.hasPermission("bukkit.command.op.give"))) {
                        if (target.equals(P)) {
                            player.setOp(true);
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "You are now op!");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /op");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /op (allowed)");
                        } else if (!target.equals(P) && !target.equals(" ")) {
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "You are not allowed to op that player.");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has attempted to use /op on player " + target);
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has attempted to use /op on player " + target + " (denied)");
                        } else {
                            player.sendMessage(ChatColor.RED + "Usage: /op <player>");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /op");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /op (allowed)");
                        }
                    } else if (cmd.equals("deop") && (player.isOp())) {
                        if (target.equals(P)) {
                            player.setOp(false);
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.YELLOW + "You are no longer op!");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /deop");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /deop (allowed)");
                        } else if (!target.equals(P) && !target.equals(" ")) {
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "You are not allowed to deop that player.");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has attempted to use /deop on player " + target);
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has attempted to use /deop on player " + target + " (denied)");
                        } else {
                            player.sendMessage(ChatColor.RED + "Usage: /deop <player>");
                            for(Player p : Bukkit.getOnlinePlayers())
                                if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                    p.sendMessage(ChatColor.GRAY + P + " has used /deop");
                            aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /deop (allowed)");
                        }
                    } else {
                        if ( ! plugin.getConfig().getBoolean("silent"))
                            player.sendMessage("[" + plugin.getDescription().getName() + "] " + ChatColor.RED + "Access Denied.");
                        for(Player p : Bukkit.getOnlinePlayers())
                            if(plugin.getConfig().getBoolean("notify.enabled") && p.hasPermission("aop.notify") && (player.getName() != p.getName()))
                                p.sendMessage(ChatColor.GRAY + P + " has used /" + cmd);
                        aOP.log.info("[" + plugin.getDescription().getName() + "] " + player.getName() + " has used /" + cmd + " (denied)");
                    }

                }
            }
        }
    }
}
