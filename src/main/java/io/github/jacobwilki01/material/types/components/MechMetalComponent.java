package io.github.jacobwilki01.material.types.components;

import lombok.Getter;

public enum MechMetalComponent {
    GEAR("_gear"),
    ROD("_rod");

    /**
     * The type name, for creating types of items.
     */
    @Getter
    private final String typeName;

    private MechMetalComponent(String typeName) {
        this.typeName = typeName;
    }
}
