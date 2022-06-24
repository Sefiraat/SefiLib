package io.github.sefiraat.sefilib.persistence.types;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BukkitObjectDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {

    @Nonnull
    private final Class<T> clazz;

    public BukkitObjectDataType(@Nonnull Class<T> clazz) {
        this.clazz = clazz;
    }

    @Nonnull
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Nonnull
    @Override
    public Class<T> getComplexType() {
        return this.clazz;
    }

    @Nonnull
    @Override
    public byte[] toPrimitive(T bukkitObject, PersistentDataAdapterContext persistentDataAdapterContext) {
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

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("unchecked")
    public T fromPrimitive(byte[] byteArray, PersistentDataAdapterContext persistentDataAdapterContext) {
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
