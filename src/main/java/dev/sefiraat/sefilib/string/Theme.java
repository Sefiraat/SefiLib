package dev.sefiraat.sefilib.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import net.md_5.bungee.api.ChatColor;

/**
 * A Theme is used to coordinate the display of Items and Strings within your addon.
 */
public class Theme {

    /**
     * A Yellow warning theme.
     */
    public static final Theme WARNING = new Theme(ChatColor.YELLOW, "Warning");
    /**
     * A Red error theme.
     */
    public static final Theme ERROR = new Theme(ChatColor.RED, "Error");
    /**
     * A White notice theme.
     */
    public static final Theme NOTICE = new Theme(ChatColor.WHITE, "Notice");
    /**
     * A Gray passive theme.
     */
    public static final Theme PASSIVE = new Theme(ChatColor.GRAY);
    /**
     * A Green success theme.
     */
    public static final Theme SUCCESS = new Theme(ChatColor.GREEN, "Success");
    /**
     * A Pale Yellow theme indicating where to click in the middle of a text.
     */
    public static final Theme CLICK_INFO = new Theme(ChatColor.of("#e4ed32"), "Click here");

    @Nonnull
    private final ChatColor color;
    @Nonnull
    private final String loreLine;

    /**
     * Creates a new {@link Theme} without a lore line.
     *
     * @param color The color of this theme.
     */
    public Theme(@Nonnull ChatColor color) {
        this(color, "");
    }

    /**
     * Creates a new {@link Theme}.
     *
     * @param color    The color of this theme.
     * @param loreLine The lore line of this theme.
     */
    public Theme(@Nonnull ChatColor color, @Nonnull String loreLine) {
        this.color = color;
        this.loreLine = loreLine;
    }

    /**
     * Returns the given string with the theme's color applied.
     *
     * @param string The string to color.
     * @return The colored string.
     */
    public String color(@Nonnull String string) {
        return this + string;
    }

    /**
     * Applies the theme color to a given string
     *
     * @param value The Object (as string) to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    public String apply(@Nonnull Object value) {
        return this.color + String.valueOf(value);
    }

    /**
     * Applies the theme color to the first string and PASSIVE to the second
     *
     * @param value1 The Object (as string) to apply the color to
     * @param value2 The Object (as string) to apply PASSIVE to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    public String asTitle(@Nonnull Object value1, @Nonnull Object value2) {
        return this.color + String.valueOf(value1) + ": " + Theme.PASSIVE + value2;
    }

    /**
     * Returns the {@link ChatColor} of this theme
     *
     * @return The {@link ChatColor} of this theme
     */
    @Nonnull
    public ChatColor getColor() {
        return color;
    }

    /**
     * Gets the {@link org.bukkit.Particle.DustOptions} for this theme.
     *
     * @param size The size of the particle
     * @return The {@link org.bukkit.Particle.DustOptions} for this theme.
     */
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

    /**
     * Gets the color code for this theme as a string.
     *
     * @return The color code for this theme as a string.
     */
    @Override
    @Nonnull
    public String toString() {
        return this.color.toString();
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param itemStack The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            ItemStack itemStack,
                                                            Theme themeType,
                                                            String name,
                                                            String... lore
    ) {
        return themedSlimefunItemStack(id, itemStack, themeType, name, Arrays.asList(lore));
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param itemStack The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            ItemStack itemStack,
                                                            Theme themeType,
                                                            String name,
                                                            List<String> lore
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            itemStack,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param material  The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            Material material,
                                                            Theme themeType,
                                                            String name,
                                                            String... lore
    ) {
        return themedSlimefunItemStack(id, material, themeType, name, Arrays.asList(lore));
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param material  The vanilla {@link Material} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            Material material,
                                                            Theme themeType,
                                                            String name,
                                                            List<String> lore
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            material,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors with parameters
     * for the plantable items/materials for seeds
     *
     * @param id              The ID for the new {@link SlimefunItemStack}
     * @param seedStack       The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType       The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name            The name to apply to the {@link SlimefunItemStack}
     * @param lore            The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @param validPlacements A list of valid placements for this seed.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSeed(String id,
                                               ItemStack seedStack,
                                               Theme themeType,
                                               String name,
                                               String[] lore,
                                               String[] validPlacements
    ) {
        return Theme.themedSeed(id, seedStack, themeType, name, Arrays.asList(lore), Arrays.asList(validPlacements));
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors with parameters
     * for the plantable items/materials for seeds
     *
     * @param id              The ID for the new {@link SlimefunItemStack}
     * @param seedStack       The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType       The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name            The name to apply to the {@link SlimefunItemStack}
     * @param lore            The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @param validPlacements A list of valid placements for this seed.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSeed(String id,
                                               ItemStack seedStack,
                                               Theme themeType,
                                               String name,
                                               List<String> lore,
                                               List<String> validPlacements
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        for (String s : validPlacements) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            seedStack,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * Applies the theme color to a given string
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param value     The object (as string) to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    public static String applyThemeToString(@Nonnull Theme themeType, @Nonnull Object value) {
        return themeType.getColor() + String.valueOf(value);
    }

    /**
     * Applies the theme color to the first string and PASSIVE to the second
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param string1   The string to apply the color to
     * @param value     The object to apply PASSIVE to as string
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeAsTitle(Theme themeType, String string1, Object value) {
        return themeType.getColor() + string1 + ": " + Theme.PASSIVE + value;
    }

    /**
     * Returns the loreline for this theme without the color applied.
     *
     * @return Returns the loreline for this theme without the color applied.
     */
    @Nonnull
    public String getLoreLine() {
        return loreLine;
    }

    /**
     * Gets an ItemStack with a pre-populated lore and name with themed colors.
     *
     * @param material  The {@link Material} used to base the {@link ItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link ItemStack} name
     * @param name      The name to apply to the {@link ItemStack}
     * @param lore      The lore lines for the {@link ItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link ItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack themedItemStack(Material material,
                                            Theme themeType,
                                            String name,
                                            @Nullable List<String> lore
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        if (lore != null) {
            finalLore.add("");
            for (String s : lore) {
                finalLore.add(passiveColor + s);
            }
        }
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new CustomItemStack(
            material,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }
}
