package io.github.sefiraat.sefilib.persistence;

import io.github.sefiraat.sefilib.persistence.types.BukkitObjectDataType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public final class DataTypes {

    private DataTypes() {
        throw new IllegalStateException("Utility class");
    }

    private static final PersistentDataType<byte[], ItemStack> ITEMSTACK = new BukkitObjectDataType<>(ItemStack.class);
}
