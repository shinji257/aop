package org.shinji257.aop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class PlayerListener implements Listener {
    public aOP plugin;

    public PlayerListener(aOP instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if ((player.isOp() & plugin.getConfig().getBoolean("opdrop",true)) && ( ! player.hasPermission("aop.bypass.opdrop"))) {
            player.setOp(false);
            aOP.log.info("Dropped op status for " + player.getName());
        }
    }
}