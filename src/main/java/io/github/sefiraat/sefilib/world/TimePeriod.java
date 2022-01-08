package io.github.sefiraat.sefilib.world;

import org.bukkit.World;

import javax.annotation.Nonnull;

/**
 * Time Periods that occur in the minecraft day with their start and end times
 * {@link #SUNRISE} When the sun is rising
 * {@link #DAY} When the sun is out
 * {@link #SUNSET} When the sun is setting
 * {@link #NIGHT} When the sun isn't out
 * {@link #WAKE_UP} When the player and villagers will get out of their bed
 * {@link #BED_TIME_RAIN} When the player can go to bed during rain
 * {@link #BED_TIME_CLEAR} When the player can go to bed during clear weather
 * {@link #MOON_HIDDEN} When the moon hides past the horizon
 * {@link #MOON_VISIBLE} When the moon is visible in the sky
 * {@link #VILLAGER_WORK} When villagers can work during a day
 * {@link #VILLAGER_SOCIALISE} When villagers can socialise (specifically cannot work)
 * {@link #VILLAGER_BED_TIME} When villagers path find to their beds and/or sleep
 * {@link #SKY_LIGHT_WAX_CLEAR} When the light level begins to increase in clear weather
 * {@link #SKY_LIGHT_WAX_RAIN} When the light level begins to increase in rain
 * {@link #SKY_LIGHT_WANE_CLEAR} When the light level begins to decrease in clear weather
 * {@link #SKY_LIGHT_WANE_RAIN} When the light level begins to decrease in rain
 * {@link #MOB_SPAWN_CLEAR} The time period in which mobs COULD spawn in the overworld during clear weather
 * {@link #MOB_SPAWN_RAIN} The time period in which mobs COULD spawn in the overworld during rain
 */
public enum TimePeriod {
    SUNRISE(23000, 23999),
    DAY(24000, 11999),
    SUNSET(12000, 12999),
    NIGHT(13000, 22999),
    WAKE_UP(24000, 24000),
    BED_TIME_RAIN(12010, 23999),
    BED_TIME_CLEAR(12542, 23999),
    MOON_HIDDEN(167, 11833),
    MOON_VISIBLE(11834, 166),
    VILLAGER_WORK(2000, 8999),
    VILLAGER_SOCIALISE(9000, 11999),
    VILLAGER_BED_TIME(12000, 23999),
    SKY_LIGHT_WAX_CLEAR(22331, 23961),
    SKY_LIGHT_WAX_RAIN(22331, 23992),
    SKY_LIGHT_WANE_CLEAR(12040, 13670),
    SKY_LIGHT_WANE_RAIN(12010, 13670),
    MOB_SPAWN_CLEAR(13188, 22812),
    MOB_SPAWN_RAIN(12969, 23031);

    private final long start;
    private final long end;

    TimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }

    /**
     * Checks if the time would be day in a 'normal' world
     *
     * @param world The {@link World} to get the time from to check against
     * @return true if the given time would be day in the overworld.
     */
    public static boolean isDay(@Nonnull World world) {
        return isDay(world.getTime());
    }

    /**
     * Checks if the time would be day in a 'normal' world
     *
     * @param time The time to check against
     * @return true if the given time would be day in the overworld.
     */
    public static boolean isDay(Long time) {
        return time < 13000 || time > 24000;
    }

    /**
     * Checks if the time would be night in a 'normal' world
     *
     * @param world The {@link World} to get the time from to check against
     * @return true if the given time would be night in the overworld.
     */
    public static boolean isNight(@Nonnull World world) {
        return isNight(world.getTime());
    }

    /**
     * Checks if the time would be night in a 'normal' world
     *
     * @param time The time to check against
     * @return true if the given time would be night in the overworld.
     */
    public static boolean isNight(@Nonnull Long time) {
        return !isDay(time);
    }

    /**
     * Checks if the given time period would be active during current world time
     *
     * @param world      The {@link World} to get the time from
     * @param timePeriod The {@link TimePeriod} to check against
     * @return true if the TimePeriod is active during the world's time
     */
    public static boolean isActive(@Nonnull World world, @Nonnull TimePeriod timePeriod) {
        return isActive(world.getTime(), timePeriod);
    }

    /**
     * Checks if the given time period would be active during the given time
     *
     * @param time       The long value denoting the chosen time
     * @param timePeriod The {@link TimePeriod} to check against
     * @return true if the TimePeriod is active
     */
    public static boolean isActive(@Nonnull Long time, @Nonnull TimePeriod timePeriod) {
        return time >= timePeriod.getStart() && time <= timePeriod.getEnd();
    }

    /**
     * Checks if villagers would be awake during the provided time.
     *
     * @param world The {@link World} to get the time from to check against.
     * @return true if villagers can be awake during the world's time
     */
    public static boolean villagersAwake(@Nonnull World world) {
        return villagersAwake(world.getTime());
    }

    /**
     * Checks if villagers would be awake during the provided time.
     *
     * @param time The long value describing the time to check
     * @return true if villagers can be awake during the given time
     */
    public static boolean villagersAwake(@Nonnull Long time) {
        return time >= WAKE_UP.getStart() && time <= VILLAGER_BED_TIME.getEnd();
    }

    /**
     * Returns if the moon is still visible in the Sky.
     *
     * @param world The world to check.
     * @return True if the moon is/would be out, false if not or wrong world type.
     */
    public static boolean moonOut(@Nonnull World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return moonOut(world.getTime());
        }
        return false;
    }

    /**
     * Returns if the moon is still visible in the Sky during the specified time.
     * This method assumes the world is Overworld.
     *
     * @param time The time to check.
     * @return True if the moon is/would be out
     */
    public static boolean moonOut(@Nonnull Long time) {
        return time >= MOON_VISIBLE.getStart() && time <= MOON_HIDDEN.getEnd();
    }

    /**
     * Returns if the world can CURRENTLY spawn mobs naturally using time.
     * Does not check world, difficulty, region or environment.
     *
     * @param world The {@link World} to get the time and weather from
     * @return Returns true if mobs can spawn naturally at the current point in time
     */
    public static boolean naturalMobsCanSpawn(World world) {
        long time = world.getTime();
        return world.isClearWeather()
            ? naturalMobsCanSpawn(time, false)
            : naturalMobsCanSpawn(time, true);
    }

    /**
     * Returns if natural mobs would spawn at the given time and weather.
     *
     * @param time The time to check against
     * @param rain The weather to check against
     * @return Returns true if mobs can spawn naturally during the given time and weather
     */
    public static boolean naturalMobsCanSpawn(long time, boolean rain) {
        return rain
            ? time >= MOB_SPAWN_RAIN.getStart() && time <= MOB_SPAWN_RAIN.getEnd()
            : time >= MOB_SPAWN_CLEAR.getStart() && time <= MOB_SPAWN_CLEAR.getEnd();
    }

    /**
     * Returns if the given world is light.
     *
     * @param world The world to check.
     * @return True if past sunrise/before sunset or in a different world.
     */
    public static boolean isLight(@Nonnull World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return isDay(world.getTime());
        }
        return false;
    }

    /**
     * Returns if the given world is dark.
     *
     * @param world The world to check.
     * @return True if past sunset/before sunrise or in a different world.
     */
    public static boolean isDark(@Nonnull World world) {
        return !isLight(world);
    }
}