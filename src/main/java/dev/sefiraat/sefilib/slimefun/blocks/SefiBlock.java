package dev.sefiraat.sefilib.slimefun.blocks;

import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import dev.sefiraat.sefilib.slimefun.items.SefiSlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * This abstract class can be extended to simplify the creation of Slimefun blocks with methods for
 * ticking, right-clicking, placing, breaking, and using.
 *
 * @param <T> The type of {@link SefiSlimefunItem} that is used to create this block.
 */
public abstract class SefiBlock<T extends SefiSlimefunItem<T>> extends SefiSlimefunItem<T> {

    private boolean sync = true;
    private boolean allowBlockPlacers = false;
    private boolean allowAndroids = false;
    private boolean allowExplosions = false;

    /**
     * Creates a new {@link SefiBlock}.
     *
     * @param itemGroup  The {@link ItemGroup} this block belongs to.
     * @param item       The {@link SlimefunItemStack} that is used to create this block.
     * @param recipeType The {@link RecipeType} of this block.
     * @param recipe     The recipe of this block.
     */
    protected SefiBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    /**
     * Creates a new {@link SefiBlock}.
     *
     * @param itemGroup    The {@link ItemGroup} this block belongs to.
     * @param item         The {@link SlimefunItemStack} that is used to create this block.
     * @param recipeType   The {@link RecipeType} of this block.
     * @param recipe       The recipe of this block.
     * @param recipeOutput The output of this block.
     */
    protected SefiBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    /**
     * Returns this object
     *
     * @return This object
     */
    protected T getThis() {
        return (T) this;
    }

    /**
     * Adds the various required handlers to this block.
     * Do not override without calling super.
     */
    @Override
    @OverridingMethodsMustInvokeSuper
    public void preRegister() {
        addItemHandler(
            new BlockPlaceHandler(allowBlockPlacers) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                    onBlockPlaced(e);
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return sync;
                }

                @Override
                @ParametersAreNonnullByDefault
                public void tick(Block block, SlimefunItem item, Config data) {
                    onTick(block, item, data);
                }

                @Override
                public void uniqueTick() {
                    onUniqueTick();
                }
            },
            (BlockUseHandler) this::onPlayerRightClickBlock,
            new BlockBreakHandler(allowAndroids, allowExplosions) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                    onPlayerBreaksBlock(event, item, drops);
                }
            }
        );
    }

    /**
     * Returns whether this block should ticked synchronously.
     *
     * @return Whether this block should ticked synchronously.
     */
    public boolean isSync() {
        return sync;
    }

    /**
     * Sets whether this block should ticked synchronously.
     *
     * @param sync Whether this block should ticked synchronously.
     * @return This object
     */
    public T setSync(boolean sync) {
        this.sync = sync;
        return getThis();
    }

    /**
     * Returns whether this block should allow block placers.
     * @return Whether this block should allow block placers.
     */
    public boolean isAllowBlockPlacers() {
        return allowBlockPlacers;
    }

    /**
     * Sets whether this block should allow block placers.
     * @param allowBlockPlacers Whether this block should allow block placers.
     * @return This object
     */
    public T setAllowBlockPlacers(boolean allowBlockPlacers) {
        this.allowBlockPlacers = allowBlockPlacers;
        return getThis();
    }

    /**
     * Returns whether this block should allow androids to break it.
     * @return Whether this block should allow androids to break it.
     */
    public boolean isAllowAndroids() {
        return allowAndroids;
    }

    /**
     * Sets whether this block should allow androids to break it.
     * @param allowAndroids Whether this block should allow androids to break it.
     * @return This object
     */
    public T setAllowAndroids(boolean allowAndroids) {
        this.allowAndroids = allowAndroids;
        return getThis();
    }

    /**
     *  Returns whether this block should allow explosions to break it.
     * @return Whether this block should allow explosions to break it.
     */
    public boolean isAllowExplosions() {
        return allowExplosions;
    }

    /**
     * Sets whether this block should allow explosions to break it.
     * @param allowExplosions Whether this block should allow explosions to break it.
     * @return This object
     */
    public T setAllowExplosions(boolean allowExplosions) {
        this.allowExplosions = allowExplosions;
        return getThis();
    }

    /**
     * Override this method to add custom code to be executed when this block is placed.
     * @param event The {@link BlockPlaceEvent} that was fired.
     */
    protected void onBlockPlaced(@Nonnull BlockPlaceEvent event) {

    }

    /**
     * Override this method to add custom code to be executed when this block is ticked.
     * @param block The {@link Block} that was ticked.
     * @param item The {@link SlimefunItem} that was ticked.
     * @param data The {@link Config} from {@link BlockStorage}
     */
    @ParametersAreNonnullByDefault
    protected void onTick(Block block, SlimefunItem item, Config data) {

    }

    /**
     * Override this method to add custom code to be executed when this {@link SlimefunItem} is uniquely ticked.
     */
    protected void onUniqueTick() {

    }

    /**
     * Override this method to add custom code to be executed when this block is right-clicked.
     * @param event The {@link PlayerRightClickEvent} that was fired.
     */
    protected void onPlayerRightClickBlock(@Nonnull PlayerRightClickEvent event) {

    }

    /**
     * Override this method to add custom code to be executed when this block is broken.
     * @param event The {@link BlockBreakEvent} that was fired.
     * @param item The {@link ItemStack} that was used to break this block.
     * @param drops The {@link List} of {@link ItemStack}s that will be dropped when this block is broken.
     */
    @ParametersAreNonnullByDefault
    protected void onPlayerBreaksBlock(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {

    }
}
