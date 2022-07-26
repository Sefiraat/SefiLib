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

public final class ItemStackGenerators {

    private ItemStackGenerators() {
        throw new IllegalStateException("Utility class");
    }

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

    @Nonnull
    @SafeVarargs
    public static ItemStack createEnchantedItemStack(@Nonnull Material material, @Nonnull Pair<Enchantment, Integer>... enchantments) {
        return createEnchantedItemStack(material, false, enchantments);
    }

    @Nonnull
    @SafeVarargs
    public static ItemStack createEnchantedItemStack(@Nonnull Material material, boolean hide, @Nonnull Pair<Enchantment, Integer>... enchantments) {
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
