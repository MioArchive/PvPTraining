package net.javamio.module;

import net.javamio.Training;
import net.javamio.utility.ConfigUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import static net.javamio.utility.ItemBuilder.createArmorItem;

public class ZombieModule {

    public static void spawnZombie(Player player) {
        Entity entity = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

        String displayName = ConfigUtil.getMessage("zombie-totem.display-name").replace("%player%", player.getName());

        entity.setCustomName(displayName);
        entity.setCustomNameVisible(true);
        entity.setGlowing(true);
        Zombie zombie = (Zombie) entity;
        zombie.setTarget(player);

        ItemStack helmet = createArmorItem(Material.NETHERITE_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        ItemStack chestplate = createArmorItem(Material.NETHERITE_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        ItemStack leggings = createArmorItem(Material.NETHERITE_LEGGINGS, Enchantment.PROTECTION_EXPLOSIONS, 3);
        ItemStack boots = createArmorItem(Material.NETHERITE_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack totems = new ItemStack(Material.TOTEM_OF_UNDYING, 64);

        zombie.getEquipment().setHelmet(helmet);
        zombie.getEquipment().setChestplate(chestplate);
        zombie.getEquipment().setLeggings(leggings);
        zombie.getEquipment().setBoots(boots);

        zombie.getEquipment().setItemInOffHand(totems);

        zombie.getEquipment().setHelmetDropChance(0.0F);
        zombie.getEquipment().setChestplateDropChance(0.0F);
        zombie.getEquipment().setLeggingsDropChance(0.0F);
        zombie.getEquipment().setBootsDropChance(0.0F);

        zombie.getEquipment().setItemInOffHandDropChance(0.0F);

        NamespacedKey key = new NamespacedKey(Training.getInstance(), "totem_zombie_owner");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId().toString());

        ConfigUtil.getMessage("messages.totem-zombie-spawn.success").replace("%player%", player.getName());
    }

    public static void despawnZombie(Player player) {
        for (Entity entity : player.getLocation().getWorld().getEntities()) {
            if (entity.getType() == EntityType.ZOMBIE) {
                Zombie zombie = (Zombie) entity;
                NamespacedKey key = new NamespacedKey(Training.getPlugin(Training.class), "totem_zombie_owner");
                if (zombie.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    String owner = zombie.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    if (owner.equals(player.getUniqueId().toString())) {
                        zombie.remove();
                        ConfigUtil.getMessage("messages.totem-zombie-despawn.success").replace("%player%", player.getName());
                        return;
                    }
                }
            }
        }
        ConfigUtil.getMessage("messages.totem-zombie-despawn.fail").replace("%player%", player.getName());
    }
}
