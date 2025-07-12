package io.github.jacobwilki01.datagen.item_color;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class ItemColorProvider extends ModelProvider<ItemColorBuilder> {
    public ItemColorProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, "item", ItemColorBuilder::new, existingFileHelper);
    }

    public ItemColorBuilder withExistingParent(String name, ResourceLocation parent, long color) {
        ItemColorBuilder builder = super.withExistingParent(name, parent.getPath());

        builder.setColor(color);

        return super.withExistingParent(name, parent);
    }

    @Override
    public String getName() {
        return "MechTech:ItemColorProvider";
    }
}
