package io.github.jacobwilki01.material.types.minecraft;

import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.MaterialRegistry;
import io.github.jacobwilki01.material.MechMaterial;
import io.github.jacobwilki01.material.form.MechMaterialItem;
import io.github.jacobwilki01.material.types.components.MechMetalComponent;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MechMinecraftMetal extends MechMaterial {
    /**
     * The vanilla item for the metal's ingot form.
     */
    @Getter
    private final Item ingot;

    /**
     * The vanilla item for the metal's nugget form.
     */
    private final Item nugget;

    /**
     * The modded item for the metal's nugget form.
     */
    private DeferredItem<Item> modNugget;

    /**
     * The plate for the metal.
     */
    private final DeferredItem<Item> plate;

    /**
     * The vanilla item for the metal's block form.
     */
    @Getter
    private final Block block;

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

    public MechMinecraftMetal(String materialName, String abbreviation, long color, Item minecraftIngot,
                              Item minecraftNugget, Block minecraftBlock, MechMetalComponent... componentTypes) {
        super(materialName, abbreviation, color, null);

        this.ingot = minecraftIngot;
        this.nugget = minecraftNugget;
        this.block = minecraftBlock;

        if (this.nugget == null)
            this.modNugget = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_nugget",
                    () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));

        this.plate = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_plate",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));

        for (MechMetalComponent componentType : componentTypes)
            registerComponentItem(componentType);
    }

    @Override
    public Item getMaterialItem() {
        return ingot;
    }

    /**
     * Modified getter for the nugget.
     */
    public Item getNugget() {
        if (nugget == null)
            return modNugget.asItem();

        return nugget;
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

    @Override
    public void registerCreativeTab(CreativeModeTab.Output output) {
        super.registerCreativeTab(output);

        if (modNugget != null)
            output.accept(modNugget.get());
        if (rod.isPresent())
            output.accept(rod.get());
        if (gear.isPresent())
            output.accept(gear.get());
    }

    @Override
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        super.registerLanguage(itemLangProvider, blockLangProvider);

        itemLangProvider.accept(plate, MechTechLangProvider.toSentenceCase(getName()) + " Plate");
        if (modNugget != null)
            itemLangProvider.accept(modNugget, MechTechLangProvider.toSentenceCase(getName()) + " Nugget");
        if (gear.isPresent())
            itemLangProvider.accept(gear.get(), MechTechLangProvider.toSentenceCase(getName()) + " Gear");
        if (rod.isPresent())
            itemLangProvider.accept(rod.get(), MechTechLangProvider.toSentenceCase(getName()) + " Rod");
    }

    @Override
    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        super.registerItemModel(itemModelProvider, mcModelLocation);

        itemModelProvider.apply(plate.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/plate");

        if (modNugget != null)
            itemModelProvider.apply(modNugget.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                    .texture("layer0", "item/nugget");

        if (gear.isPresent())
            itemModelProvider.apply(gear.get().getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                    .texture("layer0", "item/gear");
        if (rod.isPresent())
            itemModelProvider.apply(rod.get().getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                    .texture("layer0", "item/rod");
    }
}
