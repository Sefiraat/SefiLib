package io.github.sefiraat.sefilib.itemgroup;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to be the 'Main' item group for any given addon. Should only be used when you
 * both want FlexItemGroups within your 'main' group and also have them be Nested.
 */
public class MainFlexGroup extends FlexItemGroup {

    private static final int GUIDE_BACK = 1;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };

    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    @Nonnull
    private final String name;
    private final List<ItemGroup> groups = new ArrayList<>();
    private final List<Pair<ItemStack, ChestMenu.MenuClickHandler>> prependItems = new ArrayList<>();
    private final List<Pair<ItemStack, ChestMenu.MenuClickHandler>> appendItems = new ArrayList<>();

    /**
     * Creates a new MainFlexGroup
     * @param name The name of the Group, this will be displayed when opened
     * @param key The {@link NamespacedKey} used to assign this group
     * @param item The {@link ItemStack} which will act as the display item
     */
    @ParametersAreNonnullByDefault
    public MainFlexGroup(String name, NamespacedKey key, ItemStack item) {
        super(key, item);
        this.name = name;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(name);

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        // Sound
        menu.addMenuOpeningHandler(p -> p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F));

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                "",
                ChatColor.GRAY + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (p, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu(profile, mode, 1);
            return false;
        });

        int itemSlot = 9;

        for (Pair<ItemStack, ChestMenu.MenuClickHandler> prependItemPair : prependItems) {
            menu.replaceExistingItem(itemSlot, prependItemPair.getFirstValue());
            menu.addMenuClickHandler(itemSlot, prependItemPair.getSecondValue());
            itemSlot++;
        }

        for (ItemGroup group : groups) {
            menu.replaceExistingItem(itemSlot, group.getItem(player));
            menu.addMenuClickHandler(itemSlot, (p, slot, item, clickAction) ->
                openPage(profile, group, mode, 1)
            );
            itemSlot++;
        }

        for (Pair<ItemStack, ChestMenu.MenuClickHandler> appendItemPair : appendItems) {
            menu.replaceExistingItem(itemSlot, appendItemPair.getFirstValue());
            menu.addMenuClickHandler(itemSlot, appendItemPair.getSecondValue());
            itemSlot++;
        }
    }

    @ParametersAreNonnullByDefault
    private boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }

    /**
     * Adds an {@link ItemGroup} that will be displayed to the players when this group is opened.
     * Groups are displayed in the order they are added.
     * @param itemGroup The {@link ItemGroup} to be added. Accepts Flex, Nested also
     * @return Returns this group, for method chaining
     */
    public MainFlexGroup addItemGroup(@Nonnull ItemGroup itemGroup) {
        groups.add(itemGroup);
        return this;
    }

    /**
     * Adds an {@link ItemStack} that will be displayed before all {@link ItemGroup}.
     * Items are displayed in the order they are added.
     * @param item The {@link ItemStack} to be added.
     * @param handler The {@link ChestMenu.MenuClickHandler} of the item.
     * @return Returns this group, for method chaining
     */
    @ParametersAreNonnullByDefault
    public MainFlexGroup prependItem(ItemStack item, ChestMenu.MenuClickHandler handler) {
        prependItems.add(new Pair<>(item, handler));
        return this;
    }

    /**
     * Adds an {@link ItemStack} that will be displayed after all {@link ItemGroup}.
     * Items are displayed in the order they are added.
     * @param item The {@link ItemStack} to be added.
     * @param handler The {@link ChestMenu.MenuClickHandler} of the item.
     * @return Returns this group, for method chaining
     */
    @ParametersAreNonnullByDefault
    public MainFlexGroup appendItem(ItemStack item, ChestMenu.MenuClickHandler handler) {
        appendItems.add(new Pair<>(item, handler));
        return this;
    }
}
