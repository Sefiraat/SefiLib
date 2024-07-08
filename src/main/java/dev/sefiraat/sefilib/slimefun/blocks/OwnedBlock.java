package dev.sefiraat.sefilib.slimefun.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

/**
 * This block will, when placed, register it's placing player as its owner.
 * It will also check for the owner after a restart.
 */
public abstract class OwnedBlock extends SlimefunItem {

    private static final String OWNER_KEY = "block_owner";

    private final Map<BlockPosition, UUID> owners = new HashMap<>();

    /**
     * Creates a new {@link OwnedBlock}.
     *
     * @param itemGroup  The {@link ItemGroup} this block belongs to.
     * @param item       The {@link SlimefunItemStack} that is used to create this block.
     * @param recipeType The {@link RecipeType} of this block.
     * @param recipe     The recipe of this block.
     */
    protected OwnedBlock(ItemGroup itemGroup,
                         SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    /**
     * Creates a new {@link OwnedBlock}.
     *
     * @param itemGroup    The {@link ItemGroup} this block belongs to.
     * @param item         The {@link SlimefunItemStack} that is used to create this block.
     * @param recipeType   The {@link RecipeType} of this block.
     * @param recipe       The recipe of this block.
     * @param recipeOutput The recipe output of this block.
     */
    protected OwnedBlock(ItemGroup itemGroup,
                         SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    /**
     * This method fires when the block is placed. Should not be overridden, instead use
     * {@link #afterBlockPlaced(BlockPlaceEvent)}
     *
     * @param event The {@link BlockPlaceEvent} that triggered this.
     */
    protected void onBlockPlaced(@Nonnull BlockPlaceEvent event) {
        BlockStorage.addBlockInfo(event.getBlock(), OWNER_KEY, event.getPlayer().getUniqueId().toString());
        afterBlockPlaced(event);
    }

    /**
     * This method is called after the block is placed and after the owner is set.
     * Override to add your own code to handle block placement.
     *
     * @param event The {@link BlockPlaceEvent} that triggered this.
     */
    protected void afterBlockPlaced(@Nonnull BlockPlaceEvent event) {

    }

    /**
     * This method is called each time the block is ticked and should not be overridden.
     * Instead, use {@link #onTickAfterOwner(Block, SlimefunItem, Config)}
     *
     * @param block The {@link Block} that is being ticked.
     * @param item  The {@link SlimefunItem} that is being ticked.
     * @param data  The {@link Config} from BlockStorage
     */
    @ParametersAreNonnullByDefault
    protected void onTick(Block block, SlimefunItem item, Config data) {
        final BlockPosition blockPosition = new BlockPosition(block);
        if (!owners.containsKey(blockPosition)) {
            final String ownerString = BlockStorage.getLocationInfo(block.getLocation(), OWNER_KEY);
            try {
                final UUID uuid = UUID.fromString(ownerString);
                owners.put(blockPosition, uuid);
            } catch (IllegalArgumentException exception) {
                final Logger logger = OwnedBlock.this.addon.getLogger();
                logger.severe("A block has no valid registered owner and will likely error.");
                logger.severe("The block is located at: " + block.getLocation());
            }
        }
        onTickAfterOwner(block, item, data);
    }

    /**
     * This method is called after the owner is set and should be overridden to add your own code
     * to handle block ticking.
     *
     * @param block The {@link Block} that is being ticked.
     * @param item  The {@link SlimefunItem} that is being ticked.
     * @param data  The {@link Config} from BlockStorage
     */
    @ParametersAreNonnullByDefault
    protected void onTickAfterOwner(Block block, SlimefunItem item, Config data) {

    }

    /**
     * Gets the owner of the given block.
     *
     * @param block The {@link Block} that is being checked.
     * @return The owner of the block.
     */
    @Nullable
    public OfflinePlayer getOwner(@Nonnull Block block) {
        return getOwner(new BlockPosition(block));
    }

    /**
     * Gets the owner of the given block.
     *
     * @param blockPosition The {@link BlockPosition} of the block that is being checked.
     * @return The owner of the block.
     */
    @Nullable
    public OfflinePlayer getOwner(@Nonnull BlockPosition blockPosition) {
        return Bukkit.getOfflinePlayer(this.owners.get(blockPosition));
    }

    /**
     * Sets the owner of the given block.
     *
     * @param block  The {@link Block} that to set the owner of.
     * @param player The {@link Player} that is being set as the owner.
     */
    public void setOwner(@Nonnull Block block, @Nonnull Player player) {
        setOwner(new BlockPosition(block), player);
    }

    /**
     * Sets the owner of the given block.
     *
     * @param blockPosition The {@link BlockPosition} of the block that to set the owner of.
     * @param player        The {@link Player} that is being set as the owner.
     */
    public void setOwner(@Nonnull BlockPosition blockPosition, @Nonnull Player player) {
        BlockStorage.addBlockInfo(blockPosition.toLocation(), OWNER_KEY, player.getUniqueId().toString());
        owners.put(blockPosition, player.getUniqueId());
    }
}
