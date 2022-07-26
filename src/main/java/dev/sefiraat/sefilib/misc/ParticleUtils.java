package dev.sefiraat.sefilib.misc;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class contains basic utility methods for creating particles
 */
public final class ParticleUtils {

    private ParticleUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates 5 particle effects at the provided location with a random offset
     *
     * @param entity      The entity to create the particles around
     * @param particle    The particle to create
     * @param rangeRadius The radius of the area to create the particles in
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity, Particle particle, double rangeRadius) {
        displayParticleRandomly(entity.getLocation(), particle, rangeRadius, 5);
    }

    /**
     * Creates the specified amount of particle effects at the provided location with a random offset
     *
     * @param location          The location to create the particles around
     * @param particle          The particle to create
     * @param rangeRadius       The radius of the area to create the particles in
     * @param numberOfParticles The amount of particles to create
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location,
                                               Particle particle,
                                               double rangeRadius,
                                               int numberOfParticles
    ) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y, z), 1);
        }
    }

    /**
     * Creates a particle effect at the provided location
     *
     * @param entity            The entity to create the particles around
     * @param particle          The particle to create
     * @param rangeRadius       The radius of the area to create the particles in
     * @param numberOfParticles The amount of particles to create
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity,
                                               Particle particle,
                                               double rangeRadius,
                                               int numberOfParticles
    ) {
        displayParticleRandomly(entity.getLocation().clone().add(0, 1, 0), particle, rangeRadius, numberOfParticles);
    }

    /**
     * Creates a particle effect at the provided location
     *
     * @param location    The location to create the particles around
     * @param particle    The particle to create
     * @param rangeRadius The radius of the area to create the particles in
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location, Particle particle, double rangeRadius) {
        displayParticleRandomly(location, particle, rangeRadius, 5);
    }

    /**
     * Creates a dust particle effect at the provided location
     *
     * @param entity            The entity to create the particles around
     * @param rangeRadius       The radius of the area to create the particles in
     * @param numberOfParticles The amount of particles to create
     * @param dustOptions       The options for the dust particle
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity,
                                               double rangeRadius,
                                               int numberOfParticles,
                                               Particle.DustOptions dustOptions
    ) {
        displayParticleRandomly(entity.getLocation(), rangeRadius, numberOfParticles, dustOptions);
    }

    /**
     * Creates a dust particle effect at the provided location
     *
     * @param location          The location to create the particles around
     * @param rangeRadius       The radius of the area to create the particles in
     * @param numberOfParticles The amount of particles to create
     * @param dustOptions       The options for the dust particle
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location,
                                               double rangeRadius,
                                               int numberOfParticles,
                                               Particle.DustOptions dustOptions
    ) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(x, y, z), 1, dustOptions);
        }
    }

    /**
     * Creates a dust particle effect at the provided location
     *
     * @param entity      The entity to create the particles around
     * @param rangeRadius The radius of the area to create the particles in
     * @param dustOptions The options for the dust particle
     */
    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity, double rangeRadius, Particle.DustOptions dustOptions) {
        displayParticleRandomly(entity.getLocation(), rangeRadius, 5, dustOptions);
    }

    /**
     * Creates a line of particles from the provided location to the provided target location
     *
     * @param particle The particle to create
     * @param start    The location to start the line from
     * @param end      The location to end the line at
     * @param space    The space between each particle
     */
    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle, Location start, Location end, double space) {
        drawLine(particle, start, end, space, null);
    }

    /**
     * Creates a line of particles from the provided location to the provided target location
     *
     * @param particle    The particle to create
     * @param start       The location to start the line from
     * @param end         The location to end the line at
     * @param space       The space between each particle
     * @param dustOptions The options for the dust particle
     */
    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle,
                                Location start,
                                Location end,
                                double space,
                                @Nullable Particle.DustOptions dustOptions
    ) {
        final double distance = start.distance(end);
        double currentPoint = 0;
        final Vector startVector = start.toVector();
        final Vector endVector = end.toVector();
        final Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);

        while (currentPoint < distance) {
            if (dustOptions != null) {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1,
                    dustOptions
                );
            } else {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1
                );
            }
            currentPoint += space;
            startVector.add(vector);
        }
    }

    /**
     * Creates a line of dust particles from the provided location to the provided target location
     *
     * @param dustOptions The options for the dust particle
     * @param start       The location to start the line from
     * @param end         The location to end the line at
     * @param space       The space between each particle
     */
    @ParametersAreNonnullByDefault
    public static void drawLine(Particle.DustOptions dustOptions, Location start, Location end, double space) {
        drawLine(Particle.REDSTONE, start, end, space, dustOptions);
    }

    /**
     * Creates a line of {@link Location}s from the provided location to the provided target location
     *
     * @param start The location to start the line from
     * @param end   The location to end the line at
     * @param space The space between each location
     * @return A list of locations between the start and end location
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static List<Location> getLine(Location start, Location end, double space) {
        final double distance = start.distance(end);
        double currentPoint = 0;
        final Vector startVector = start.toVector();
        final Vector endVector = end.toVector();
        final Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);

        List<Location> locations = new ArrayList<>();

        while (currentPoint < distance) {
            locations.add(new Location(
                start.getWorld(),
                startVector.getX(),
                startVector.getY(),
                startVector.getZ()
            ));

            currentPoint += space;
            startVector.add(vector);
        }
        return locations;
    }

    /**
     * Draws a cubic shape of particles at the provided location
     *
     * @param particle The particle to create
     * @param corner1  The first corner of the cube
     * @param corner2  The second corner of the cube
     * @param space    The space between each particle
     */
    @ParametersAreNonnullByDefault
    public static void drawCube(Particle particle, Location corner1, Location corner2, double space) {
        drawCube(particle, corner1, corner2, space, null);
    }

    /**
     * Draws a cubic shape of particles at the provided location
     * https://www.spigotmc.org/threads/create-particles-in-cube-outline-shape.65991/
     *
     * @param particle         The particle to create
     * @param corner1          The first corner of the cube
     * @param corner2          The second corner of the cube
     * @param particleDistance The distance between each particle
     * @param dustOptions      The options for the dust particle
     */
    @ParametersAreNonnullByDefault
    public static void drawCube(Particle particle,
                                Location corner1,
                                Location corner2,
                                double particleDistance,
                                @Nullable Particle.DustOptions dustOptions
    ) {
        World world = corner1.getWorld();
        double minX = Math.min(corner1.getX(), corner2.getX());
        double minY = Math.min(corner1.getY(), corner2.getY());
        double minZ = Math.min(corner1.getZ(), corner2.getZ());
        double maxX = Math.max(corner1.getX(), corner2.getX());
        double maxY = Math.max(corner1.getY(), corner2.getY());
        double maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (double x = minX; x <= maxX; x += particleDistance) {
            for (double y = minY; y <= maxY; y += particleDistance) {
                for (double z = minZ; z <= maxZ; z += particleDistance) {
                    int components = 0;
                    if (x == minX || x == maxX) {
                        components++;
                    }
                    if (y == minY || y == maxY) {
                        components++;
                    }
                    if (z == minZ || z == maxZ) {
                        components++;
                    }
                    if (components >= 2) {
                        if (dustOptions != null) {
                            world.spawnParticle(particle, x, y, z, 1, dustOptions);
                        } else {
                            world.spawnParticle(particle, x, y, z, 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Draws a cubic shape of dust particles at the provided location
     *
     * @param dustOptions The options for the dust particle
     * @param corner1     The first corner of the cube
     * @param corner2     The second corner of the cube
     * @param space       The space between each particle
     */
    @ParametersAreNonnullByDefault
    public static void drawCube(Particle.DustOptions dustOptions, Location corner1, Location corner2, double space) {
        drawCube(Particle.REDSTONE, corner1, corner2, space, dustOptions);
    }
}
