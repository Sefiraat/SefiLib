package dev.sefiraat.sefilib.itemstacks;

import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;


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
