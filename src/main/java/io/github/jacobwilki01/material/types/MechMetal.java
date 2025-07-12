package io.github.jacobwilki01.material.types;

import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.MaterialRegistry;
import io.github.jacobwilki01.material.MechMaterial;
import io.github.jacobwilki01.material.form.MechMaterialBlock;
import io.github.jacobwilki01.material.form.MechMaterialItem;
import io.github.jacobwilki01.material.types.components.MechMetalComponent;
import io.github.jacobwilki01.material.types.makeup.IMechMaterialMakeup;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MechMetal extends MechMaterial {
    /**
     * The metal's ingot form.
     */
    @Getter
    private final DeferredItem<Item> ingot;

    /**
     * The metal's nugget form.
     */
    @Getter
    private final DeferredItem<Item> nugget;

    /**
     * The metal's plate form.
     */
    @Getter
    private final DeferredItem<Item> plate;

    /**
     * The metal's block form.
     */
    @Getter
    private final DeferredBlock<Block> block;

    /**
     * The metal's gear form.
     */
    @Getter
    private Optional<DeferredItem<Item>> gear;

    /**
     * The metal's rod form.
     */
    @Getter
    private Optional<DeferredItem<Item>> rod;

    public MechMetal(String materialName, String abbreviation, long color, BlockBehaviour.Properties properties,
                     IMechMaterialMakeup makeup, MechMetalComponent... componentTypes) {
        super(materialName, abbreviation, color, makeup);

        this.ingot = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_ingot",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
        this.nugget = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_nugget",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
        this.plate = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_plate",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
        this.block = MaterialRegistry.BLOCK_REGISTRY.register(MOD_PREFIX + materialName + "_block",
                () -> new MechMaterialBlock(properties, getAbbreviation()));

        MaterialRegistry.ITEM_REGISTRY.registerSimpleBlockItem(block);

        for (MechMetalComponent componentType : componentTypes)
            registerComponentItem(componentType);
    }

    @Override
    public Item getMaterialItem() {
        return this.ingot.asItem();
    }

    /**
     * Registers additional, optional component items for the given metal.
     * @param componentType The flag for what type of component is being added.
     */
    private void registerComponentItem(MechMetalComponent componentType) {
        DeferredItem<Item> componentItem = MaterialRegistry.ITEM_REGISTRY.register(
                MOD_PREFIX + this.getName() + componentType.getTypeName(),
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));

        switch (componentType) {
            case GEAR -> this.gear = Optional.of(componentItem);
            case ROD -> this.rod = Optional.of(componentItem);
        }
    }

    /**
     * Returns whether this metal contains a certain optional component item.
     */
    public boolean hasComponent(MechMetalComponent flag) {
        return switch (flag) {
            case GEAR -> this.gear.isPresent();
            case ROD -> this.rod.isPresent();
        };
    }

    @Override
    public void registerCreativeTab(CreativeModeTab.Output output) {
        super.registerCreativeTab(output);

        output.accept(ingot.get());
        output.accept(nugget.get());
        output.accept(plate.get());
        output.accept(block.get());
        if (gear.isPresent())
            output.accept(gear.get());
        if (rod.isPresent())
            output.accept(rod.get());
    }

    @Override
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        super.registerLanguage(itemLangProvider, blockLangProvider);

        itemLangProvider.accept(ingot, MechTechLangProvider.toSentenceCase(getName()) + " Ingot");
        itemLangProvider.accept(nugget, MechTechLangProvider.toSentenceCase(getName()) + " Nugget");
        itemLangProvider.accept(plate, MechTechLangProvider.toSentenceCase(getName()) + " Plate");
        blockLangProvider.accept(block, MechTechLangProvider.toSentenceCase(getName()) + " Block");
        if (gear.isPresent())
            itemLangProvider.accept(gear.get(), MechTechLangProvider.toSentenceCase(getName()) + " Gear");
        if (rod.isPresent())
            itemLangProvider.accept(rod.get(), MechTechLangProvider.toSentenceCase(getName()) + " Rod");
    }

    @Override
    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        super.registerItemModel(itemModelProvider, mcModelLocation);

        itemModelProvider.apply(ingot.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/ingot");
        itemModelProvider.apply(nugget.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/nugget");
        itemModelProvider.apply(plate.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/plate");

        if (gear.isPresent())
            itemModelProvider.apply(gear.get().getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/gear");
        if (rod.isPresent())
            itemModelProvider.apply(rod.get().getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/rod");
    }
}
