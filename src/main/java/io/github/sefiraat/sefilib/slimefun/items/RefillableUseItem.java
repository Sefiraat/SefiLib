package io.github.sefiraat.sefilib.slimefun.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

/**
 * A {@link LimitedUseItem} with a method that allows you to refill uses
 */
public abstract class RefillableUseItem extends LimitedUseItem {

    protected RefillableUseItem(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
    }

    /**
     * Adds a single use back to the item
     * @param itemStack The {@link ItemStack} to refill
     */
    protected void refillItem(ItemStack itemStack) {
        refillItem(itemStack, 1);
    }

    /**
     * Adds the requested number of uses back to the item
     * @param itemStack The {@link ItemStack} to refill
     * @param refillAmount The number of uses to add
     */
    @ParametersAreNonnullByDefault
    protected void refillItem(ItemStack itemStack, int refillAmount) {
        final ItemMeta meta = itemStack.getItemMeta();
        final NamespacedKey key = getStorageKey();
        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        int usesLeft = pdc.getOrDefault(key, PersistentDataType.INTEGER, getMaxUseCount());

        usesLeft = Math.min(usesLeft + refillAmount, getMaxUseCount());
        pdc.set(key, PersistentDataType.INTEGER, usesLeft);
        updateLore(itemStack, meta, usesLeft);
    }

    /**
     * Stolen from the Super class as it's private
     * Refreshes the item lore when adding a use back
     * @param itemStack The {@link ItemStack} that has been refilled.
     * @param itemMeta The {@link ItemMeta} of the ItemStack.
     * @param usesLeft The remaining uses after updating.
     */
    @ParametersAreNonnullByDefault
    protected void updateLore(ItemStack itemStack, ItemMeta itemMeta, int usesLeft) {
        List<String> lore = itemMeta.getLore();

        String newLine = ChatColors.color(LoreBuilder.usesLeft(usesLeft));
        if (lore != null && !lore.isEmpty()) {
            // find the correct line
            for (int i = 0; i < lore.size(); i++) {
                if (PatternUtils.USES_LEFT_LORE.matcher(lore.get(i)).matches()) {
                    lore.set(i, newLine);
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);
                    return;
                }
            }
        } else {
            itemMeta.setLore(Collections.singletonList(newLine));
            itemStack.setItemMeta(itemMeta);
        }
    }

}
