package dev.sefiraat.sefilib.localization;

import com.google.common.base.Preconditions;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class can be used to provide different strings based on the server's chosen language.
 */
public class LanguageManager {

    private static final Pattern YAML_ENTRY = Pattern.compile("[a-z0-9_-]+:.*");

    private final JavaPlugin javaPlugin;
    private final FileConfiguration defaultLanguage;
    private final FileConfiguration selectedLanguage;

    /**
     * Creates a new instance of the {@link LanguageManager} class.
     *
     * @param javaPlugin    The {@link JavaPlugin} instance.
     * @param langDirectory The directory where the language files are located. This is relative to the
     *                      plugin's data folder.
     * @param chosenLang    The language that is currently selected by the server.
     * @param defaultLang   The language that is used as a fallback if the selected language is not available or
     *                      if the string is not available.
     */
    @ParametersAreNonnullByDefault
    public LanguageManager(JavaPlugin javaPlugin, String langDirectory, String chosenLang, String defaultLang) {
        this.javaPlugin = javaPlugin;
        this.defaultLanguage = load(javaPlugin, langDirectory, defaultLang);
        this.selectedLanguage = load(javaPlugin, langDirectory, chosenLang);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    private FileConfiguration load(JavaPlugin plugin, String directory, String fileName) {
        final InputStream stream = plugin.getResource(directory + "/" + fileName);

        Preconditions.checkArgument(stream != null, "Unable to find language file " + fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            final String content = reader.lines().collect(Collectors.joining("\n"));
            final FileConfiguration config = new YamlConfiguration();

            if (YAML_ENTRY.matcher(content).find()) {
                config.loadFromString(content);
            }

            return config;
        } catch (IOException | InvalidConfigurationException e) {
            this.javaPlugin.getLogger().severe("Unable to find/load language file " + fileName);
            return new YamlConfiguration();
        }
    }

    /**
     * Gets the string for the given path. Will fallback to the default language if the string is not available.
     *
     * @param path The path to the string.
     * @return The string for the given path in the selected language or the default language if the
     * string is not available.
     */
    @Nonnull
    public String getString(@Nonnull String path) {
        String string = selectedLanguage.getString(path);
        if (string == null) {
            string = defaultLanguage.getString(path);
        }
        return Objects.requireNonNullElse(string, "Localisation error");
    }

    /**
     * Gets the string list for the given path. Will fallback to the default language if the string is not available.
     *
     * @param path The path to the string.
     * @return The string list for the given path in the selected language or the default language if the
     * string is not available.
     */
    @Nonnull
    public List<String> getStringList(@Nonnull String path) {
        List<String> string = selectedLanguage.getStringList(path);
        if (string.isEmpty()) {
            string = defaultLanguage.getStringList(path);
        }
        return Objects.requireNonNullElse(string, List.of("Localisation Error"));
    }

    /**
     * Gets the plugin instance.
     *
     * @return The plugin instance.
     */
    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    /**
     * Gets the default language file.
     *
     * @return The default language file.
     */
    public FileConfiguration getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * Gets the selected language file.
     *
     * @return The selected language file.
     */
    public FileConfiguration getSelectedLanguage() {
        return selectedLanguage;
    }
}
