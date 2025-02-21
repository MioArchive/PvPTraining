package net.javamio;

import lombok.Getter;
import net.javamio.command.TrainingCommand;
import net.javamio.listener.ZombieListener;
import net.javamio.module.ZombieModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Training extends JavaPlugin {

    @Getter
    private static Training instance;
    private ZombieModule zombieModule;

    @Override
    public void onLoad() {
        instance = this;
    }


    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.zombieModule = new ZombieModule();

        getCommand("training").setExecutor(new TrainingCommand());
        getCommand("training").setTabCompleter(new TrainingCommand());

        Bukkit.getServer().getPluginManager().registerEvents(new ZombieListener(),instance);
    }
}
