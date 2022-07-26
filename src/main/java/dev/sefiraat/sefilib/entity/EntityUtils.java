package dev.sefiraat.sefilib.entity;

import dev.sefiraat.sefilib.protections.Protections;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public final class EntityUtils {

    private EntityUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Heal the entity by the provided amount
     *
     * @param livingEntity The {@link LivingEntity} to heal
     * @param healAmount   The amount to heal by
     */
    @ParametersAreNonnullByDefault
    public static void healEntity(LivingEntity livingEntity, double healAmount) {
        AttributeInstance attribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            livingEntity.setHealth(Math.min(attribute.getValue(), livingEntity.getHealth() + healAmount));
        }
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID player, double damage) {
        return damageEntity(livingEntity, player, damage, null, 0);
    }

    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID playerUUID, double damage, @Nullable Location knockbackOrigin, double knockbackForce) {
        Interaction interaction = livingEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
        if (Protections.hasPermission(playerUUID, livingEntity.getLocation(), interaction)) {
            Player player = Bukkit.getPlayer(playerUUID);
            livingEntity.damage(damage, player);
            if (knockbackOrigin != null && knockbackForce > 0) {
                pushEntity(playerUUID, knockbackOrigin, livingEntity, knockbackForce);
            }
            return true;
        }
        return false;
    }

    @ParametersAreNonnullByDefault
    public static boolean pullEntity(UUID caster, Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
            .subtract(pullToLocation.toVector())
            .normalize()
            .multiply(-force);
        return pushEntity(caster, vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID caster, Location pushFromLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
            .subtract(pushFromLocation.toVector())
            .normalize()
            .multiply(force);
        return pushEntity(caster, vector, pushed);
    }

    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID caster, Vector vector, Entity pushed) {
        Interaction interaction = pushed instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.INTERACT_ENTITY;
        if (Protections.hasPermission(caster, pushed.getLocation(), interaction)) {
            pushed.setVelocity(vector);
            return true;
        }
        return false;
    }

    public static double getFacing(@Nonnull Entity testEntity, @Nonnull Entity compareEntity) {
        Vector pd = testEntity.getLocation().getDirection();
        Vector ed = compareEntity.getLocation().getDirection();
        double x = (pd.getX() * ed.getZ()) - (pd.getZ() * ed.getX());
        double z = (pd.getX() * ed.getX()) + (pd.getZ() * ed.getZ());
        double a = Math.atan2(x, z);
        return (a * 180) / Math.PI;
    }

    public static boolean isFacingAway(@Nonnull Entity testEntity, @Nonnull Entity compareEntity) {
        return isFacingAway(testEntity, compareEntity, null);
    }

    public static boolean isFacingAway(@Nonnull Entity testEntity, @Nonnull Entity compareEntity, @Nullable Integer tolerance) {
        double d = getFacing(testEntity, compareEntity);
        int val = tolerance == null ? 30 : tolerance;
        return d <= val && d >= -val;
    }


}
