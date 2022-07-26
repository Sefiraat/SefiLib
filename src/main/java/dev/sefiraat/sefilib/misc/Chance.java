package dev.sefiraat.sefilib.misc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class contains basic utility methods for chance and rolls.
 */
public final class Chance {

    private Chance() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Tests a chance roll starting from 1 to upper
     *
     * @param chance The chance to succeed between 0-1
     * @return true if roll passes
     */
    public static boolean testChance(double chance) {
        return ThreadLocalRandom.current().nextDouble() <= chance;
    }

    /**
     * Tests a chance roll starting from 1 to upper
     *
     * @param chance The number the roll must be lower than
     * @param upper  The highest possible number that could roll (inclusive)
     * @return true if roll passes
     */
    public static boolean testChance(int chance, int upper) {
        return roll(upper, true) <= chance;
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper   The highest possible number that could roll (inclusive)
     * @param upLimit If true, the bound will be increased for 1 for inclusivity while
     *                maintaining readability for manually typed numbers
     *                (i.e. Upper 50 converts to 51 returning a max of 50 still).
     *                Set false when using 0 based indexes (list.size())
     * @return rolled int
     */
    public static int roll(int upper, boolean upLimit) {
        if (upLimit) upper++;
        return ThreadLocalRandom.current().nextInt(1, upper);
    }

    /**
     * Tests a chance roll starting from 1 to {@param upper}
     *
     * @param chance The number the roll must be lower than
     * @param upper  The highest possible number that could roll (inclusive)
     * @return true if roll passes
     */
    public static boolean testChance(double chance, double upper) {
        return roll(upper, true) <= chance;
    }

    /**
     * Rolls a number starting from 0 to upper
     *
     * @param upper The highest possible number that could roll (inclusive)
     * @return rolled int
     */
    public static int roll(int upper) {
        return roll(upper, true);
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper The highest possible number that could roll (inclusive)
     * @return rolled int
     */
    public static double roll(double upper) {
        return roll(upper, true);
    }

    /**
     * Rolls a number starting from 1 to upper
     *
     * @param upper   The highest possible number that could roll (inclusive)
     * @param upLimit If true, the bound will be increased for 1 for inclusivity while
     *                maintaining readability for manually typed numbers
     *                (i.e. Upper 50 converts to 51 returning a max of 50 still).
     *                Set false when using 0 based indexes (list.size())
     * @return rolled int
     */
    public static double roll(double upper, boolean upLimit) {
        if (upLimit) upper++;
        return ThreadLocalRandom.current().nextDouble(1, upper);
    }
}
