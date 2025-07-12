package io.github.jacobwilki01.material.types.minecraft;

import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.MaterialRegistry;
import io.github.jacobwilki01.material.MechMaterial;
import io.github.jacobwilki01.material.form.MechMaterialItem;
import io.github.jacobwilki01.material.types.components.MechMaterialCharacteristic;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MechMinecraftGem extends MechMaterial {
    /**
     * The vanilla form of the gem.
     */
    @Getter
    private final Item gem;

    /**
     * The vanilla form of the block.
     */
    @Getter
    private final Block block;

    /**
     * The modded nugget form of the gem.
     */
    @Getter
    private final DeferredItem<Item> nugget;

    public MechMinecraftGem(String materialName, String abbreviation, long color, Item minecraftGem,
                            Block minecraftBlock) {
        super(materialName, abbreviation, color, null);

        this.gem = minecraftGem;
        this.block = minecraftBlock;

        this.nugget = MaterialRegistry.ITEM_REGISTRY.register(MOD_PREFIX + materialName + "_nugget",
                () -> new MechMaterialItem(new Item.Properties(), getAbbreviation()));
    }

    @Override
    public Item getMaterialItem() {
        return this.gem;
    }

    @Override
    public void registerCreativeTab(CreativeModeTab.Output output) {
        super.registerCreativeTab(output);

        output.accept(nugget.get());
    }

    @Override
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        super.registerLanguage(itemLangProvider, blockLangProvider);

        itemLangProvider.accept(nugget, MechTechLangProvider.toSentenceCase(getName()) + " Nugget");
    }

    @Override
    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        super.registerItemModel(itemModelProvider, mcModelLocation);

        itemModelProvider.apply(nugget.getId().toString(), mcModelLocation.apply("item/generated"), getColor())
                .texture("layer0", "item/nugget_" + getCharacteristic().getJsonName());
    }

    public void registerVanillaTooltips(ItemTooltipEvent event) {
        Item eventItem = event.getItemStack().getItem();

        if (eventItem == gem)
            event.getToolTip().add(Component.literal(getAbbreviation()).withColor(0x8b8b8b));
    }

    @Override
    public <T extends MechMaterial> T setCharacteristic(MechMaterialCharacteristic characteristic) {
        super.setCharacteristicAttribute(characteristic);
        return (T) this;
    }
}
