package org.shinji257.aop;

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
        String[] split = event.getMessage().split(" ");
        if (split.length < 1) return;

        String cmd = split[0].trim().substring(1).toLowerCase();

        // Let's build our array. :)
        Disabled = new ArrayList<String>();

        // Now to populate it. -- normally this would of been done by file but...
        Disabled.add("deop");
        Disabled.add("op");

        // Getting the display string for below...
        final Player player = event.getPlayer();
        String P = player.getName();
        if ( ! (P.equals(ChatColor.stripColor(player.getDisplayName()))) && plugin.getConfig().getBoolean("shownick",true)) {
            P = P + " ( " + player.getDisplayName() + ChatColor.GRAY + " )";
        }

        if (Collections.binarySearch(Disabled, cmd) >= 0) {
            event.setCancelled(true);
            if ( ! plugin.getConfig().getBoolean("silent",false)) {
                event.getPlayer().sendMessage("[aOP] " + ChatColor.RED + "Access Denied.");
            }
            for(Player p : Bukkit.getOnlinePlayers()){
                if((p.isOp() || p.hasPermission("aop.notify")) & plugin.getConfig().getBoolean("notify",true)){
                    p.sendMessage(ChatColor.GRAY + P + " has used /" + cmd);
                }
            }
        aOP.log.info(player.getName() + " has used /" + cmd + " (denied)");
        }
    }
}