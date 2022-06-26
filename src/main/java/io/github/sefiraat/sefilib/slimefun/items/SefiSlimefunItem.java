package io.github.sefiraat.sefilib.slimefun.items;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class SefiSlimefunItem<T extends SefiSlimefunItem<T>> extends SlimefunItem {

    public SefiSlimefunItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public SefiSlimefunItem(ItemGroup itemGroup,
                            SlimefunItemStack item,
                            RecipeType recipeType,
                            ItemStack[] recipe,
                            @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    protected T getThis() {
        return (T) this;
    }

    public T registerItem(SlimefunAddon addon) {
        this.register(addon);
        return getThis();
    }

}
