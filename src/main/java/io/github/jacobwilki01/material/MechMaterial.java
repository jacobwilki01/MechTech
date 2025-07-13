package io.github.jacobwilki01.material;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.component.MechMaterialCharacteristic;
import io.github.jacobwilki01.material.component.MechMaterialItemComponent;
import io.github.jacobwilki01.material.makeup.IMechMaterialMakeup;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.function.TriFunction;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MechMaterial {
    /**
     * The material's name.
     */
    @Getter
    private final String name;

    /**
     * The material's abbreviation.
     */
    @Getter
    @Setter
    private String abbreviation;

    /**
     * The color of the material.
     */
    @Getter
    @Setter
    private long color;

    /**
     * The material's chemical makeup.
     */
    @Setter
    private IMechMaterialMakeup makeup;

    /**
     * The material's characteristic
     */
    @Getter
    @Setter
    private MechMaterialCharacteristic characteristic;

    /**
     * The component map.
     */
    private final Map<MechMaterialItemComponent, DeferredItem<Item>> componentItemMap = new EnumMap<>(MechMaterialItemComponent.class);

    /**
     * The vanilla item map.
     */
    private final Map<MechMaterialItemComponent, Item> vanillaItemMap = new EnumMap<>(MechMaterialItemComponent.class);

    /**
     * The material's block form.
     */
    @Getter
    @Setter
    private Optional<DeferredBlock<Block>> block;

    /**
     * The material's block item form.
     */
    @Getter
    @Setter
    private Optional<DeferredItem<BlockItem>> blockItem;

    /**
     * The material's vanilla block form.
     */
    @Getter
    @Setter
    private Optional<Block> vanillaBlock;

    protected MechMaterial(String materialName, String abbreviation) {
        this.name = materialName;
        this.abbreviation = abbreviation;

      MechTech.LOGGER.info("Registered MechMaterial {}", materialName);
    }

    /**
     * Registers an item of a given component type.
     */
    public void registerComponent(MechMaterialItemComponent component, DeferredItem<Item> item) {
        componentItemMap.put(component, item);
    }

    /**
     * Gets a relevant item reference.
     */
    public Item getItem(MechMaterialItemComponent component) {
        return componentItemMap.get(component).get();
    }

    /**
     * Gets a relevant vanilla item reference.
     */
    public Item getVanillaItem(MechMaterialItemComponent component) {
        return vanillaItemMap.get(component);
    }

    /**
     * Registers a vanilla item of a given component type.
     */
    public void registerVanillaItem(MechMaterialItemComponent component, Item item) {
        vanillaItemMap.put(component, item);
    }

    /**
     * Registers all items within the given creative tab.
     */
    public void registerCreativeTab(CreativeModeTab.Output output) {
        for (DeferredItem<Item> item : componentItemMap.values()) {
            output.accept(item);
        }
    }

    /**
     * Registers all items with a language file.
     */
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        for (Map.Entry<MechMaterialItemComponent, DeferredItem<Item>> entry : componentItemMap.entrySet()) {
            itemLangProvider.accept(entry.getValue(),
                    MechTechLangProvider.toSentenceCase(getName()) + " " + MechTechLangProvider.toSentenceCase(entry.getKey().getLocalizedName()));
        }
    }

    /**
     * Registers all items with a model.
     */
    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        for (Map.Entry<MechMaterialItemComponent, DeferredItem<Item>> entry : componentItemMap.entrySet()) {
            itemModelProvider.apply(entry.getValue().getId().toString(), mcModelLocation.apply("item/generated"), color)
                    .texture("layer0", "item/" + entry.getKey().getLocalizedName() + "_" + getCharacteristic().getJsonName());
        }
    }

    /**
     * Registers a vanilla item with a tooltip.
     */
    public void registerVanillaItemTooltip(ItemTooltipEvent event) {
        Item eventItem = event.getItemStack().getItem();

        for (Item vanillaItem : vanillaItemMap.values()) {
            if (vanillaItem.equals(eventItem)) {
                event.getToolTip().add(Component.literal(getAbbreviation()).withColor(0x8b8b8b));
            }
        }

        if (vanillaBlock.isPresent()) {
            Block block = vanillaBlock.get();
        }
    }

    /**
     * Returns if the material makeup is complex.
     */
    public boolean isComplexMakeup() {
        return makeup != null && makeup.isComplex();
    }
}
