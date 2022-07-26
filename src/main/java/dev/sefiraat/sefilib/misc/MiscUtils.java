package dev.sefiraat.sefilib.misc;

/**
 * This class contains basic utility methods that do not fit in any other category.
 */
public final class MiscUtils {

    private MiscUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Forces a class to be loaded by the JVM
     *
     * @param clazz The class to load
     * @param <T>   The type of the class
     * @return The class that was loaded
     */
    public static <T> Class<T> forceInit(Class<T> clazz) {
        try {
            Class.forName(clazz.getName(), true, clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        return clazz;
    }

}
