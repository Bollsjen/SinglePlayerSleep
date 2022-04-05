package dk.bollsjen.singleplayersleep;

import dk.bollsjen.singleplayersleep.events.SleepEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class SinglePlayerSleep extends JavaPlugin {

    private static SinglePlayerSleep plugin;

    public static SinglePlayerSleep Instance(){
        return plugin;
    }

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new SleepEvent(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SinglePlayerSleep] Plugin enabled");
        plugin = this;
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SinglePlayerSleep] Plugin disabled");
    }
}
