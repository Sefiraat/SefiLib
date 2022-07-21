package io.github.sefiraat.sefilib.misc;

public final class MiscUtils {

    private MiscUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> Class<T> forceInit(Class<T> clazz) {
        try {
            Class.forName(clazz.getName(), true, clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        return clazz;
    }

}
