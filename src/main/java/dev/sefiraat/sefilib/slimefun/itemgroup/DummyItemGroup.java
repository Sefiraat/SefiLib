package dev.sefiraat.sefilib.slimefun.itemgroup;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ItemGroup} that is not shown on the main menu.
 */
public class DummyItemGroup extends ItemGroup {

    /**
     * Creates a new {@link DummyItemGroup}.
     *
     * @param key  The {@link NamespacedKey} of this item group.
     * @param item The {@link ItemStack} that is used to create this item group.
     */
    @ParametersAreNonnullByDefault
    public DummyItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean isHidden(Player p) {
        return true;
    }

}
