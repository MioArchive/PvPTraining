package net.javamio.module;

import net.javamio.Training;
import net.javamio.util.ConfigUtil;
import net.javamio.util.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ZombieModule {

    public void spawnZombie(Player player) {
        Entity entity = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

        String displayName = ConfigUtil.getMessage("training-zombie.display-name").replace("%player%", player.getName());

        entity.setCustomName(displayName);
        entity.setCustomNameVisible(true);
        entity.setGlowing(true);
        Zombie zombie = (Zombie) entity;
        zombie.setTarget(player);

        ItemStack helmet = new ItemStackBuilder(Material.NETHERITE_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4,false).get();
        ItemStack chestplate = new ItemStackBuilder(Material.NETHERITE_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4,false).get();
        ItemStack leggings = new ItemStackBuilder(Material.NETHERITE_LEGGINGS).addEnchantment(Enchantment.PROTECTION_EXPLOSIONS,3,false).get();
        ItemStack boots = new ItemStackBuilder(Material.NETHERITE_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4,false).get();

        ItemStack totems = new ItemStackBuilder(Material.TOTEM_OF_UNDYING).setAmount(64).get();

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

        NamespacedKey key = new NamespacedKey(Training.getInstance(), "zombie_owner");
        zombie.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId().toString());

        player.sendMessage(ConfigUtil.getPrefix() + ConfigUtil.getMessage("messages.training-zombie.spawn.success").replace("%player%",player.getName()));
    }

    public void despawnZombie(Player player) {
        NamespacedKey key = new NamespacedKey(Training.getInstance(), "zombie_owner");
        boolean found = false;

        for (Entity entity : player.getWorld().getEntitiesByClass(Zombie.class)) {
            Zombie zombie = (Zombie) entity;
            String owner = zombie.getPersistentDataContainer().get(key, PersistentDataType.STRING);

            if (owner != null && owner.equals(player.getUniqueId().toString())) {
                zombie.remove();
                found = true;
                break;
            }
        }

        String messageKey = found ? "messages.training-zombie-despawn.success" : "messages.training-zombie-despawn.fail";
        player.sendMessage(ConfigUtil.getMessage(messageKey).replace("%player%", player.getName()));
    }
}
