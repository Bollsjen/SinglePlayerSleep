package dk.bollsjen.singleplayersleep.events;

import dk.bollsjen.singleplayersleep.SinglePlayerSleep;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SleepEvent implements Listener {

    private static int isPlayerSleeping = 0;

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "[SinglePlayerSleep] Sov som var det single player");
    }

    @EventHandler
    public static void onBedEnter(PlayerBedEnterEvent event){
        isPlayerSleeping++;
        Player player = event.getPlayer();
        int online = Bukkit.getOnlinePlayers().size();
        if(online > 1){
            Makeday(player);
        }
    }

    @EventHandler
    public static void onBedLeave(PlayerBedLeaveEvent event){
        if(isPlayerSleeping -1 >= 0)
            isPlayerSleeping--;
        else
            isPlayerSleeping = 0;
    }

    public static void Makeday(Player player){
        new BukkitRunnable(){
            @Override
            public void run(){
                if(isPlayerSleeping > 0){
                    World world = Bukkit.getServer().getWorld("world");
                    world.setTime(1000);
                    world.setThundering(false);
                    world.setStorm(false);
                    isPlayerSleeping = 0;
                }
            }
        }.runTaskLater(SinglePlayerSleep.Instance(), 60);
    }
}
