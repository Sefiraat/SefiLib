package dev.sefiraat.sefilib.protections;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * This class contains static methods for dealing with Slimefun4's protections.
 */
public final class Protections {

    private Protections() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if the given {@link Player} is allowed to interact with the given {@link Block}.
     *
     * @param player      The {@link Player} to check.
     * @param block       The {@link Block} to check.
     * @param interaction The {@link Interaction} type to check.
     * @return True if the {@link Player} is allowed to interact with the {@link Block}.
     */
    public static boolean hasPermission(@Nonnull Player player,
                                        @Nonnull Block block,
                                        @Nonnull Interaction interaction
    ) {
        return hasPermission(player.getUniqueId(), block.getLocation(), interaction);
    }

    /**
     * Checks if the given {@link Player} is allowed to interact with the given {@link Block}.
     *
     * @param player      The {@link Player} to check.
     * @param location    The {@link Location} of the block to check.
     * @param interaction The {@link Interaction} type to check.
     * @return True if the {@link Player} is allowed to interact with the {@link Block}.
     */
    public static boolean hasPermission(@Nonnull Player player,
                                        @Nonnull Location location,
                                        @Nonnull Interaction interaction
    ) {
        return hasPermission(player.getUniqueId(), location, interaction);
    }

    /**
     * Checks if the given player is allowed to interact with the given {@link Block}.
     *
     * @param player      The {@link UUID} of the OfflinePlayer to check.
     * @param block       The {@link Block} to check.
     * @param interaction The {@link Interaction} type to check.
     * @return True if the player is allowed to interact with the {@link Block}.
     */
    public static boolean hasPermission(@Nonnull UUID player, @Nonnull Block block, @Nonnull Interaction interaction) {
        return hasPermission(player, block.getLocation(), interaction);
    }

    /**
     * Checks if the given player is allowed to interact with the given {@link Block}.
     *
     * @param player      The {@link UUID} of the OfflinePlayer to check.
     * @param location    The {@link Location} of the block to check.
     * @param interaction The {@link Interaction} type to check.
     * @return True if the player is allowed to interact with the {@link Block}.
     */
    public static boolean hasPermission(@Nonnull UUID player,
                                        @Nonnull Location location,
                                        @Nonnull Interaction interaction
    ) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
        return Slimefun.getProtectionManager().hasPermission(offlinePlayer, location, interaction);
    }
}
