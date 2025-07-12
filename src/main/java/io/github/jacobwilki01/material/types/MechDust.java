package io.github.jacobwilki01.material.types;

import io.github.jacobwilki01.material.MechMaterial;
import io.github.jacobwilki01.material.types.makeup.IMechMaterialMakeup;
import net.minecraft.world.item.Item;

public class MechDust extends MechMaterial {
    protected MechDust(String materialName, String abbreviation, long color, IMechMaterialMakeup makeup) {
        super(materialName, abbreviation, color, makeup);
    }

    @Override
    public Item getMaterialItem() {
        return this.getDust().asItem();
    }
}
