package io.github.sefiraat.sefilib.world;

import org.bukkit.Location;

import javax.annotation.Nonnull;

public final class LocationUtils {

    private LocationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Location centre(@Nonnull Location location) {
        return location.clone().add(0.5, 0.5, 0.5);
    }

}
