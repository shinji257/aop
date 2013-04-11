package org.shinji257.aop;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

// Needed for chat handling
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

public class aOP extends JavaPlugin {
    protected final static Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onDisable() {
        aOP.log.info(this.getDescription().getName() + " has been disabled.");
    }

    // For handling chat events -- block op and deop commands
    public class CommandPreprocessListener implements Listener {
        private final aOP plugin;

        public CommandPreprocessListener(aOP instance) {
            plugin = instance;
        }

        public List<String> Disabled;

        @EventHandler(priority=EventPriority.MONITOR)
        public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        	// OP only version
        	//Player player = event.getPlayer();
        	//if (player != null && player.isOp()) return;

        	String[] split = event.getMessage().split(" ");
        	if (split.length < 1) return;

        	String cmd = split[0].trim().substring(1).toLowerCase();

                // Let's build our array. :)
                Disabled = new ArrayList<String>();

                // Now to populate it. -- normally this would of been done by file but...
                Disabled.add("deop");
                Disabled.add("op");

        	if (Collections.binarySearch(Disabled, cmd) >= 0) {
                    final Player player = event.getPlayer();
        	    event.setCancelled(true);
		    event.getPlayer().sendMessage("[aOP] " + ChatColor.RED + "Access Denied.");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.isOp() || p.hasPermission("aop.notify")){
                            p.sendMessage(ChatColor.GRAY + player.getName() + " has used /" + cmd);
                        }
                    }
		    aOP.log.info(player.getName() + " has used /" + cmd + " (denied)");
        	}
        }
    }

    @Override
    public void onEnable() {
        this.getCommand("opme").setExecutor(new OpmeExecutor());
        this.getCommand("deopme").setExecutor(new DeopmeExecutor());
        aOP.log.info(this.getDescription().getName() + " has been enabled.");
        // register events
        getServer().getPluginManager().registerEvents(new CommandPreprocessListener(this), this);
        // load configuration
//        getConfig().options().copyDefaults(true);
//        saveConfig();
    }
}
