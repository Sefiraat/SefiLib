package dev.sefiraat.sefilib.itemstacks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class GeneralItemStackUtils {

    private GeneralItemStackUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void damage(@Nonnull ItemStack itemStack, int amount) {
        damage(itemStack, null, amount);
    }

    public static void damage(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta instanceof Damageable damageable) {
            int newDamage = Math.max((damageable.getDamage() + amount), 0);
            setItemDamage(itemStack, damageable, player, newDamage);
        }
    }

    public static void repair(@Nonnull ItemStack itemStack, int amount) {
        repair(itemStack, null, amount);
    }

    public static void repair(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta instanceof Damageable damageable) {
            int newDamage = Math.max((damageable.getDamage() - amount), 0);
            setItemDamage(itemStack, damageable, player, newDamage);
        }
    }

    public static void setItemDamage(@Nonnull ItemStack itemStack, int amount) {
        setItemDamage(itemStack, null, amount);
    }

    public static void setItemDamage(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        setItemDamage(itemStack, itemMeta, player, amount);
    }

    public static void setItemDamage(@Nonnull ItemStack itemStack, @Nonnull ItemMeta itemMeta, @Nullable Player player, int amount) {
        // Meta is not damageable - currently not possible but possible in the future?
        if (!(itemMeta instanceof Damageable damageable)) {
            return;
        }

        // Damage amount has not changed - no need to update the stack
        if (amount == damageable.getDamage()) {
            return;
        }

        // Check if stack will break
        if (amount >= itemStack.getType().getMaxDurability()) {
            // If a player has been sent, we need to fire an event
            if (player != null) {
                PlayerItemBreakEvent event = new PlayerItemBreakEvent(player, itemStack.clone());
                Bukkit.getPluginManager().callEvent(event);
            }
            itemStack.setAmount(0);
            return;
        }

        // Check for player and, if there, fire an PlayerItemDamageEvent
        if (player != null) {
            int damage = amount - damageable.getDamage();
            PlayerItemDamageEvent event = new PlayerItemDamageEvent(player, itemStack, damage);
            Bukkit.getPluginManager().callEvent(event);
            // Event cancelled - so lets not damage the item
            if (event.isCancelled()) {
                return;
            }
            amount = amount - (damage - event.getDamage());
        }

        // All clear, lets damage this sucker!
        damageable.setDamage(amount);
        itemStack.setItemMeta(damageable);
    }

    @Nonnull
    public static ItemStack getAsQuantity(@Nonnull ItemStack itemStack, int amount) {
        ItemStack clone = itemStack.clone();
        clone.setAmount(amount);
        return clone;
    }

}
