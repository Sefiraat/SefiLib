package dev.sefiraat.sefilib.number;

public final class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isEven(int i) {
        return i % 2 == 0;
    }

    public static boolean isOdd(int i) {
        return !isEven(i);
    }
}
