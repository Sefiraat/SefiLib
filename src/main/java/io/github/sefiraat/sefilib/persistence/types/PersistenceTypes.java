package io.github.sefiraat.sefilib.persistence.types;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PersistenceTypes {

    private PersistenceTypes() {
        throw new IllegalStateException("Utility class");
    }

    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new BukkitObjectDataType<>(ItemStack.class);
    public static final PersistentDataType<byte[], ItemMeta> ITEM_META = new BukkitObjectDataType<>(ItemMeta.class);
    public static final PersistentDataType<byte[], OfflinePlayer> OFFLINE_PLAYER = new BukkitObjectDataType<>(
        OfflinePlayer.class
    );
}
