package io.github.jacobwilki01.datagen.item_color.json;

import java.util.HashMap;
import java.util.Map;

public class NeoForgeData {
    private Map<String, Layer> layers = new HashMap<>();

    public void addLayer(String name, Layer layer) {
        layers.put(name, layer);
    }
}
