package dev.sefiraat.sefilib.slimefun.recipes;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ConfigurableRecipeManager {

    @Nonnull
    private final SlimefunAddon addon;
    @Nonnull
    private final File recipeDirectory;
    @Nonnull
    private final Set<ConfigurableRecipe> recipes = new HashSet<>();

    public ConfigurableRecipeManager(@Nonnull SlimefunAddon addon) {
        this.addon = addon;
        recipeDirectory = new File(this.addon.getJavaPlugin().getDataFolder() + "\\recipes");
        recipeDirectory.mkdir();
    }

    @ParametersAreNonnullByDefault
    public void newRecipe(String recipeName, ConfigurableRecipeItem[] defaultItemStacks) {
        final ConfigurableRecipe recipe = new ConfigurableRecipe(recipeName, defaultItemStacks);
        recipes.add(recipe);
    }

    @Nonnull
    public File getRecipeDirectory() {
        return recipeDirectory;
    }

    @Nonnull
    public SlimefunAddon getAddon() {
        return addon;
    }

    @Nonnull
    public Set<ConfigurableRecipe> getRecipes() {
        return recipes;
    }
}
