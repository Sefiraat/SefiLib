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

/**
 * This class contains utility methods for working with {@link ItemStack}s.
 */
public final class GeneralItemStackUtils {

    private GeneralItemStackUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Damages the item by the provided amount.
     *
     * @param itemStack The {@link ItemStack} to damage.
     * @param amount    The amount of damage to deal.
     */
    public static void damage(@Nonnull ItemStack itemStack, int amount) {
        damage(itemStack, null, amount);
    }

    /**
     * Damages the item by the provided amount.
     *
     * @param itemStack The {@link ItemStack} to damage.
     * @param player    The {@link Player} who damaged the item.
     * @param amount    The amount of damage to deal.
     */
    public static void damage(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta instanceof Damageable damageable) {
            int newDamage = Math.max((damageable.getDamage() + amount), 0);
            setItemDamage(itemStack, damageable, player, newDamage);
        }
    }

    /**
     * Repairs the item by the provided amount.
     *
     * @param itemStack The {@link ItemStack} to repair.
     * @param amount    The amount of damage to repair.
     */
    public static void repair(@Nonnull ItemStack itemStack, int amount) {
        repair(itemStack, null, amount);
    }

    /**
     * Repairs the item by the provided amount.
     *
     * @param itemStack The {@link ItemStack} to repair.
     * @param player    The {@link Player} who repaired the item.
     * @param amount    The amount of damage to repair.
     */
    public static void repair(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta instanceof Damageable damageable) {
            int newDamage = Math.max((damageable.getDamage() - amount), 0);
            setItemDamage(itemStack, damageable, player, newDamage);
        }
    }

    /**
     * Sets the damage of the item.
     *
     * @param itemStack The {@link ItemStack} to set the damage of.
     * @param amount    The amount of damage to set.
     */
    public static void setItemDamage(@Nonnull ItemStack itemStack, int amount) {
        setItemDamage(itemStack, null, amount);
    }

    /**
     * Sets the damage of the item.
     *
     * @param itemStack The {@link ItemStack} to set the damage of.
     * @param player    The {@link Player} who set the damage of the item.
     * @param amount    The amount of damage to set.
     */
    public static void setItemDamage(@Nonnull ItemStack itemStack, @Nullable Player player, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        setItemDamage(itemStack, itemMeta, player, amount);
    }

    /**
     * Sets the damage of the item.
     *
     * @param itemStack The {@link ItemStack} to set the damage of.
     * @param itemMeta  The {@link ItemMeta} of the item.
     * @param player    The {@link Player} who set the damage of the item.
     * @param amount    The amount of damage to set.
     */
    public static void setItemDamage(@Nonnull ItemStack itemStack,
                                     @Nonnull ItemMeta itemMeta,
                                     @Nullable Player player,
                                     int amount
    ) {
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

    /**
     * Gets a clone of an {@link ItemStack} with the provided amount.
     *
     * @param itemStack The {@link ItemStack} to clone.
     * @param amount    The amount of the clone.
     * @return A clone of the {@link ItemStack} with the provided amount.
     */
    @Nonnull
    public static ItemStack getAsQuantity(@Nonnull ItemStack itemStack, int amount) {
        ItemStack clone = itemStack.clone();
        clone.setAmount(amount);
        return clone;
    }
}
