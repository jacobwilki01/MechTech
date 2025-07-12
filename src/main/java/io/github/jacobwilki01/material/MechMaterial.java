package io.github.jacobwilki01.material;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.datagen.MechTechLangProvider;
import io.github.jacobwilki01.datagen.item_color.ItemColorBuilder;
import io.github.jacobwilki01.material.form.MechMaterialItem;
import io.github.jacobwilki01.material.types.components.MechMaterialCharacteristic;
import io.github.jacobwilki01.material.types.makeup.IMechMaterialMakeup;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class MechMaterial {
    /**
     * The prefix for all registered components for this material.
     */
    protected static final String MOD_PREFIX = "mech_";

    /**
     * The material's name.
     */
    @Getter
    private final String name;

    /**
     * The material's abbreviation.
     */
    @Getter
    private String abbreviation;

    /**
     * The color of the material.
     */
    @Getter
    private final long color;

    /**
     * The material's dust form.
     */
    @Getter
    private final DeferredItem<Item> dust;

    /**
     * The material's chemical makeup.
     */
    @Getter
    private IMechMaterialMakeup makeup;

    /**
     * The material's characteristic
     */
    @Getter
    private MechMaterialCharacteristic characteristic;

    protected MechMaterial(String materialName, String abbreviation, long color, IMechMaterialMakeup makeup) {
        this.name = materialName;
        this.color = color;

        if (makeup != null)
            this.abbreviation = makeup.getTooltip();
        else
            this.abbreviation = abbreviation;

        this.makeup = makeup;

        this.dust = MaterialRegistry.ITEM_REGISTRY.register(
                MOD_PREFIX + materialName + "_dust", () -> new MechMaterialItem(new Item.Properties(), this.abbreviation)
        );

        MechTech.LOGGER.info("Registered MechMaterial {}", materialName);
    }

    /**
     * Returns the primary item form of the material.
     */
    public abstract Item getMaterialItem();

    /**
     * Registers all items within the given creative tab.
     */
    public void registerCreativeTab(CreativeModeTab.Output output) {
        output.accept(dust.get());
    }

    /**
     * Registers all items with a language file.
     */
    public void registerLanguage(BiConsumer<Supplier<? extends Item>, String> itemLangProvider,
                                 BiConsumer<Supplier<? extends Block>, String> blockLangProvider) {
        itemLangProvider.accept(dust, MechTechLangProvider.toSentenceCase(getName()) + " Dust");
    }


    public void registerItemModel(TriFunction<String, ResourceLocation, Long, ItemColorBuilder> itemModelProvider,
                                  Function<String, ResourceLocation> mcModelLocation) {
        itemModelProvider.apply(dust.getId().toString(), mcModelLocation.apply("item/generated"), color)
                .texture("layer0", "item/dust_" + getCharacteristic().getJsonName());
    }

    /**
     * Sets the characteristic attribute, returns and instance.
     */
    public abstract <T extends MechMaterial> T setCharacteristic(MechMaterialCharacteristic characteristic);

    protected void setCharacteristicAttribute(MechMaterialCharacteristic characteristic) {
        this.characteristic = characteristic;
    }
}
