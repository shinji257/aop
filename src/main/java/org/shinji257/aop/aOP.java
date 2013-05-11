package org.shinji257.aop;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class aOP extends JavaPlugin {
    protected final static Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onDisable() {
        aOP.log.info(getDescription().getName() + " has been disabled.");
    }

    @Override
    public void onEnable() {
        // save default config if it doesn't exist...
        saveDefaultConfig();
        getCommand("opme").setExecutor(new OpmeExecutor(this));
        getCommand("deopme").setExecutor(new DeopmeExecutor(this));
        // register events
        if (getConfig().getBoolean("opblock",true)) {
            getServer().getPluginManager().registerEvents(new CommandPreprocessListener(this), this);
        }
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        aOP.log.info(getDescription().getName() + " has been enabled.");
    }
}
