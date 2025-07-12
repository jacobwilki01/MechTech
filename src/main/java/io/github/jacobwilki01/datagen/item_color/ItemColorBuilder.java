package io.github.jacobwilki01.datagen.item_color;

import com.google.gson.*;
import io.github.jacobwilki01.datagen.item_color.json.Layer;
import io.github.jacobwilki01.datagen.item_color.json.NeoForgeData;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemColorBuilder extends ModelBuilder<ItemColorBuilder> {
    @Setter
    private long color;

    public ItemColorBuilder(ResourceLocation outputLocation, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();

        Layer layer = new Layer();
        layer.setColor(color);

        NeoForgeData data = new NeoForgeData();
        data.addLayer("0", layer);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(data);

        JsonElement jsonElement = JsonParser.parseString(jsonString);
        json.add("neoforge_data", jsonElement);
        json.addProperty("loader", "neoforge:item_layers");

        return json;
    }
}
