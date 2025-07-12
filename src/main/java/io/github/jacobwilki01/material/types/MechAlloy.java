package io.github.jacobwilki01.material.types;

import io.github.jacobwilki01.material.types.components.MechMetalComponent;
import io.github.jacobwilki01.material.types.makeup.IMechMaterialMakeup;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MechAlloy extends MechMetal {
    public MechAlloy(String materialName, long color, BlockBehaviour.Properties properties,
                     IMechMaterialMakeup makeup, MechMetalComponent... componentTypes) {
        super(materialName, null, color, properties, makeup, componentTypes);
    }
}
