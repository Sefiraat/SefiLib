package dev.sefiraat.sefilib.slimefun.itemgroup;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents an icon in the {@link SimpleFlexGroup}
 */
public class MenuItem {
    @Nullable
    private ItemStack item;
    @Nullable
    private ChestMenu.MenuClickHandler clickHandler;
    @Nullable
    private ItemGroup itemGroup;

    /**
     * Creates a new MenuItem
     *
     * @param itemGroup The {@link ItemGroup} to be added. Accepts Flex, Nested and ItemGroup (DummyItemGroup)
     *                  is preferable.
     */
    public MenuItem(@Nonnull ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    /**
     * Creates a new {@link MenuItem} with an {@link ItemStack} and a {@link ChestMenu.MenuClickHandler}
     *
     * @param item         The {@link ItemStack} to be displayed
     * @param clickHandler The {@link ChestMenu.MenuClickHandler} to be executed when the item is clicked
     */
    public MenuItem(@Nonnull ItemStack item, @Nonnull ChestMenu.MenuClickHandler clickHandler) {
        this.item = item;
        this.clickHandler = clickHandler;
    }

    /**
     * Returns the {@link ItemStack} that will be displayed to the players when in the guide.
     *
     * @return The {@link ItemStack} to be displayed
     */
    @Nullable
    public ItemStack getItem() {
        return item;
    }

    /**
     * Returns the {@link ChestMenu.MenuClickHandler} for this {@link MenuItem}
     *
     * @return The {@link ChestMenu.MenuClickHandler} for this {@link MenuItem}
     */
    @Nullable
    public ChestMenu.MenuClickHandler getClickHandler() {
        return clickHandler;
    }

    /**
     * Returns the {@link ItemGroup} of this {@link MenuItem}
     *
     * @return The {@link ItemGroup} of this {@link MenuItem}
     */
    @Nullable
    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    /**
     * Creates a new MenuItem derived from an ItemGroup
     *
     * @param itemGroup the ItemGroup to be added to the group
     * @return Returns a new MenuItem
     */
    @Nonnull
    public static MenuItem of(ItemGroup itemGroup) {
        return new MenuItem(itemGroup);
    }

    /**
     * Creates a new MenuItem derived from an ItemStack and a custom
     * {@link ChestMenu.MenuClickHandler}
     *
     * @param item         the ItemStack to display on the guide
     * @param clickHandler the MenuClickHandler to be executed when the item is clicked
     * @return Returns a new MenuItem
     */
    @Nonnull
    public static MenuItem of(ItemStack item, ChestMenu.MenuClickHandler clickHandler) {
        return new MenuItem(item, clickHandler);
    }
}
