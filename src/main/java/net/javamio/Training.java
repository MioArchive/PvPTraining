package net.javamio;

import lombok.Getter;
import net.javamio.command.TrainingCommand;
import net.javamio.listener.ZombieListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Training extends JavaPlugin {

    @Getter
    private static Training instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("training").setExecutor(new TrainingCommand());
        getCommand("training").setTabCompleter(new TrainingCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new ZombieListener(),this);
    }
}
