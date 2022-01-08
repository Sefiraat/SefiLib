package io.github.sefiraat.sefilib.misc;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class Keys {

    private Keys() {
        throw new IllegalStateException("Utility Class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull JavaPlugin instance, @Nonnull String value) {
        return new NamespacedKey(instance, value);
    }
}
