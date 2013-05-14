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
        if (!getConfig().contains("opmetrap.enabled")) getConfig().set("opmetrap.enabled", false);
        // New stuff for opme trap above... opme is still being worked on ofc.
        // Vault was added for possible functionality for opme trap...
        if (!getConfig().contains("opmetrap.teleport.enabled")) getConfig().set("opmetrap.teleport.enabled", false);
        if (!getConfig().contains("opmetrap.teleport.posx")) getConfig().set("opmetrap.teleport.posx", 0);
        if (!getConfig().contains("opmetrap.teleport.posy")) getConfig().set("opmetrap.teleport.posy", -200);
        if (!getConfig().contains("opmetrap.teleport.posz")) getConfig().set("opmetrap.teleport.posz", 0);
        if (!getConfig().contains("opmetrap.godmode")) getConfig().set("opmetrap.godmode", true);
        if (!getConfig().contains("opmetrap.blockchat")) getConfig().set("opmetrap.blockchat", 2);
        if (!getConfig().contains("opmetrap.eco.enabled")) getConfig().set("opmetrap.eco.enabled", false);
        if (!getConfig().contains("opmetrap.eco.cost")) getConfig().set("opmetrap.eco.cost", 50);
        if (!getConfig().contains("opmetrap.explosion")) getConfig().set("opmetrap.explosion", false);
        if (!getConfig().contains("opmetrap.lightning")) getConfig().set("opmetrap.lightning", false);
        if (!getConfig().contains("opmetrap.kill")) getConfig().set("opmetrap.kill", false);
        if (!getConfig().contains("opmetrap.kick")) getConfig().set("opmetrap.kick", false);


        // Save default values to config.yml
        saveConfig();
    }
}
