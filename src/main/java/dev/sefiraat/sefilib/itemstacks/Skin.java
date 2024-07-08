package dev.sefiraat.sefilib.itemstacks;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;


/**
 * This class can be used to quickly create and store a PlayerHead hash with methods for retrieving
 * a skull {@link ItemStack} and {@link PlayerSkin}
 */
public class Skin {
    @Nonnull
    private final String hash;
    @Nonnull
    private final PlayerSkin playerSkin;

    public Skin(@Nonnull String hash) {
        this.hash = hash;
        this.playerSkin = PlayerSkin.fromHashCode(hash);
    }

    @Nonnull
    public String getHash() {
        return hash;
    }

    @Nonnull
    public ItemStack getPlayerHead() {
        return PlayerHead.getItemStack(playerSkin);
    }

    @Nonnull
    public PlayerSkin getPlayerSkin() {
        return playerSkin;
    }
}
