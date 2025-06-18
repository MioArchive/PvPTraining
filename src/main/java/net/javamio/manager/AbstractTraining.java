package net.javamio.manager;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractTraining<T> {

    @Getter
    protected Map<UUID, T> entities = new HashMap<>();

    protected boolean checkWorld(Player player) {
        return true;
    }

    public abstract void spawnBot(Player player);

    public abstract void despawnBot(Player player, Boolean silent);

    public abstract boolean isBotSpawned(Player player);

    public abstract void removeAllBots();
}
