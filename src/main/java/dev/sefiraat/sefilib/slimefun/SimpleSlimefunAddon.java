package dev.sefiraat.sefilib.slimefun;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.text.MessageFormat;

public abstract class SimpleSlimefunAddon extends JavaPlugin implements SlimefunAddon {

    private static SimpleSlimefunAddon instance;

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        tryUpdate();
        setupStats();
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    protected abstract String getGitHubUsername();

    @Nonnull
    protected abstract String getGitHubRepoName();

    @Nonnull
    protected abstract String getGitHubBranchName();

    protected abstract int getBStatsId();

    protected abstract boolean isAutoUpdate();

    private void setupStats() {
        if (getBStatsId() != 0) {
            Metrics metrics = new Metrics(this, getBStatsId());
        }
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", getGitHubUsername(), getGitHubRepoName());
    }

    private void tryUpdate() {
        if (isAutoUpdate() && getDescription().getVersion().startsWith("DEV")) {
            String updateLocation = MessageFormat.format(
                "{0}/{1}/{2}",
                getGitHubUsername(),
                getGitHubRepoName(),
                getGitHubBranchName()
            );
            GitHubBuildsUpdater updater = new GitHubBuildsUpdater(this, getFile(), updateLocation);
            updater.start();
        }
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return instance.getServer().getPluginManager();
    }

    public static void logError(@Nonnull String string) {
        instance.getLogger().severe(string);
    }

    public static void logWarning(@Nonnull String string) {
        instance.getLogger().warning(string);
    }

    public static void logInfo(@Nonnull String string) {
        instance.getLogger().info(string);
    }


}
