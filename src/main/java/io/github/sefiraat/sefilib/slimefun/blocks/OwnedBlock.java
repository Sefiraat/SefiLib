package io.github.sefiraat.sefilib.slimefun.blocks;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public abstract class OwnedBlock<T extends SefiBlock<T>> extends SefiBlock<T> {

    private static final String OWNER_KEY = "block_owner";

    private final Map<BlockPosition, UUID> owners = new HashMap<>();

    protected OwnedBlock(ItemGroup itemGroup,
                         SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected OwnedBlock(ItemGroup itemGroup,
                         SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    public T getThis() {
        return (T) this;
    }

    protected void onBlockPlaced(@Nonnull BlockPlaceEvent event) {
        BlockStorage.addBlockInfo(event.getBlock(), OWNER_KEY, event.getPlayer().getUniqueId().toString());
        onBlockPlacedAfterOwnership(event);
    }

    protected void onBlockPlacedAfterOwnership(@Nonnull BlockPlaceEvent event) {

    }

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

    @ParametersAreNonnullByDefault
    protected void onTickAfterOwner(Block block, SlimefunItem item, Config data) {

    }

    @Nullable
    public OfflinePlayer getOwner(@Nonnull Block block) {
        return getOwner(new BlockPosition(block));
    }

    @Nullable
    public OfflinePlayer getOwner(@Nonnull BlockPosition blockPosition) {
        return Bukkit.getOfflinePlayer(this.owners.get(blockPosition));
    }

    public void setOwner(@Nonnull Block block, @Nonnull Player player) {
        setOwner(new BlockPosition(block), player);
    }

    public void setOwner(@Nonnull BlockPosition blockPosition, @Nonnull Player player) {
        BlockStorage.addBlockInfo(blockPosition.toLocation(), OWNER_KEY, player.getUniqueId().toString());
        owners.put(blockPosition, player.getUniqueId());
    }
}
