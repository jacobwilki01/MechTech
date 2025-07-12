package io.github.jacobwilki01.datagen;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.datagen.item_color.ItemColorProvider;
import io.github.jacobwilki01.material.MaterialRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MechTechItemModelProvider extends ItemColorProvider {
    public MechTechItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MechTech.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        MaterialRegistry.registerItemModels(this::withExistingParent, this::mcLoc);
    }
}
