package io.github.sefiraat.sefilib.slimefun.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class TestBlock extends OwnedBlock<TestBlock> {

    protected TestBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected TestBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }
}
