package dev.sefiraat.sefilib.slimefun.itemgroup;

import io.github.bakedlibs.dough.chat.ChatInput;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to be the 'Main' item group for any given addon. Should only be used when you
 * both want FlexItemGroups within your 'main' group and also have them be Nested.
 *
 * @author Sefiraat
 * @author ybw0014
 */
public class SimpleFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;
    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_SEARCH = 7;
    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };

    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    @Nonnull
    private final JavaPlugin plugin;
    @Nonnull
    private final String name;
    private final List<MenuItem> menuItems = new ArrayList<>();

    /**
     * Creates a new SimpleFlexGroup
     *
     * @param plugin The {@link JavaPlugin} instance of your addon.
     * @param name   The name of the Group, this will be displayed when opened
     * @param key    The {@link NamespacedKey} used to assign this group
     * @param item   The {@link ItemStack} which will act as the display item
     */
    @ParametersAreNonnullByDefault
    public SimpleFlexGroup(JavaPlugin plugin, String name, NamespacedKey key, ItemStack item) {
        super(key, item);
        this.plugin = plugin;
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
        setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        // Pagination
        final int amount = menuItems.size();
        final int totalPages = (int) Math.ceil(amount / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, amount);
        final List<MenuItem> sublist = menuItems.subList(start, end);

        // Header / Footer
        reapplyHeader(player, profile, mode, menu);
        reapplyFooter(player, profile, mode, menu, page, totalPages);

        // Sound
        menu.addMenuOpeningHandler(p -> p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F));

        // Add Items
        for (int i = 0; i < sublist.size(); i++) {
            final MenuItem menuItem = sublist.get(i);
            final int slot = 9 + i;
            if (menuItem.getItemGroup() != null) {
                final ItemGroup itemGroup = menuItem.getItemGroup();
                menu.replaceExistingItem(slot, itemGroup.getItem(player));
                menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) ->
                    openPage(profile, itemGroup, mode, 1, page)
                );
            } else if (menuItem.getItem() != null && menuItem.getClickHandler() != null) {
                menu.replaceExistingItem(slot, menuItem.getItem());
                menu.addMenuClickHandler(slot, menuItem.getClickHandler());
            }
        }
    }

    @ParametersAreNonnullByDefault
    private boolean openPage(PlayerProfile profile,
                             ItemGroup itemGroup,
                             SlimefunGuideMode mode,
                             int page,
                             int returnPage
    ) {
        profile.getGuideHistory().add(this, returnPage);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }

    /**
     * Adds an {@link ItemGroup} that will be displayed to the players when this group is opened.
     * items are displayed in the order they are added.
     *
     * @param itemGroup The {@link ItemGroup} to be added. Accepts Flex, Nested also
     * @return Returns this group, for method chaining
     */
    public SimpleFlexGroup addItemGroup(@Nonnull ItemGroup itemGroup) {
        menuItems.add(MenuItem.of(itemGroup));
        return this;
    }

    /**
     * Adds an {@link ItemStack} and a custom {@link me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler}
     * that will be displayed to the players when this group is opened.
     * items are displayed in the order they are added.
     *
     * @param menuItem The {@link MenuItem} to be added. Accepts Flex, Nested also
     * @return Returns this group, for method chaining
     */
    public SimpleFlexGroup addMenuItem(@Nonnull MenuItem menuItem) {
        menuItems.add(menuItem);
        return this;
    }

    @ParametersAreNonnullByDefault
    private void reapplyFooter(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int page,
                               int totalPages
    ) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                setupPage(player1, profile, mode, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                setupPage(player1, profile, mode, menu, nextPage);
            }
            return false;
        });
    }

    @ParametersAreNonnullByDefault
    private void reapplyHeader(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
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
            SlimefunGuide.openMainMenu(profile, mode, profile.getGuideHistory().getMainMenuPage());
            return false;
        });

        // Search
        menu.replaceExistingItem(
            GUIDE_SEARCH,
            ChestMenuUtils.getSearchButton(player)
        );
        menu.addMenuClickHandler(GUIDE_SEARCH, (p, slot, itemStack, clickAction) -> {
            p.closeInventory();
            Slimefun.getLocalization().sendMessage(p, "guide.search.message");
            ChatInput.waitForPlayer(plugin, p, msg -> SlimefunGuide.openSearch(profile, msg, mode, true));

            return false;
        });
    }
}
