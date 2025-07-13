package io.github.jacobwilki01.material;

import io.github.jacobwilki01.material.component.MechMaterialCharacteristic;
import io.github.jacobwilki01.material.component.MechMaterialItemComponent;
import io.github.jacobwilki01.material.form.MechMaterialBlock;
import io.github.jacobwilki01.material.form.MechMaterialItem;
import io.github.jacobwilki01.material.makeup.IMechMaterialMakeup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Optional;

public class MechMaterialBuilder {
    /**
     * The prefix for all registered components for this material.
     */
    private static final String MOD_PREFIX = "mech";

    /**
     * The underscore character.
     */
    private static final String UNDERSCORE = "_";

    /**
     * The internal material object.
     */
    private MechMaterial material;

    /**
     * Final material build call.
     */
    public MechMaterial build() throws RuntimeException {
        if (material == null) {
            throw new RuntimeException("Material not set");
        }

        return material;
    }

    /**
     * Builds a basic material of the selected type.
     */
    public MechMaterialBuilder init(String name, String abbreviation, IMechMaterialMakeup makeup) {
        material = new MechMaterial(name, abbreviation, makeup);
        MaterialRegistry.MATERIALS.add(material);

        return this;
    }

    /**
     * Register's the material's color.
     */
    public MechMaterialBuilder registerColor(long color) {
        material.setColor(color);

        return this;
    }

    /**
     * Registers a material component.
     */
    public MechMaterialBuilder registerItemComponent(MechMaterialItemComponent... components) {
        for (MechMaterialItemComponent component : components) {
            DeferredItem<Item> componentItem = MaterialRegistry.ITEM_REGISTRY.register(
                    MOD_PREFIX + UNDERSCORE + material.getName() + UNDERSCORE + component.getLocalizedName(),
                    () -> new MechMaterialItem(new Item.Properties(), material.getAbbreviation()));

            material.registerComponent(component, componentItem);
        }

        return this;
    }

    /**
     * Registers a block for the material.
     */
    public MechMaterialBuilder registerBlock(BlockBehaviour.Properties properties) {
        DeferredBlock<Block> block = MaterialRegistry.BLOCK_REGISTRY.register(
                MOD_PREFIX + UNDERSCORE + material.getName() + "_block",
                () -> new MechMaterialBlock(properties, material.getAbbreviation()));

        DeferredItem<BlockItem> blockItem = MaterialRegistry.ITEM_REGISTRY.registerSimpleBlockItem(block);

        material.setBlock(Optional.of(block));
        material.setBlockItem(Optional.of(blockItem));

        return this;
    }

    /**
     * Registers the material's characteristic.
     */
    public MechMaterialBuilder registerCharacteristic(MechMaterialCharacteristic characteristic) {
        material.setCharacteristic(characteristic);

        return this;
    }

    /**
     * Registers any vanilla items with the material.
     */
    public MechMaterialBuilder registerVanillaItem(MechMaterialItemComponent component, Item item) {
        material.registerVanillaItem(component, item);

        return this;
    }

    /**
     * Registers a vanilla block with the material.
     */
    public MechMaterialBuilder registerVanillaBlock(Block block) {
        material.setVanillaBlock(Optional.of(block));

        return this;
    }
}
