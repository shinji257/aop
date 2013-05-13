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
        // update config... add missing options. :p
        updateConfig();
        getCommand("opme").setExecutor(new OpmeExecutor(this));
        getCommand("deopme").setExecutor(new DeopmeExecutor(this));
        getCommand("aop").setExecutor(new aOPExecutor(this));
        // register events
        getServer().getPluginManager().registerEvents(new CommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        aOP.log.info(getDescription().getName() + " has been enabled.");
    }
    
    public void updateConfig() {
        getConfig().options().copyDefaults(true);

        // This is the default configuration
        if (!getConfig().contains("opblock")) getConfig().set("opblock", true);
        if (!getConfig().contains("shownick")) getConfig().set("shownick", true);
        if (!getConfig().contains("opdrop")) getConfig().set("opdrop", true);
        if (!getConfig().contains("notify")) getConfig().set("notify", true);
        if (!getConfig().contains("silent")) getConfig().set("silent", false);
        if (!getConfig().contains("opmetrap")) getConfig().set("opmetrap", false);

        // Save default values to config.yml in datadirectory
        saveConfig();
    }
}
