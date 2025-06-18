package net.javamio;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PvPTraining extends JavaPlugin {

    @Getter
    public static PvPTraining instance;

    @Override
    public void onEnable() {
        instance = this;
    }
}
