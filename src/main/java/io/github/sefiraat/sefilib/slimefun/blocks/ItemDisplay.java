package io.github.sefiraat.sefilib.slimefun.blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class ItemDisplay extends SimpleTickingBlock {

    protected static final String ITEM_UUID = "itemUUID";

    protected final Map<Location, UUID> itemMap = new HashMap<>();
    protected final Map<Location, Integer> currentTickMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    protected ItemDisplay(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        this.addItemHandler((BlockUseHandler) this::onRightClick);
    }

    public abstract void onRightClick(PlayerRightClickEvent e);

    @Override
    protected void onFirstTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        final Location blockLocation = block.getLocation();
        String itemUuidString = BlockStorage.getLocationInfo(block.getLocation(), ITEM_UUID);
        if (itemUuidString != null) {
            itemMap.put(block.getLocation(), UUID.fromString(itemUuidString));
        }
        // Set a random current tick
        ItemDisplay.this.currentTickMap.put(
            blockLocation,
            ThreadLocalRandom.current().nextInt(3, 7)
        );
    }

    @Override
    protected void onTick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        final Location blockLocation = block.getLocation();
        final UUID currentItemUuid = itemMap.get(blockLocation);

        // If an item isn't in place, then don't do anything
        if (currentItemUuid != null) {
            int currentTick = currentTickMap.get(blockLocation);
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);

            if (currentItem == null) {
                return;
            }

            // Only tick periodically
            if (currentTick <= 0 && ItemDisplay.this.itemMap.containsKey(blockLocation)) {
                final Location itemLocation = currentItem.getLocation();
                final Location desiredLocation = blockLocation.clone().add(0.5, 1.5, 0.5);

                // Check if item has moved off the platform
                if (itemLocation.distance(desiredLocation) > 0.3) {
                    final ItemStack itemStack = currentItem.getItemStack();
                    blockLocation.getWorld().dropItemNaturally(blockLocation, itemStack);
                    BlockStorage.addBlockInfo(block, ITEM_UUID, null);
                    itemMap.remove(blockLocation);
                    currentItem.remove();
                }

                ItemDisplay.this.currentTickMap.put(blockLocation, ThreadLocalRandom.current().nextInt(4, 8));
            } else {
                currentTick--;
                ItemDisplay.this.currentTickMap.put(blockLocation, currentTick);
            }
            afterTick(currentItem, block, slimefunItem, config);
        }
    }

    @Override
    protected void onPlace(@Nonnull BlockPlaceEvent event) {
        ItemDisplay.this.currentTickMap.put(
            event.getBlock().getLocation(),
            ThreadLocalRandom.current().nextInt(3, 7)
        );
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent blockBreakEvent, @Nonnull ItemStack itemStack, @Nonnull List<ItemStack> list) {
        Location location = blockBreakEvent.getBlock().getLocation();
        final UUID currentItemUuid = itemMap.get(location);
        if (currentItemUuid != null) {
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
            if (currentItem != null) {
                final ItemStack displayStack = currentItem.getItemStack();
                location.getWorld().dropItemNaturally(location, displayStack);
                currentItem.remove();
            }
            BlockStorage.clearBlockInfo(location);
        }
    }

    public abstract void afterTick(@Nonnull Item item, @Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config);
}
