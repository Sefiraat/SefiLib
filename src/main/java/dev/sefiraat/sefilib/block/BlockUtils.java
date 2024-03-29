package dev.sefiraat.sefilib.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * This class contains static methods for dealing with blocks.
 */
public final class BlockUtils {

    private BlockUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Credits : https://www.spigotmc.org/threads/getting-the-blockface-of-a-targeted-block.319181/
     * Gets the BlockFace of the block the player is currently targeting.
     *
     * @param player the player's whose targeted blocks BlockFace is to be checked.
     * @return the BlockFace of the targeted block, or null if the targeted block is non-occluding.
     */
    @Nullable
    public static BlockFace getTargetedBlockFace(@Nonnull Player player) {
        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
        if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) {
            return null;
        }
        Block targetBlock = lastTwoTargetBlocks.get(1);
        Block adjacentBlock = lastTwoTargetBlocks.get(0);
        return targetBlock.getFace(adjacentBlock);
    }

    /**
     * Returns true if the block is a Player Head or Wall Player Head
     *
     * @param block The block to test
     * @return True if a player head (wall or not)
     */
    public static boolean isSkullBlock(@Nonnull Block block) {
        return block.getType() == Material.PLAYER_HEAD || block.getType() == Material.PLAYER_WALL_HEAD;
    }
}
