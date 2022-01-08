package io.github.sefiraat.sefilib.string;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public enum Theme {
    MAIN(ChatColor.of("#21588f"), ""),
    WARNING(ChatColor.YELLOW, "Warning"),
    ERROR(ChatColor.RED, "Error"),
    NOTICE(ChatColor.WHITE, "Notice"),
    PASSIVE(ChatColor.GRAY, ""),
    SUCCESS(ChatColor.GREEN, "Success"),
    INFO(ChatColor.of("#323232"), "Information"),
    CLICK_INFO(ChatColor.of("#e4ed32"), "Click here"),
    RESEARCH(ChatColor.of("#a60e03"), "Research"),
    RECIPE_TYPE(ChatColor.of("#ffe89c"), "Recipe Type"),
    MATERIAL_CLASS(ChatColor.of("#a4c2ba"), "Material Class"),
    GUIDE(ChatColor.of("#444444"), "Guide"),


    TOOL(ChatColor.of("#6b32a8"), "Tool"),
    WEAPON(ChatColor.of("#3d32a8"), "Weapon"),
    ARMOR(ChatColor.of("#6b32a8"), "Armor"),
    GADGET(ChatColor.of("#8732a8"), "Gadget"),
    EXALTED(ChatColor.of("#8732a8"), "Exalted"),
    POTION(ChatColor.of("#e6c522"), "Potion"),

    MACHINE(ChatColor.of("#3295a8"), "Machine"),
    MECHANISM(ChatColor.of("#3295a8"), "Mechanism"),
    CHEST(ChatColor.of("#b89b1c"), "Chest"),

    MOLTEN_METAL(ChatColor.of("#21588f"), "Molten Metal"),
    LIQUID(ChatColor.of("#65dbb4"), "Liquid"),
    CAST(ChatColor.of("#ffe138"), "Cast"),
    PART(ChatColor.of("#42c8f5"), "Part"),
    STAVE(ChatColor.of("#c2fc03"), "Stave"),
    MODIFICATION(ChatColor.of("#bf32af"), "Modification"),
    TRAIT(ChatColor.of("#bf307f"), "Material Trait"),

    DROP(ChatColor.of("#bf307f"), "Rare Drop"),
    BASE(ChatColor.of("#9e9e9e"), "Base Resource"),
    CRAFTING(ChatColor.of("#dbcea9"), "Crafting Material"),
    FUEL(ChatColor.of("#112211"), "Fossil Fuel"),
    CRYSTAL(ChatColor.of("#dbcea9"), "Crystal");

    private static final Theme[] CACHED_VALUES = values();

    @Nonnull
    private final ChatColor color;
    @Nonnull
    private final String loreLine;

    @ParametersAreNonnullByDefault
    Theme(ChatColor color, String loreLine) {
        this.color = color;
        this.loreLine = loreLine;

    }

    @Nonnull
    public Particle.DustOptions getDustOptions(float size) {
        return new Particle.DustOptions(
            Color.fromRGB(
                color.getColor().getRed(),
                color.getColor().getGreen(),
                color.getColor().getBlue()
            ),
            size
        );
    }

    @Nonnull
    public ChatColor getColor() {
        return this.color;
    }

    @Nonnull
    public String getLoreLine() {
        return this.loreLine;
    }

    @Nonnull
    public String apply(@Nonnull String string) {
        return this.color + string;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum class should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return this.color.toString();
    }

    /**
     * Applies the theme color to a given string
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param string    The string to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyTheme(Theme themeType, String string) {
        return themeType.getColor() + string;
    }
}
