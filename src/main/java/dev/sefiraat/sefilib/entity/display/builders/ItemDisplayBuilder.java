package dev.sefiraat.sefilib.entity.display.builders;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemDisplayBuilder extends DisplayBuilder<ItemDisplayBuilder> {

    protected ItemStack itemStack;
    protected ItemDisplay.ItemDisplayTransform itemDisplayTransform;

    public ItemDisplayBuilder setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public ItemDisplayBuilder setItemDisplayTransform(@Nonnull ItemDisplay.ItemDisplayTransform itemDisplayTransform) {
        this.itemDisplayTransform = itemDisplayTransform;
        return this;
    }

    public ItemDisplay build() {
        if (this.location == null) {
            throw new IllegalStateException("You must provide a location for the Display Entity");
        }
        return generateDisplay(location);
    }

    public ItemDisplay build(@Nonnull DisplayGroup group) {
        if (this.groupParentOffset == null) {
            throw new IllegalStateException("You must provide a Group Parent Offset vector");
        }
        return generateDisplay(group.getLocation().clone().add(groupParentOffset));
    }

    private ItemDisplay generateDisplay(@Nonnull Location location) {
        final ItemDisplay display = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);

        display.setItemStack(itemStack);
        if (itemDisplayTransform != null) {
            display.setItemDisplayTransform(itemDisplayTransform);
        }
        applyDisplay(display);

        return display;
    }
}
