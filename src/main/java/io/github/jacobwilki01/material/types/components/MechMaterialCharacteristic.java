package io.github.jacobwilki01.material.types.components;

import lombok.Getter;

public enum MechMaterialCharacteristic {
    METALLIC("metallic"),
    SHINY("shiny"),;

    @Getter
    private final String jsonName;

    private MechMaterialCharacteristic(String jsonName) {
        this.jsonName = jsonName;
    }
}
