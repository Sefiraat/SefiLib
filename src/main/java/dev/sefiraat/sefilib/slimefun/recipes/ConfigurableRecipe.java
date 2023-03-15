package dev.sefiraat.sefilib.slimefun.recipes;

import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ConfigurableRecipe {

    @Nonnull
    private final ConfigurableRecipeItem[] defaultRecipe;
    @Nonnull
    private final String recipeName;

    @ParametersAreNonnullByDefault
    public ConfigurableRecipe(String recipeName, ConfigurableRecipeItem[] recipeItems) {
        this.defaultRecipe = recipeItems;
        this.recipeName = recipeName;
    }

    @Nonnull
    public ConfigurableRecipeItem[] getDefaultRecipe() {
        return defaultRecipe;
    }

    @Nonnull
    public String getRecipeName() {
        return recipeName;
    }

    public static ConfigurableRecipe fromConfig(@Nonnull ConfigurationSection section) {
        final ConfigurableRecipeItem[] items = new ConfigurableRecipeItem[9];
        final String recipeName = section.getString("name");
        if (recipeName == null) {
            throw new IllegalArgumentException("Recipe name not provided");
        }
        for (int i = 0; i < 9; i++) {
            ConfigurationSection itemSection = section.getConfigurationSection("item." + i);
            if (itemSection != null) {
                items[i] = ConfigurableRecipeItem.fromConfig(itemSection);
            } else {
                items[i] = ConfigurableRecipeItem.EMPTY;
            }
        }
        return new ConfigurableRecipe(recipeName, items);
    }
}
