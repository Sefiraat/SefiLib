package dev.sefiraat.sefilib.misc;


import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

/**
 * This utility class provides a few handy methods to build a {@link Transformation}.
 *
 * @author Sfiguz7
 */
public final class TransformationBuilder {


    private AxisAngle4f firstRotation = new AxisAngle4f(0, 0, 1, 0);
    private AxisAngle4f secondRotation = new AxisAngle4f(0, 0, 1, 0);
    private Vector3f scaling = new Vector3f(1, 1, 1);
    private Vector3f translation = new Vector3f(0, 0, 0);

    /**
     * Set the second rotation for the {@link Transformation}.
     * @param face The {@link RotationFace} to rotate around
     * @param angle The angle to rotate by, in degrees
     */
    public TransformationBuilder firstRotation(RotationFace face, float angle) {
        // We assume the angle is in degrees, so we convert it to radians
        this.firstRotation = new AxisAngle4f((float) Math.toRadians(angle), face.getX(), face.getY(), face.getZ());
        return this;
    }

    /**
     * Set the second rotation for the {@link Transformation}.
     * @param face The {@link RotationFace} to rotate around
     * @param angle The angle to rotate by, in degrees
     */
    public TransformationBuilder secondRotation(RotationFace face, float angle) {
        // We assume the angle is in degrees, so we convert it to radians
        this.secondRotation = new AxisAngle4f((float) Math.toRadians(angle), face.getX(), face.getY(), face.getZ());
        return this;
    }

    /**
     * Set the scaling for the {@link Transformation}.
     * This allows for scaling of the DisplayGroup in the X, Y and Z axis.
     *
     * @param scaleX The multiplier for the X axis
     * @param scaleY The multiplier for the Y axis
     * @param scaleZ The multiplier for the Z axis
     */
    public TransformationBuilder scale(float scaleX, float scaleY, float scaleZ){
        this.scaling = new Vector3f(scaleX, scaleY, scaleZ);
        return this;
    }

    /**
     * Set the translation for the {@link Transformation}.
     * This allows for translation of the DisplayGroup in the X, Y and Z axis.
     *
     * @param deltaX The amount to translate in the X axis
     * @param deltaY The amount to translate in the Y axis
     * @param deltaZ The amount to translate in the Z axis
     */
    public TransformationBuilder translation(float deltaX, float deltaY, float deltaZ) {
        this.translation = new Vector3f(deltaX, deltaY, deltaZ);
        return this;
    }

    /**
     * Build the {@link Transformation}.
     * @return The {@link Transformation}
     */
    public Transformation build() {
        return new Transformation(this.translation, this.firstRotation, this.scaling, this.secondRotation);
    }
}

