package dev.sefiraat.sefilib.world;

import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class contains static methods for dealing with locations.
 */
public final class LocationUtils {

    private LocationUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Picks a random location within a given range around a point
     *
     * @param centreLocation The {@link Location} which acts as the centre of the random check
     * @param range          The range in blocks in which to spread out from
     * @return The {@link Location} randomly selected
     */
    @Nonnull
    public static Location randomLocation(@Nonnull Location centreLocation, int range) {
        return randomLocation(centreLocation, range, range, range);
    }

    /**
     * Picks a random location within a given range around a point
     *
     * @param centreLocation The {@link Location} which acts as the centre of the random check
     * @param rangeX         The range in blocks in which to spread out from on the X axis
     * @param rangeY         The range in blocks in which to spread out from on the Y axis
     * @param rangeZ         The range in blocks in which to spread out from on the Z axis
     * @return The {@link Location} randomly selected
     */
    @Nonnull
    public static Location randomLocation(@Nonnull Location centreLocation, int rangeX, int rangeY, int rangeZ) {
        final double randomX = ThreadLocalRandom.current().nextInt(-rangeX, rangeX + 1);
        final double randomY = ThreadLocalRandom.current().nextInt(-rangeY, rangeY + 1);
        final double randomZ = ThreadLocalRandom.current().nextInt(-rangeZ, rangeZ + 1);
        return centreLocation.clone().add(randomX, randomY, randomZ);
    }

    /**
     * Returns a location in the centre of a block at the given location.
     *
     * @param location The location to centre on.
     * @return The centre location.
     */
    public static Location centre(@Nonnull Location location) {
        return location.clone().add(0.5, 0.5, 0.5);
    }

}
