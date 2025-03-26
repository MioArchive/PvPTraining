package net.javamio.command;

import net.javamio.Training;
import net.javamio.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingCommand implements CommandExecutor, TabCompleter {

    private final List<String> subCommands;

    public TrainingCommand() {
        this.subCommands = List.of("spawn","reload","despawn");
        Training.getInstance().getCommand("train").setExecutor(this);
        Training.getInstance().getCommand("train").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (!player.hasPermission("training.use")) {
            player.sendMessage(ConfigUtil.getPrefix() + ConfigUtil.getMessage("messages.no-permission"));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ConfigUtil.getPrefix() + ConfigUtil.getMessage("messages.no-args"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "spawn" -> {
                Training.getInstance().getZombieModule().spawnZombie(player);
                return true;
            }
            case "despawn" -> {
                Training.getInstance().getZombieModule().despawnZombie(player);
                return true;
            }
            case "reload" -> {
                if (player.hasPermission("training.reload")) {
                    Training.getInstance().reloadConfig();
                    player.sendMessage(ConfigUtil.getPrefix() + ConfigUtil.getMessage("messages.reload"));
                } else {
                    player.sendMessage(ConfigUtil.getMessage(ConfigUtil.getPrefix() + "messages.no-permission"));
                }
                return true;
            }
            default -> {
                player.sendMessage(ConfigUtil.getMessage(ConfigUtil.getPrefix() + "messages.no-args"));
                return false;
            }
        }
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return new ArrayList<>();
        if (!(player.hasPermission("training.use"))) return new ArrayList<>();

        if (args.length == 1) {
            return subCommands.stream()
                    .filter(sc -> sc.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
