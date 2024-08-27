package net.javamio;

import net.javamio.command.TrainingCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Training extends JavaPlugin {

    private static Training instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("training").setExecutor(new TrainingCommand());
        getCommand("training").setTabCompleter(new TrainingCommand());
    }

    public static Training getInstance() {
        return instance;
    }
}
