package io.github.sefiraat.sefilib.entities;

import io.github.sefiraat.sefilib.misc.Keys;
import io.github.sefiraat.sefilib.string.StringUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public final class DisplayArmorStand {

    public static final String KEY_STRING = "a_dpy";

    private DisplayArmorStand() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static void setDisplay(JavaPlugin plugin, ArmorStand armorStand) {
        final NamespacedKey key = Keys.newKey(plugin, KEY_STRING);

        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setRemoveWhenFarAway(false);
        armorStand.setCollidable(false);
        armorStand.setInvulnerable(true);
        armorStand.setMarker(true);
        armorStand.setSilent(true);
        armorStand.setCustomName(StringUtils.getRandomEggName());

        PersistentDataAPI.setBoolean(armorStand, key, true);
    }

    @ParametersAreNonnullByDefault
    public void setDisplayItem(ArmorStand armorStand, Material material) {
        setDisplayItem(armorStand, new ItemStack(material));
    }

    @ParametersAreNonnullByDefault
    public void setDisplayItem(ArmorStand armorStand, ItemStack itemStack) {
        clearDisplayItem(armorStand);
        armorStand.getEquipment().setHelmet(itemStack);
    }

    public void clearDisplayItem(@Nonnull ArmorStand armorStand) {
        armorStand.getEquipment().setHelmet(null);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDisplayStand(JavaPlugin plugin, ArmorStand armorStand) {
        final NamespacedKey key = Keys.newKey(plugin, KEY_STRING);
        return PersistentDataAPI.getBoolean(armorStand, key);
    }
}
