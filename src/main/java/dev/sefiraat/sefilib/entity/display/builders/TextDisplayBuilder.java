package dev.sefiraat.sefilib.entity.display.builders;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TextDisplayBuilder extends DisplayBuilder<TextDisplayBuilder> {

    protected String text;
    protected int lineWidth;
    protected boolean hasLineWidth;
    protected Color backgroundColor;
    protected byte textOpacity;
    protected boolean hasTextOpacity;
    protected boolean shadowed;
    protected boolean hasShadowed;
    protected boolean seeThrough;
    protected boolean hasSeeThrough;
    protected boolean defaultBackground;
    protected boolean hasDefaultBackground;
    protected TextDisplay.TextAlignment textAlignment;

    public TextDisplayBuilder setText(@Nullable String text) {
        this.text = text;
        return this;
    }

    public TextDisplayBuilder setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        this.hasLineWidth = true;
        return this;
    }

    public TextDisplayBuilder setBackgroundColor(@Nonnull Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TextDisplayBuilder setTextOpacity(byte textOpacity) {
        this.textOpacity = textOpacity;
        this.hasTextOpacity = true;
        return this;
    }

    public TextDisplayBuilder setShadowed(boolean shadowed) {
        this.shadowed = shadowed;
        this.hasShadowed = true;
        return this;
    }

    public TextDisplayBuilder setSeeThrough(boolean seeThrough) {
        this.seeThrough = seeThrough;
        this.hasSeeThrough = true;
        return this;
    }

    public TextDisplayBuilder setDefaultBackground(boolean defaultBackground) {
        this.defaultBackground = defaultBackground;
        this.hasDefaultBackground = true;
        return this;
    }

    public TextDisplayBuilder setTextAlignment(@Nonnull TextDisplay.TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        return this;
    }

    public TextDisplay build() {
        if (this.location == null) {
            throw new IllegalStateException("You must provide a Location for the Display Entity");
        }
        return generateDisplay(location);
    }

    public TextDisplay build(@Nonnull DisplayGroup group) {
        if (this.groupParentOffset == null) {
            throw new IllegalStateException("You must provide a Group Parent Offset vector");
        }
        return generateDisplay(group.getLocation().clone().add(groupParentOffset));
    }

    private TextDisplay generateDisplay(@Nonnull Location location) {
        if (this.textAlignment == null) {
            throw new IllegalStateException("you must provide a TextAlignment for this Display");
        }
        final TextDisplay display = (TextDisplay) location.getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        if (text != null) {
            display.setText(text);
        }
        display.setLineWidth(lineWidth);
        if (backgroundColor != null) {
            display.setBackgroundColor(backgroundColor);
        }
        display.setTextOpacity(textOpacity);
        display.setShadowed(shadowed);
        display.setSeeThrough(seeThrough);
        display.setDefaultBackground(defaultBackground);
        if (textAlignment != null) {
            display.setAlignment(textAlignment);
        }

        applyDisplay(display);
        return display;
    }
}
