package net.javamio.listener;

import net.javamio.Training;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ZombieListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Training.getInstance().getZombieModule().despawnZombie(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Training.getInstance().getZombieModule().despawnZombie(event.getPlayer());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            return;
        }
        Training.getInstance().getZombieModule().despawnZombie(event.getPlayer());
    }
}
