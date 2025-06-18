package net.javamio;

import lombok.Getter;
import net.javamio.manager.TrainingManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PvPTraining extends JavaPlugin {

    @Getter
    public static PvPTraining instance;
    private TrainingManager trainingManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        trainingManager = new TrainingManager();
    }
}
