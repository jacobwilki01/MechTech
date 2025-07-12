package io.github.jacobwilki01.material;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.types.MechMetal;
import io.github.jacobwilki01.material.types.components.MechMaterialCharacteristic;
import io.github.jacobwilki01.material.types.components.MechMetalComponent;
import io.github.jacobwilki01.material.types.minecraft.MechMinecraftGem;
import io.github.jacobwilki01.material.types.minecraft.MechMinecraftMetal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MaterialRegistry {
    public static final DeferredRegister.Blocks BLOCK_REGISTRY = DeferredRegister.createBlocks(MechTech.MODID);
    public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(MechTech.MODID);

    // Minecraft metals.
    public static final MechMinecraftMetal IRON = new MechMinecraftMetal("iron", "Fe", 0xfff7f7f7L,
            Items.IRON_INGOT, Items.IRON_NUGGET, Blocks.IRON_BLOCK, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.METALLIC);
    public static final MechMinecraftMetal GOLD = new MechMinecraftMetal("gold", "Au", 0xfffff300L,
            Items.GOLD_INGOT, Items.GOLD_NUGGET, Blocks.GOLD_BLOCK, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.SHINY);
    public static final MechMinecraftMetal COPPER = new MechMinecraftMetal("copper", "Cu", 0xffe77c56L,
            Items.COPPER_INGOT, null, Blocks.COPPER_BLOCK, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.SHINY);
    public static final MechMinecraftMetal NETHERITE = new MechMinecraftMetal("netherite", "Nt", 0xff5a575aL,
            Items.NETHERITE_INGOT, null, Blocks.NETHERITE_BLOCK, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.METALLIC);

    // Minecraft gems.
    public static final MechMinecraftGem DIAMOND = new MechMinecraftGem("diamond", "C", 0xff4aedd9L,
            Items.DIAMOND, Blocks.DIAMOND_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);
    public static final MechMinecraftGem AMETHYST = new MechMinecraftGem("amethyst", "SiO\u2082", 0xffcca4f4L,
            Items.AMETHYST_SHARD, Blocks.AMETHYST_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);
    public static final MechMinecraftGem EMERALD = new MechMinecraftGem("emerald", "Be\u2083Al\u2082SiO\u2086", 0xff17dd62L,
            Items.EMERALD, Blocks.EMERALD_BLOCK).setCharacteristic(MechMaterialCharacteristic.SHINY);

    // MechTech metals.
    public static final MechMaterial TIN = new MechMetal("tin", "Sn", 0xffebe8d8L,
            BlockBehaviour.Properties.of(), null, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.METALLIC);
    public static final MechMaterial ALUMINUM = new MechMetal("aluminum", "Al", 0xffe6fffeL,
            BlockBehaviour.Properties.of(), null, MechMetalComponent.GEAR, MechMetalComponent.ROD)
            .setCharacteristic(MechMaterialCharacteristic.METALLIC);

    public static void registerCreativeTabs(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        IRON.registerCreativeTab(output);
        GOLD.registerCreativeTab(output);
        COPPER.registerCreativeTab(output);
        NETHERITE.registerCreativeTab(output);
        DIAMOND.registerCreativeTab(output);
        AMETHYST.registerCreativeTab(output);
        EMERALD.registerCreativeTab(output);
        TIN.registerCreativeTab(output);
        ALUMINUM.registerCreativeTab(output);
    }


    public static void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                        BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        IRON.registerLanguage(itemLangProvider, blockLangProvider);
        GOLD.registerLanguage(itemLangProvider, blockLangProvider);
        COPPER.registerLanguage(itemLangProvider, blockLangProvider);
        NETHERITE.registerLanguage(itemLangProvider, blockLangProvider);
        DIAMOND.registerLanguage(itemLangProvider, blockLangProvider);
        AMETHYST.registerLanguage(itemLangProvider, blockLangProvider);
        EMERALD.registerLanguage(itemLangProvider, blockLangProvider);
        TIN.registerLanguage(itemLangProvider, blockLangProvider);
        ALUMINUM.registerLanguage(itemLangProvider, blockLangProvider);
    }


    public static void registerItemModels(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                          Function<String, ResourceLocation> mcModelLocation) {
        IRON.registerItemModel(itemModelProvider, mcModelLocation);
        GOLD.registerItemModel(itemModelProvider, mcModelLocation);
        COPPER.registerItemModel(itemModelProvider, mcModelLocation);
        NETHERITE.registerItemModel(itemModelProvider, mcModelLocation);
        DIAMOND.registerItemModel(itemModelProvider, mcModelLocation);
        AMETHYST.registerItemModel(itemModelProvider, mcModelLocation);
        EMERALD.registerItemModel(itemModelProvider, mcModelLocation);
        TIN.registerItemModel(itemModelProvider, mcModelLocation);
        ALUMINUM.registerItemModel(itemModelProvider, mcModelLocation);
    }
}
