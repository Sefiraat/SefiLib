package dev.sefiraat.sefilib.slimefun.items;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

/**
 * This class is used to create a new {@link SlimefunItem} that is more suited to chainable method calls and
 * in-line registration.
 * On its own, without extending, it just allows registration of the {@link SlimefunItem} without declaring first.
 *
 * @param <T> The {@link SlimefunItem} that is created by this class.
 */
public class SefiSlimefunItem<T extends SefiSlimefunItem<T>> extends SlimefunItem {

    /**
     * Creates a new {@link SefiSlimefunItem}.
     *
     * @param itemGroup  The {@link ItemGroup} this item belongs to.
     * @param item       The {@link SlimefunItemStack} that is used to create this item.
     * @param recipeType The {@link RecipeType} of this item.
     * @param recipe     The recipe of this item.
     */
    public SefiSlimefunItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    /**
     * Creates a new {@link SefiSlimefunItem}.
     *
     * @param itemGroup    The {@link ItemGroup} this item belongs to.
     * @param item         The {@link SlimefunItemStack} that is used to create this item.
     * @param recipeType   The {@link RecipeType} of this item.
     * @param recipe       The recipe of this item.
     * @param recipeOutput The recipe output of this item.
     */
    public SefiSlimefunItem(ItemGroup itemGroup,
                            SlimefunItemStack item,
                            RecipeType recipeType,
                            ItemStack[] recipe,
                            @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    /**
     * Returns this object
     *
     * @return this object
     */
    protected T getThis() {
        return (T) this;
    }

    /**
     * Registers this item with {@link Slimefun}.
     *
     * @param addon The {@link SlimefunAddon} that is registering this item.
     * @return this object
     */
    public T registerItem(SlimefunAddon addon) {
        this.register(addon);
        return getThis();
    }
}
