package io.github.jacobwilki01.creative_tab;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.material.MaterialRegistry;
import io.github.jacobwilki01.material.component.MechMaterialItemComponent;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MechTech.MODID);

    public static final Supplier<CreativeModeTab> MATERIAL_TAB = CREATIVE_TABS.register("materials",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("MechTech Materials"))
                    .icon(() -> new ItemStack(MaterialRegistry.TIN.getItem(MechMaterialItemComponent.INGOT)))
                    .displayItems(MaterialRegistry::registerCreativeTabs)
                    .build()
            );
}
