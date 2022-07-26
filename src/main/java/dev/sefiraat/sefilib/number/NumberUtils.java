package dev.sefiraat.sefilib.number;

/**
 * This class contains basic utility methods for numbers.
 */
public final class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if the given number is even.
     *
     * @param i The number to check.
     * @return True if the number is even.
     */
    public static boolean isEven(int i) {
        return i % 2 == 0;
    }

    /**
     * Checks if the given number is odd.
     *
     * @param i The number to check.
     * @return True if the number is odd.
     */
    public static boolean isOdd(int i) {
        return !isEven(i);
    }
}
