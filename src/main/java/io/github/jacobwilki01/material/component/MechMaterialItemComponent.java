package io.github.jacobwilki01.material.component;

import lombok.Getter;

public enum MechMaterialItemComponent {
    DUST("dust"),
    GEAR("gear"),
    GEM("gem"),
    INGOT("ingot"),
    NUGGET("nugget"),
    PLATE("plate"),
    ROD("rod");

    @Getter
    private final String localizedName;

    private MechMaterialItemComponent(String name) {
        this.localizedName = name;
    }
}
