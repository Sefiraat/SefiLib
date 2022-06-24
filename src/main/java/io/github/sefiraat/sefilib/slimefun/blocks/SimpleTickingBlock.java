package io.github.sefiraat.sefilib.slimefun.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple ticking block that does not have a GUI.
 * Override onFirstTick for Caching or other actions normally
 * undertaken in {@link me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset}'s onNewInstance
 */
public abstract class SimpleTickingBlock extends SlimefunItem {

    protected final Map<Location, Boolean> firstTickMap = new HashMap<>();

    protected SimpleTickingBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(itemGroup, item, recipeType, recipe, null);
    }

    protected SimpleTickingBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        addItemHandler(blockTicker());
    }

    /**
     * This method will fire the first time this block ticks, either when placed or after a
     * server restart. Override this method if you need to grab from BlockStorage and cache
     * the information like you normally would in newInstance if using a BlockMenuPreset
     * @param block The {@link Block} being ticked
     * @param slimefunItem The {@link SlimefunItem} that is stored in the block
     * @param config The {@link Config} from BlockStorage at the time the block ticked
     */
    protected abstract void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config);

    /**
     * This method will fire everytime the block ticks (including the the first tick, just
     * after onFirstTick().
     * @param block The {@link Block} being ticked
     * @param slimefunItem The {@link SlimefunItem} that is stored in the block
     * @param config The {@link Config} from BlockStorage at the time the block ticked
     */
    protected abstract void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config);

    /**
     * This method fires when the block is first placed
     * @param event The {@link BlockPlaceEvent} passed from Bukkit via Slimefun
     */
    protected abstract void onPlace(@Nonnull BlockPlaceEvent event);

    /**
     * This method will fire when the block is broken. Override this method if you need to
     * wrap anything up or drop items etc.
     * @param blockBreakEvent The {@link BlockBreakEvent} passed from Bukkit via Slimefun
     * @param itemStack The {@link} ItemStack held by the player
     * @param list The {@link List} of items about to be dropped
     */
    protected abstract void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list);

    protected ItemHandler[] blockTicker() {
        return new ItemHandler[]{
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    if (!firstTickMap.containsKey(block.getLocation())) {
                        onFirstTick(block, slimefunItem, config);
                        firstTickMap.put(block.getLocation(), true);
                    }
                    onTick(block, slimefunItem, config);
                }
            },
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    onPlace(event);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
                    onBreak(blockBreakEvent, itemStack, list);
                }
            }
        };
    }
}
