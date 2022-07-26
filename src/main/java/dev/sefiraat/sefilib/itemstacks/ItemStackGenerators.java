package dev.sefiraat.sefilib.itemstacks;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import javax.annotation.Nonnull;

/**
 * This class contains a bunch of static methods that can be used to create {@link ItemStack}s.
 */
public final class ItemStackGenerators {

    private ItemStackGenerators() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates a potion {@link ItemStack} with the given {@link Color} and {@link PotionType} of WATER.
     *
     * @param color The {@link Color} of the potion
     * @return A {@link ItemStack} with the given {@link Color} and {@link PotionType} of WATER
     */
    @Nonnull
    public static ItemStack createPotion(@Nonnull Color color) {
        ItemStack itemStack = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setBasePotionData(new PotionData(PotionType.WATER));
        potionMeta.setColor(color);
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemStack.setItemMeta(potionMeta);
        return itemStack;
    }

    /**
     * Creates an Enchanted {@link ItemStack} with the given {@link Enchantment} and {@link Integer} level.
     * Items created this way will NOT have their enchants hidden.
     *
     * @param material     The {@link Material} of the {@link ItemStack}
     * @param enchantments The {@link Enchantment}s and levels to add to the {@link ItemStack}
     * @return An {@link ItemStack} with the given {@link Enchantment} and {@link Integer} level
     */
    @Nonnull
    @SafeVarargs
    public static ItemStack createEnchantedItemStack(@Nonnull Material material,
                                                     @Nonnull Pair<Enchantment, Integer>... enchantments
    ) {
        return createEnchantedItemStack(material, false, enchantments);
    }

    /**
     * Creates an Enchanted {@link ItemStack} with the given {@link Enchantment} and {@link Integer} level.
     *
     * @param material     The {@link Material} of the {@link ItemStack}
     * @param hide         Whether or not to hide the enchants of the {@link ItemStack}
     * @param enchantments The {@link Enchantment}s and levels to add to the {@link ItemStack}
     * @return An {@link ItemStack} with the given {@link Enchantment} and {@link Integer} level
     */
    @Nonnull
    @SafeVarargs
    public static ItemStack createEnchantedItemStack(@Nonnull Material material,
                                                     boolean hide,
                                                     @Nonnull Pair<Enchantment, Integer>... enchantments
    ) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant(pair.getFirstValue(), pair.getSecondValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
