package dev.sefiraat.sefilib.slimefun.recipes;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ConfigurableRecipeItem {

    public static final ConfigurableRecipeItem EMPTY = new ConfigurableRecipeItem();

    private final ItemType itemType;
    private Material material = null;
    private String slimefunId = null;
    private ItemStack itemStack = null;

    public ConfigurableRecipeItem(@Nonnull Material material) {
        this.itemType = ItemType.MATERIAL;
        this.material = material;
    }

    public ConfigurableRecipeItem(@Nonnull String slimefunId) {
        this.itemType = ItemType.SLIMEFUN;
        this.slimefunId = slimefunId;
    }

    public ConfigurableRecipeItem(@Nonnull ItemStack itemStack) {
        this.itemType = ItemType.ITEMSTACK;
        this.itemStack = itemStack;
    }

    private ConfigurableRecipeItem() {
        this.itemType = ItemType.EMPTY;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Material getMaterial() {
        return material;
    }

    public String getSlimefunId() {
        return slimefunId;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static ConfigurableRecipeItem fromConfig(@Nonnull ConfigurationSection section) throws IllegalArgumentException, IllegalStateException {
        final ItemType type = ItemType.valueOf(section.getString("type", ItemType.SLIMEFUN.name()));
        final String value = section.getString("value");
        if (value == null) {
            throw new IllegalArgumentException("No value was provided");
        }
        switch (type) {
            case SLIMEFUN -> {
                return new ConfigurableRecipeItem(value);
            }
            case MATERIAL -> {
                final Material material = Material.valueOf(value);
                return new ConfigurableRecipeItem(material);
            }
            case ITEMSTACK -> {
                final ItemStack itemStack = section.getItemStack("value");
                if (itemStack == null) {
                    throw new IllegalArgumentException("ItemStack is missing or invalid");
                }
                return new ConfigurableRecipeItem(itemStack);
            }
            case EMPTY -> {
                return ConfigurableRecipeItem.EMPTY;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public enum ItemType {
        SLIMEFUN,
        MATERIAL,
        ITEMSTACK,
        EMPTY
    }
}
