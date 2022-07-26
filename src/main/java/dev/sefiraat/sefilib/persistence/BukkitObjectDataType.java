package dev.sefiraat.sefilib.persistence;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This {@link PersistentDataType} can be used to store and retrieve {@link ConfigurationSerializable}s from
 * {@link PersistentDataHolder}s.
 *
 * @param <T> The type of {@link ConfigurationSerializable} to store
 */
public class BukkitObjectDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {

    @Nonnull
    private final Class<T> clazz;

    /**
     * Creates a new {@link BukkitObjectDataType} for the given {@link ConfigurationSerializable} class.
     *
     * @param clazz The {@link ConfigurationSerializable} class to store
     */
    public BukkitObjectDataType(@Nonnull Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Gets the primative {@link Class} of the {@link ConfigurationSerializable}
     * that this {@link PersistentDataType} uses
     *
     * @return The {@link ConfigurationSerializable} class
     */
    @Nonnull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    /**
     * Gets the {@link ConfigurationSerializable} class that is stored by this {@link PersistentDataType}.
     *
     * @return The {@link ConfigurationSerializable} class
     */
    @Nonnull
    @Override
    public Class<T> getComplexType() {
        return this.clazz;
    }

    /**
     * Converts the provided {@link ConfigurationSerializable} to a byte array.
     *
     * @param bukkitObject The {@link ConfigurationSerializable} to convert
     * @param context      The {@link PersistentDataAdapterContext} to use
     * @return The byte array representation of the {@link ConfigurationSerializable}
     */
    @Nonnull
    @Override
    public byte[] toPrimitive(@Nonnull T bukkitObject, @Nonnull PersistentDataAdapterContext context) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitStream = new BukkitObjectOutputStream(stream);
            bukkitStream.writeObject(bukkitObject);
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * Converts the provided byte array to a {@link ConfigurationSerializable}.
     *
     * @param byteArray The byte array to convert
     * @param context   The {@link PersistentDataAdapterContext} to use
     * @return The {@link ConfigurationSerializable} representation of the byte array
     */
    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("unchecked")
    public T fromPrimitive(byte[] byteArray, PersistentDataAdapterContext context) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(byteArray);
            BukkitObjectInputStream bukkitStream = new BukkitObjectInputStream(stream);
            Object read = bukkitStream.readObject();
            return (T) read;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
