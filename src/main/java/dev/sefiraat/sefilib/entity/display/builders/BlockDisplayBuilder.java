package dev.sefiraat.sefilib.entity.display.builders;

import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;

public class BlockDisplayBuilder extends DisplayBuilder<BlockDisplayBuilder> {

    protected BlockData blockData;

    public BlockDisplay build() {
        if (this.location == null) {
            throw new IllegalStateException("You must provide a Location for the Display Entity");
        }
        return generateDisplay(location);
    }

    public BlockDisplay build(@Nonnull DisplayGroup group) {
        if (this.groupParentOffset == null) {
            throw new IllegalStateException("You must provide a Group Parent Offset vector");
        }
        return generateDisplay(group.getLocation().clone().add(groupParentOffset));
    }

    private BlockDisplay generateDisplay(@Nonnull Location location) {
        if (this.blockData == null) {
            throw new IllegalStateException("you must provide a BlockData Object for this Display");
        }
        final BlockDisplay display = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);

        display.setBlock(blockData);
        applyDisplay(display);

        return display;
    }
}
