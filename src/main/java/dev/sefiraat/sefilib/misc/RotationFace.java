package dev.sefiraat.sefilib.misc;

/**
 * This utility class provides a direct link between the faces a {@link dev.sefiraat.sefilib.entity.display.DisplayGroup}
 * rotates around and the corresponding axes. Due to the redundancy of having 6 faces, only 3 are used.
 *
 * @author Sfiguz7
 */
public enum RotationFace {
    FRONT(0, 0, 1),
    // Looking at the front, the SIDE face is to the right of the DisplayGroup
    SIDE(1, 0, 0),
    TOP(0, 1, 0);

    private final float x;
    private final float y;
    private final float z;

    RotationFace(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ(){
        return z;
    }
}
