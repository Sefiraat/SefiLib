package dev.sefiraat.sefilib.world;

import org.bukkit.Location;

import javax.annotation.Nonnull;

/**
 * This class contains static methods for dealing with locations.
 */
public final class LocationUtils {

    private LocationUtils() {
        throw new IllegalStateException("Utility class");
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
