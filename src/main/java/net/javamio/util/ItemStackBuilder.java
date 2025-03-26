package net.javamio.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.inventoryaccess.component.AdventureComponentWrapper;
import xyz.xenondevs.invui.item.builder.AbstractItemBuilder;

public class ItemStackBuilder extends AbstractItemBuilder<ItemStackBuilder> {
    public ItemStackBuilder(@NotNull Material material) {
        super(material);
    }

    public ItemStackBuilder(@NotNull Material material, int amount) {
        super(material, amount);
    }

    public ItemStackBuilder(@NotNull ItemStack base) {
        super(base);
    }

    public @NotNull ItemStackBuilder setDisplayName(Component component) {
        return super.setDisplayName(new AdventureComponentWrapper(component));
    }

    public @NotNull ItemStackBuilder addLoreLines(Component... component) {
        AdventureComponentWrapper[] wrapper = new AdventureComponentWrapper[component.length];
        for (int i = 0; i < component.length; i++) {
            wrapper[i] = new AdventureComponentWrapper(component[i]);
        }
        return super.addLoreLines(wrapper);
    }
}
