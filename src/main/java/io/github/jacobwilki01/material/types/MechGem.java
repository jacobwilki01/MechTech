package io.github.jacobwilki01.material.types;

import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.MaterialRegistry;
import io.github.jacobwilki01.material.MechMaterial;
import io.github.jacobwilki01.material.form.MechMaterialBlock;
import io.github.jacobwilki01.material.form.MechMaterialItem;
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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MechGem extends MechMaterial {
    /**
     * The gem form.
     */
    @Getter
    private final DeferredItem<Item> gem;

    /**
     * The metal's nugget form.
     */
    @Getter
    private final DeferredItem<Item> nugget;

    /**
     * The gem's block form.
     */
    @Getter
    private final DeferredBlock<Block> block;

    public MechGem(String materialName, String abbreviation, long color, BlockBehaviour.Properties properties,
                   IMechMaterialMakeup makeup) {
        super(materialName, abbreviation, color, makeup);

        this.gem = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_gem",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
        this.nugget = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_nugget",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
        this.block = MaterialRegistry.BLOCK_REGISTRY.register(MOD_PREFIX + materialName + "_block",
                () -> new MechMaterialBlock(properties, getAbbreviation()));

        MaterialRegistry.ITEM_REGISTRY.registerSimpleBlockItem(block);
    }

    @Override
    public Item getMaterialItem() {
        return this.gem.asItem();
    }

    @Override
    public void registerCreativeTab(CreativeModeTab.Output output) {
        super.registerCreativeTab(output);

        output.accept(this.gem);
        output.accept(this.block);
    }

    @Override
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        super.registerLanguage(itemLangProvider, blockLangProvider);

        itemLangProvider.accept(gem, MechTechLangProvider.toSentenceCase(getName()));
        itemLangProvider.accept(nugget, MechTechLangProvider.toSentenceCase(getName()) + " Nugget");
        blockLangProvider.accept(block, MechTechLangProvider.toSentenceCase(getName()) + " Block");
    }

    @Override
    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        super.registerItemModel(itemModelProvider, mcModelLocation);

        itemModelProvider.apply(gem.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/gem");
        itemModelProvider.apply(nugget.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/nugget");
    }
}
