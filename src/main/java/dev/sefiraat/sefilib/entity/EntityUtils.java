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

/**
 * This class contains basic utility methods for entities.
 */
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

    /**
     * Damages the entity by the provided amount
     *
     * @param livingEntity The {@link LivingEntity} to damage
     * @param player       The {@link Player} who damaged the entity
     * @param damage       The amount of damage to deal
     * @return Whether the entity was damaged or not
     */
    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity, UUID player, double damage) {
        return damageEntity(livingEntity, player, damage, null, 0);
    }

    /**
     * Damages the entity by the provided amount
     *
     * @param livingEntity    The {@link LivingEntity} to damage
     * @param playerUUID      The {@link UUID} of the {@link Player} who damaged the entity
     * @param damage          The amount of damage to deal
     * @param knockbackOrigin The {@link Location} of the knockback origin
     * @param knockbackForce  The amount of knockback to apply
     * @return Whether the entity was damaged or not
     */
    @ParametersAreNonnullByDefault
    public static boolean damageEntity(LivingEntity livingEntity,
                                       UUID playerUUID,
                                       double damage,
                                       @Nullable Location knockbackOrigin,
                                       double knockbackForce
    ) {
        Interaction interaction = livingEntity instanceof Player ?
                                  Interaction.ATTACK_PLAYER :
                                  Interaction.ATTACK_ENTITY;
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

    /**
     * Pulls the entity by the provided amount
     *
     * @param originatingPlayer The {@link UUID} of the player who caused the pull
     * @param pullToLocation    The {@link Location} to pull the entity to
     * @param pushed            The {@link Entity} to pull
     * @param force             The amount of force to apply
     * @return Whether the entity was pulled or not
     */
    @ParametersAreNonnullByDefault
    public static boolean pullEntity(UUID originatingPlayer, Location pullToLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
            .subtract(pullToLocation.toVector())
            .normalize()
            .multiply(-force);
        return pushEntity(originatingPlayer, vector, pushed);
    }

    /**
     * Pushes the entity by the provided amount
     *
     * @param originatingPlayer The {@link UUID} of the player who caused the push
     * @param pushFromLocation  The {@link Location} to push the entity from
     * @param pushed            The {@link Entity} to push
     * @param force             The amount of force to apply
     * @return Whether the entity was pushed or not
     */
    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID originatingPlayer, Location pushFromLocation, Entity pushed, double force) {
        Vector vector = pushed.getLocation().toVector()
            .subtract(pushFromLocation.toVector())
            .normalize()
            .multiply(force);
        return pushEntity(originatingPlayer, vector, pushed);
    }

    /**
     * Pushes the entity by the provided amount
     *
     * @param originatingPlayer The {@link UUID} of the player who caused the push
     * @param vector            The {@link Vector} to push the entity by
     * @param pushed            The {@link Entity} to push
     * @return Whether the entity was pushed or not
     */
    @ParametersAreNonnullByDefault
    public static boolean pushEntity(UUID originatingPlayer, Vector vector, Entity pushed) {
        Interaction interaction = pushed instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.INTERACT_ENTITY;
        if (Protections.hasPermission(originatingPlayer, pushed.getLocation(), interaction)) {
            pushed.setVelocity(vector);
            return true;
        }
        return false;
    }

    /**
     * Checks if the tested entity is facing the comparing entity
     *
     * @param testEntity    The entity to test
     * @param compareEntity The entity to compare to
     * @return Whether the tested entity is facing the comparing entity
     */
    public static double getFacing(@Nonnull Entity testEntity, @Nonnull Entity compareEntity) {
        Vector pd = testEntity.getLocation().getDirection();
        Vector ed = compareEntity.getLocation().getDirection();
        double x = (pd.getX() * ed.getZ()) - (pd.getZ() * ed.getX());
        double z = (pd.getX() * ed.getX()) + (pd.getZ() * ed.getZ());
        double a = Math.atan2(x, z);
        return (a * 180) / Math.PI;
    }

    /**
     * Checks if the tested entity is NOT facing the comparing entity
     *
     * @param testEntity    The entity to test
     * @param compareEntity The entity to compare to
     * @return Whether the tested entity is NOT facing the comparing entity
     */
    public static boolean isFacingAway(@Nonnull Entity testEntity, @Nonnull Entity compareEntity) {
        return isFacingAway(testEntity, compareEntity, null);
    }

    /**
     * Checks if the tested entity is NOT facing the comparing entity
     *
     * @param testEntity    The entity to test
     * @param compareEntity The entity to compare to
     * @param tolerance     The tolerance to use
     * @return Whether the tested entity is NOT facing the comparing entity
     */
    public static boolean isFacingAway(@Nonnull Entity testEntity,
                                       @Nonnull Entity compareEntity,
                                       @Nullable Integer tolerance
    ) {
        double d = getFacing(testEntity, compareEntity);
        int val = tolerance == null ? 30 : tolerance;
        return d <= val && d >= -val;
    }
}
