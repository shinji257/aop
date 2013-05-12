package org.shinji257.aop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class PlayerListener implements Listener {
    public aOP plugin;

    public PlayerListener(aOP instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogin (final PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        if ((player.isOp() & plugin.getConfig().getBoolean("opdrop",true)) && ( ! player.hasPermission("aop.bypass.opdrop"))) {
            player.setOp(false);
            aOP.log.info("[" + plugin.getDescription().getName() + "] Dropped op status for " + player.getName() + " at login");
        }
    }
}
