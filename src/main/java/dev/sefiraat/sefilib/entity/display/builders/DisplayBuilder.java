package dev.sefiraat.sefilib.entity.display.builders;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

abstract class DisplayBuilder<T extends DisplayBuilder<T>> {
    protected Transformation transformation;
    protected int interpolationDuration;
    protected boolean hasInterpolationDuration;
    protected int interpolationDelay;
    protected boolean hasInterpolationDelay;
    protected float viewRange;
    protected boolean hasViewRange;
    protected float shadowRadius;
    protected boolean hasShadowRadius;
    protected float shadowStrength;
    protected boolean hasShadowStrength;
    protected float displayWidth;
    protected boolean hasDisplayWidth;
    protected float displayHeight;
    protected boolean hasDisplayHeight;
    protected Display.Billboard billboard;
    protected Color glowColorOverride;
    protected Display.Brightness brightness;
    protected Location location;
    protected Vector groupParentOffset;

    public T setTransformation(@Nonnull Transformation transformation) {
        this.transformation = transformation;
        return (T) this;
    }

    public T setInterpolationDuration(int interpolationDuration) {
        this.interpolationDuration = interpolationDuration;
        this.hasInterpolationDuration = true;
        return (T) this;
    }

    public T setInterpolationDelay(int interpolationDelay) {
        this.interpolationDelay = interpolationDelay;
        this.hasInterpolationDelay = true;
        return (T) this;
    }

    public T setViewRange(float viewRange) {
        this.viewRange = viewRange;
        this.hasViewRange = true;
        return (T) this;
    }

    public T setShadowRadius(float shadowRadius) {
        this.shadowRadius = shadowRadius;
        this.hasShadowRadius = true;
        return (T) this;
    }

    public T setShadowStrength(float shadowStrength) {
        this.shadowStrength = shadowStrength;
        this.hasShadowStrength = true;
        return (T) this;
    }

    public T setDisplayWidth(float displayWidth) {
        this.displayWidth = displayWidth;
        this.hasDisplayWidth = true;
        return (T) this;
    }

    public T setDisplayHeight(float displayHeight) {
        this.displayHeight = displayHeight;
        this.hasDisplayHeight = true;
        return (T) this;
    }

    public T setBillboard(@Nonnull Display.Billboard billboard) {
        this.billboard = billboard;
        return (T) this;
    }

    public T setGlowColorOverride(@Nullable Color glowColorOverride) {
        this.glowColorOverride = glowColorOverride;
        return (T) this;
    }

    public T setBrightness(@Nullable Display.Brightness brightness) {
        this.brightness = brightness;
        return (T) this;
    }

    public T setLocation(Location location) {
        this.location = location;
        return (T) this;
    }

    public T setGroupParentOffset(Vector groupParentOffset) {
        this.groupParentOffset = groupParentOffset;
        return (T) this;
    }

    protected void applyDisplay(@Nonnull Display display) {
        if (transformation != null) {
            display.setTransformation(transformation);
        }
        if (hasInterpolationDuration) {
            display.setInterpolationDuration(interpolationDuration);
        }
        if (hasInterpolationDelay) {
            display.setInterpolationDelay(interpolationDelay);
        }
        if (hasViewRange) {
            display.setViewRange(viewRange);
        }
        if (hasShadowRadius) {
            display.setShadowRadius(shadowRadius);
        }
        if (hasShadowStrength) {
            display.setShadowStrength(shadowStrength);
        }
        if (hasDisplayWidth) {
            display.setDisplayWidth(displayWidth);
        }
        if (hasDisplayHeight) {
            display.setDisplayHeight(displayHeight);
        }
        if (billboard != null) {
            display.setBillboard(billboard);
        }
        if (glowColorOverride != null) {
            display.setGlowColorOverride(glowColorOverride);
        }
        if (brightness != null) {
            display.setBrightness(brightness);
        }
    }
}
