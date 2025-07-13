package io.github.jacobwilki01.material;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.component.MechMaterialCharacteristic;
import io.github.jacobwilki01.material.makeup.MechMaterialMakeup;
import io.github.jacobwilki01.material.makeup.MechMaterialMakeup.MechMaterialMakeupProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.lang3.function.TriFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.jacobwilki01.material.component.MechMaterialItemComponent.*;

public class MaterialRegistry {
    public static final DeferredRegister.Blocks BLOCK_REGISTRY = DeferredRegister.createBlocks(MechTech.MODID);
    public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(MechTech.MODID);

    public static final List<MechMaterial> MATERIALS = new ArrayList<>();

    // Metals
    public static final MechMaterial ALUMINUM;
    public static final MechMaterial BRONZE;
    public static final MechMaterial COPPER;
    public static final MechMaterial GOLD;
    public static final MechMaterial IRON;
    public static final MechMaterial NETHERITE;
    public static final MechMaterial TIN;

    // Gems
    public static final MechMaterial AMETHYST;
    public static final MechMaterial DIAMOND;
    public static final MechMaterial EMERALD;
    public static final MechMaterial RUBY;

// Minecraft gems.
//    public static final MechMinecraftGem DIAMOND = new MechMinecraftGem("diamond", "C", 0xff4aedd9L,
//            Items.DIAMOND, Blocks.DIAMOND_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);
//    public static final MechMinecraftGem AMETHYST = new MechMinecraftGem("amethyst", "SiO\u2082", 0xffcca4f4L,
//            Items.AMETHYST_SHARD, Blocks.AMETHYST_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);
//    public static final MechMinecraftGem EMERALD = new MechMinecraftGem("emerald", "Be\u2083Al\u2082SiO\u2086", 0xff17dd62L,
//            Items.EMERALD, Blocks.EMERALD_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);
//
//    // MechTech metals.
//    public static final MechMaterial TIN = new MechMetal("tin", "Sn", 0xffebe8d8L,
//            BlockBehaviour.Properties.of(), null, MechMetalComponent.GEAR, MechMetalComponent.ROD)
//            .setCharacteristic(MechMaterialCharacteristic.METALLIC);
//    public static final MechMaterial ALUMINUM = new MechMetal("aluminum", "Al", 0xffe6fffeL,
//            BlockBehaviour.Properties.of(), null, MechMetalComponent.GEAR, MechMetalComponent.ROD)
//            .setCharacteristic(MechMaterialCharacteristic.METALLIC);

    static {
        // Register basic materials.
        ALUMINUM = new MechMaterialBuilder()
                .init("aluminum", "Al", null)
                .registerColor(0xffe6fffeL)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(INGOT, ROD, DUST, NUGGET)
                .build();
        COPPER = new MechMaterialBuilder()
                .init("copper", "Cu", null)
                .registerColor(0xffe77c56L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaItem(INGOT, Items.COPPER_INGOT)
                .registerVanillaBlock(Blocks.COPPER_BLOCK)
                .registerItemComponent(ROD, DUST, NUGGET)
                .build();
        GOLD = new MechMaterialBuilder()
                .init("gold", "Au", null)
                .registerColor(0xfffff300L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.GOLD_BLOCK)
                .registerVanillaItem(INGOT, Items.GOLD_INGOT)
                .registerVanillaItem(NUGGET, Items.GOLD_NUGGET)
                .registerItemComponent(ROD, DUST)
                .build();
        IRON = new MechMaterialBuilder()
                .init("iron", "Fe", null)
                .registerColor(0xfff7f7f7L)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerVanillaBlock(Blocks.IRON_BLOCK)
                .registerVanillaItem(INGOT, Items.IRON_INGOT)
                .registerVanillaItem(NUGGET, Items.IRON_NUGGET)
                .registerItemComponent(ROD, DUST)
                .build();
        NETHERITE = new MechMaterialBuilder()
                .init("netherite", "Nt", null)
                .registerColor(0xff5a575aL)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerVanillaBlock(Blocks.NETHERITE_BLOCK)
                .registerVanillaItem(INGOT, Items.NETHERITE_INGOT)
                .registerItemComponent(ROD, DUST, NUGGET)
                .build();
        TIN = new MechMaterialBuilder()
                .init("tin", "Sn", null)
                .registerColor(0xffebe8d8L)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(INGOT, ROD, DUST, NUGGET)
                .build();

        // Register Gems
        // TODO use material makeup on these gems.
        AMETHYST = new MechMaterialBuilder()
                .init("amethyst", "SiO₂", null)
                .registerColor(0xffcca4f4L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.AMETHYST_BLOCK)
                .registerVanillaItem(GEM, Items.AMETHYST_SHARD)
                .registerItemComponent(DUST, NUGGET)
                .build();
        DIAMOND = new MechMaterialBuilder()
                .init("diamond", "C", null)
                .registerColor(0xff4aedd9L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.DIAMOND_BLOCK)
                .registerVanillaItem(GEM, Items.DIAMOND)
                .registerItemComponent(DUST, NUGGET)
                .build();
        EMERALD = new MechMaterialBuilder()
                .init("emerald", "Be₃Al₂SiO₆", null)
                .registerColor(0xff17dd62L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.EMERALD_BLOCK)
                .registerVanillaItem(GEM, Items.EMERALD)
                .registerItemComponent(DUST, NUGGET)
                .build();
        RUBY = new MechMaterialBuilder()
                .init("ruby", "Al₂O₃", null)
                .registerColor(0xffe30f00L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(GEM, DUST, NUGGET)
                .build();

        // Register Alloys
        MechMaterialMakeupProperty copperProperty = new MechMaterialMakeupProperty(3, COPPER);
        MechMaterialMakeupProperty tinProperty = new MechMaterialMakeupProperty(1, TIN);
        BRONZE = new MechMaterialBuilder()
                .init("bronze", null, new MechMaterialMakeup(copperProperty, tinProperty))
                .registerColor(0xffce8946L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(INGOT, ROD, DUST, NUGGET)
                .build();
    }

    public static void registerCreativeTabs(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        for (MechMaterial mechMaterial : MATERIALS) {
            mechMaterial.registerCreativeTab(output);
        }
    }


    public static void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                        BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        for (MechMaterial mechMaterial : MATERIALS) {
            mechMaterial.registerLanguage(itemLangProvider, blockLangProvider);
        }
    }


    public static void registerItemModels(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                          Function<String, ResourceLocation> mcModelLocation) {
        for (MechMaterial mechMaterial : MATERIALS) {
            mechMaterial.registerItemModel(itemModelProvider, mcModelLocation);
        }
    }
}
