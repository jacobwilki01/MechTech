package io.github.jacobwilki01.material.types.makeup;

import io.github.jacobwilki01.material.MechMaterial;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MechMaterialMakeup implements IMechMaterialMakeup {
    /**
     * The individual makeup properties.
     */
    private final List<MechMaterialMakeupProperty> makeupProperties;

    public MechMaterialMakeup(MechMaterialMakeupProperty... properties) {
        makeupProperties = new ArrayList<>(Arrays.asList(properties));
    }

    @Override
    public String getTooltip() {
        StringBuilder builder = new StringBuilder();
        for (MechMaterialMakeupProperty property : makeupProperties)
            builder.append(property.getTooltip());

        return builder.toString();
    }

    @Override
    public boolean isComplex() {
        for (MechMaterialMakeupProperty property : makeupProperties) {
            if (property.isComplex())
                return true;
        }

        return false;
    }

    public static class MechMaterialMakeupProperty {
        /**
         * The count of molecules for this property.
         */
        private int count;

        /**
         * The material for this property.
         */
        private MechMaterial material;

        /**
         * Whether the material makeup is "complex".
         */
        @Getter
        private boolean complex;

        protected MechMaterialMakeupProperty(int count, MechMaterial material) {
            this.count = count;
            this.material = material;
            this.complex = count > 1 || this.material.getMakeup().isComplex();
        }

        /**
         * Returns this property's tooltip.
         */
        public String getTooltip() {
            if (complex)
                return "(" + material.getAbbreviation() + ")" + count;
            else
                return material.getAbbreviation() + count;
        }
    }
}
