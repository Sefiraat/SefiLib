package dev.sefiraat.sefilib.entity.display;

import dev.sefiraat.sefilib.persistence.StringListDataType;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DisplayGroup {

    // todo Don't like this - can we offload key to the plugin
    private static final NamespacedKey KEY_LIST = new NamespacedKey("sefilib", "child_display_list");
    private static final NamespacedKey KEY_NAMES = new NamespacedKey("sefilib", "child_display_names");
    @Nonnull
    private final Interaction parentDisplay;
    private final Map<String, Display> displays = new HashMap<>();

    public DisplayGroup(@Nonnull Location location) {
        this(location, 1, 1);
    }

    public DisplayGroup(@Nonnull Location location, float height, float width) {
        parentDisplay = (Interaction) location.getWorld().spawnEntity(location, EntityType.INTERACTION);
        parentDisplay.setInteractionHeight(height);
        parentDisplay.setInteractionWidth(width);
        applyLists(new ArrayList<>(), new ArrayList<>());
    }

    public DisplayGroup(@Nonnull Interaction textDisplay) {
        parentDisplay = textDisplay;

        final List<String> childList = getChildList();
        final List<String> childNames = getChildNames();

        if (childList == null || childNames == null) {
            throw new IllegalArgumentException("This display was never part of a group");
        }
        if (childList.size() != childNames.size()) {
            throw new IllegalStateException("This display's memory has been borked");
        }

        for (int i = 0; i < childList.size(); i++) {
            final String s = childList.get(i);
            final UUID uuid = UUID.fromString(s);
            final Entity entity = Bukkit.getEntity(uuid);
            if (entity == null || entity.isDead() || !(entity instanceof Display display)) {
                continue;
            }
            this.displays.put(childNames.get(i), display);
        }
    }

    @Nonnull
    public Interaction getParentDisplay() {
        return parentDisplay;
    }

    public UUID getParentUUID() {
        return parentDisplay.getUniqueId();
    }

    public Location getLocation() {
        return parentDisplay.getLocation();
    }

    public Map<String, Display> getDisplays() {
        return Collections.unmodifiableMap(displays);
    }

    public void addDisplay(@Nonnull String name, @Nonnull Display display) {
        // todo stop duplicate naming
        final List<String> childList = getChildList();
        final List<String> childNames = getChildNames();
        if (childList == null || childNames == null) {
            throw new IllegalArgumentException("This display doesn't appear to have a group");
        }
        childList.add(display.getUniqueId().toString());
        childNames.add(name);
        applyLists(childList, childNames);

        this.displays.put(name, display);
    }

    @Nullable
    public Display removeDisplay(@Nonnull String name) {
        final Display display = this.displays.remove(name);
        if (display == null) {
            return display;
        }
        final List<String> childList = getChildList();
        final List<String> childNames = getChildNames();
        if (childList == null || childNames == null) {
            throw new IllegalArgumentException("This display doesn't appear to have a group");
        }
        childList.add(display.getUniqueId().toString());
        childNames.add(name);
        applyLists(childList, childNames);
        return display;
    }

    public void killDisplay(@Nonnull String name) {
        Display display = removeDisplay(name);
        if (display != null) {
            display.remove();
        }
    }

    public void remove() {
        displays.forEach((s, display) -> display.remove());
        parentDisplay.remove();
    }

    public void teleport(@Nonnull Location location) {
        this.displays.forEach((s, display) -> {
            final Location offset = getParentDisplay().getLocation().subtract(display.getLocation());
            display.teleport(location.clone().add(offset));
        });
        this.getParentDisplay().teleport(location);
    }

    @Nullable
    private List<String> getChildList() {
        return PersistentDataAPI.get(parentDisplay, KEY_LIST, StringListDataType.TYPE);
    }

    @Nullable
    private List<String> getChildNames() {
        return PersistentDataAPI.get(parentDisplay, KEY_NAMES, StringListDataType.TYPE);
    }

    private void applyLists(@Nonnull List<String> childList, @Nonnull List<String> childNames) {
        PersistentDataAPI.set(parentDisplay, KEY_LIST, StringListDataType.TYPE, childList);
        PersistentDataAPI.set(parentDisplay, KEY_NAMES, StringListDataType.TYPE, childNames);
    }

    @Nullable
    public static DisplayGroup fromUUID(@Nonnull UUID uuid) {
        Entity entity = Bukkit.getEntity(uuid);
        if (entity == null || entity.isDead() || !(entity instanceof Interaction display)) {
            return null;
        }
        return fromInteraction(display);
    }

    @Nullable
    public static DisplayGroup fromInteraction(@Nonnull Interaction interaction) {
        if (PersistentDataAPI.has(interaction, KEY_LIST, StringListDataType.TYPE)) {
            return new DisplayGroup(interaction);
        } else {
            return null;
        }
    }
}
