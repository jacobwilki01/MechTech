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

    // Elements
    public static final MechMaterial OXYGEN;
    public static final MechMaterial SILICON;

    static {
        // Elements
        OXYGEN = new MechMaterialBuilder()
                .init("oxygen", "O")
                .build();
        SILICON = new MechMaterialBuilder()
                .init("silicon", "Si")
                .registerColor(0xff181a4aL)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerItemComponent(DUST)
                .build();

        // Register basic materials.
        ALUMINUM = new MechMaterialBuilder()
                .init("aluminum", "Al")
                .registerColor(0xffe6fffeL)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(INGOT, ROD, DUST, NUGGET)
                .build();
        COPPER = new MechMaterialBuilder()
                .init("copper", "Cu")
                .registerColor(0xffe77c56L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaItem(INGOT, Items.COPPER_INGOT)
                .registerVanillaBlock(Blocks.COPPER_BLOCK)
                .registerItemComponent(ROD, DUST, NUGGET)
                .build();
        GOLD = new MechMaterialBuilder()
                .init("gold", "Au")
                .registerColor(0xfffff300L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.GOLD_BLOCK)
                .registerVanillaItem(INGOT, Items.GOLD_INGOT)
                .registerVanillaItem(NUGGET, Items.GOLD_NUGGET)
                .registerItemComponent(ROD, DUST)
                .build();
        IRON = new MechMaterialBuilder()
                .init("iron", "Fe")
                .registerColor(0xfff7f7f7L)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerVanillaBlock(Blocks.IRON_BLOCK)
                .registerVanillaItem(INGOT, Items.IRON_INGOT)
                .registerVanillaItem(NUGGET, Items.IRON_NUGGET)
                .registerItemComponent(ROD, DUST)
                .build();
        NETHERITE = new MechMaterialBuilder()
                .init("netherite", "Nt")
                .registerColor(0xff5a575aL)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerVanillaBlock(Blocks.NETHERITE_BLOCK)
                .registerVanillaItem(INGOT, Items.NETHERITE_INGOT)
                .registerItemComponent(ROD, DUST, NUGGET)
                .build();
        TIN = new MechMaterialBuilder()
                .init("tin", "Sn")
                .registerColor(0xffebe8d8L)
                .registerCharacteristic(MechMaterialCharacteristic.METALLIC)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(INGOT, ROD, DUST, NUGGET)
                .build();

        // Register Gems
        // TODO use material makeup on these gems.
        AMETHYST = new MechMaterialBuilder()
                .init("amethyst", null)
                .registerColor(0xffcca4f4L)
                .registerChemicalMakeup(new MechMaterialMakeupProperty(1, SILICON), new MechMaterialMakeupProperty(2, OXYGEN))
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.AMETHYST_BLOCK)
                .registerVanillaItem(GEM, Items.AMETHYST_SHARD)
                .registerItemComponent(DUST, NUGGET)
                .build();
        DIAMOND = new MechMaterialBuilder()
                .init("diamond", "C")
                .registerColor(0xff4aedd9L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.DIAMOND_BLOCK)
                .registerVanillaItem(GEM, Items.DIAMOND)
                .registerItemComponent(DUST, NUGGET)
                .build();
        EMERALD = new MechMaterialBuilder()
                .init("emerald", "Be₃Al₂SiO₆")
                .registerColor(0xff17dd62L)
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerVanillaBlock(Blocks.EMERALD_BLOCK)
                .registerVanillaItem(GEM, Items.EMERALD)
                .registerItemComponent(DUST, NUGGET)
                .build();
        RUBY = new MechMaterialBuilder()
                .init("ruby", null)
                .registerColor(0xffe30f00L)
                .registerChemicalMakeup(new MechMaterialMakeupProperty(2, ALUMINUM), new MechMaterialMakeupProperty(3, OXYGEN))
                .registerCharacteristic(MechMaterialCharacteristic.SHINY)
                .registerBlock(BlockBehaviour.Properties.of())
                .registerItemComponent(GEM, DUST, NUGGET)
                .build();

        // Register Alloys
        BRONZE = new MechMaterialBuilder()
                .init("bronze", null)
                .registerColor(0xffce8946L)
                .registerChemicalMakeup(new MechMaterialMakeupProperty(3, COPPER), new MechMaterialMakeupProperty(1, TIN))
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
