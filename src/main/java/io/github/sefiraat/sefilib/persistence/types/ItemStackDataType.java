package io.github.sefiraat.sefilib.persistence.types;

import org.bukkit.inventory.ItemStack;

public class ItemStackDataType extends BukkitObjectDataType<ItemStack> {
    public ItemStackDataType() {
        super(ItemStack.class);
    }
}
