package dev.sefiraat.sefilib.slimefun.blocks;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import dev.sefiraat.sefilib.entity.display.DisplayInteractable;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public abstract class DisplayGroupBlock extends SlimefunItem implements DisplayInteractable {

    public static final String KEY_UUID = "display-uuid";
    private final Function<Location, DisplayGroup> displayGroupFunction;

    public DisplayGroupBlock(ItemGroup itemGroup,
                             SlimefunItemStack item,
                             RecipeType recipeType,
                             ItemStack[] recipe,
                             Function<Location, DisplayGroup> displayGroupFunction
    ) {
        this(itemGroup, item, recipeType, recipe, null, displayGroupFunction);
    }

    public DisplayGroupBlock(ItemGroup itemGroup,
                             SlimefunItemStack item,
                             RecipeType recipeType,
                             ItemStack[] recipe,
                             @Nullable ItemStack recipeOutput,
                             Function<Location, DisplayGroup> displayGroupFunction
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        this.displayGroupFunction = displayGroupFunction;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void preRegister() {
        addItemHandler(
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    Location location = event.getBlock().getLocation();
                    DisplayGroup displayGroup = displayGroupFunction.apply(location.clone().add(0.5, 0, 0.5));
                    setUUID(displayGroup, location);
                    event.getBlock().setType(getBaseMaterial());
                    whenPlaced(event);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                    Location location = e.getBlock().getLocation();
                    DisplayGroup displayGroup = getDisplayGroup(location);
                    if (displayGroup == null) {
                        return;
                    }
                    displayGroup.remove();
                    e.getBlock().setType(Material.AIR);
                }
            }
        );
    }

    protected void whenPlaced(@Nonnull BlockPlaceEvent event) {
        // Can be overridden by extending classes.
    }

    @ParametersAreNonnullByDefault
    protected void whenBroken(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
        // Can be overridden by extending classes.
    }

    @Nonnull
    protected abstract Material getBaseMaterial();

    private void setUUID(@Nonnull DisplayGroup displayGroup, @Nonnull Location location) {
        BlockStorage.addBlockInfo(location, KEY_UUID, displayGroup.getParentUUID().toString());
    }

    @Nullable
    @OverridingMethodsMustInvokeSuper
    public UUID getUUID(@Nonnull Location location) {
        String uuid = BlockStorage.getLocationInfo(location, KEY_UUID);
        if (uuid == null) {
            return null;
        }
        return UUID.fromString(uuid);
    }

    @Nullable
    @OverridingMethodsMustInvokeSuper
    public DisplayGroup getDisplayGroup(@Nonnull Location location) {
        UUID uuid = getUUID(location);
        if (uuid == null) {
            return null;
        }
        return DisplayGroup.fromUUID(uuid);
    }
}
