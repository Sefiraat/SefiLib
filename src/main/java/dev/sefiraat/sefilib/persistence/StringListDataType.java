package dev.sefiraat.sefilib.persistence;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class StringListDataType implements PersistentDataType<PersistentDataContainer, List<String>> {

    public static final PersistentDataType<PersistentDataContainer, List<String>> TYPE = new StringListDataType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public Class<List<String>> getComplexType() {
        return (Class<List<String>>) (Class<?>) List.class;
    }

    @Override
    @Nonnull
    public PersistentDataContainer toPrimitive(@Nonnull List<String> complex,
                                               @Nonnull PersistentDataAdapterContext context
    ) {
        final PersistentDataContainer container = context.newPersistentDataContainer();
        for (int i = 0; i < complex.size(); i++) {
            final NamespacedKey key = new NamespacedKey("sefilib", String.valueOf(i));
            container.set(key, STRING, complex.get(i));
        }


        return container;
    }

    @Override
    @Nonnull
    public List<String> fromPrimitive(@Nonnull PersistentDataContainer primitive,
                                           @Nonnull PersistentDataAdapterContext context
    ) {
        final List<String> strings = new ArrayList<>();
        for (NamespacedKey key : primitive.getKeys()) {
            strings.add(primitive.get(key, STRING));
        }
        return strings;
    }
}
