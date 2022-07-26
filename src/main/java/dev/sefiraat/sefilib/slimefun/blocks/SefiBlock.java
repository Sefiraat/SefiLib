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
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public abstract class SefiBlock<T extends SefiSlimefunItem<T>> extends SefiSlimefunItem<T> {

    private boolean sync = true;
    private boolean allowBlockPlacers = false;
    private boolean allowAndroids = false;
    private boolean allowExplosions = false;

    protected SefiBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected SefiBlock(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    protected T getThis() {
        return (T) this;
    }

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

    public boolean isSync() {
        return sync;
    }

    public T setSync(boolean sync) {
        this.sync = sync;
        return getThis();
    }

    public boolean isAllowBlockPlacers() {
        return allowBlockPlacers;
    }

    public T setAllowBlockPlacers(boolean allowBlockPlacers) {
        this.allowBlockPlacers = allowBlockPlacers;
        return getThis();
    }

    public boolean isAllowAndroids() {
        return allowAndroids;
    }

    public T setAllowAndroids(boolean allowAndroids) {
        this.allowAndroids = allowAndroids;
        return getThis();
    }

    public boolean isAllowExplosions() {
        return allowExplosions;
    }

    public T setAllowExplosions(boolean allowExplosions) {
        this.allowExplosions = allowExplosions;
        return getThis();
    }

    protected void onBlockPlaced(@Nonnull BlockPlaceEvent event) {

    }

    @ParametersAreNonnullByDefault
    protected void onTick(Block block, SlimefunItem item, Config data) {

    }

    protected void onUniqueTick() {

    }

    protected void onPlayerRightClickBlock(@Nonnull PlayerRightClickEvent event) {

    }

    @ParametersAreNonnullByDefault
    protected void onPlayerBreaksBlock(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {

    }
}
