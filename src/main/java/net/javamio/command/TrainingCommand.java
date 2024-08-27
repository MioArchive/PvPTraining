package net.javamio.command;

import net.javamio.Training;
import net.javamio.module.ZombieModule;
import net.javamio.utility.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                Training.getInstance().reloadConfig();
                sender.sendMessage(MessageUtils.getMessage("messages.reload.success"));
                return true;
            }
            sender.sendMessage(MessageUtils.getMessage("messages.error.player-only"));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtils.getMessage("messages.error.no-args"));
            return false;
        }

        if (!player.hasPermission("training.use")) {
            player.sendMessage(MessageUtils.getMessage("messages.error.no-permission"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "spawn" -> {
                ZombieModule.spawnZombie(player);
                return true;
            }
            case "despawn" -> {
                ZombieModule.despawnZombie(player);
                return true;
            }
            case "reload" -> {
                if (player.hasPermission("training.reload")) {
                    Training.getInstance().reloadConfig();
                    player.sendMessage(MessageUtils.getMessage("messages.reload.success"));
                } else {
                    player.sendMessage(MessageUtils.getMessage("messages.error.no-permission"));
                }
                return true;
            }
            default -> {
                player.sendMessage(MessageUtils.getMessage("messages.error.invalid-command"));
                return false;
            }
        }
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player) || !player.hasPermission("training.use")) {
            return new ArrayList<>();
        }

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(Arrays.asList("spawn", "despawn", "reload"));
        }

        return completions.stream()
                .filter(s -> s.startsWith(args[args.length - 1].toLowerCase()))
                .sorted()
                .toList();
    }
}
