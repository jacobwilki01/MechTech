package io.github.jacobwilki01.material.makeup;

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

        public MechMaterialMakeupProperty(int count, MechMaterial material) {
            this.count = count;
            this.material = material;
            this.complex = count > 1 && this.material.isComplexMakeup();
        }

        /**
         * Returns this property's tooltip.
         */
        public String getTooltip() {
            return (complex ? "(" : "") +
                    material.getAbbreviation() +
                   (complex ? ")" : "") +
                   (count > 1 ? toSubscript(count) : "");
        }

        /**
         * Converts a integer value to a subscript-formatted string.
         */
        private String toSubscript(int count) {
            String countStr = Integer.toString(count);
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < countStr.length(); i++) {
                switch (countStr.charAt(i)) {
                    case '0' -> result.append("₀");
                    case '1' -> result.append("₁");
                    case '2' -> result.append("₂");
                    case '3' -> result.append("₃");
                    case '4' -> result.append("₄");
                    case '5' -> result.append("₅");
                    case '6' -> result.append("₆");
                    case '7' -> result.append("₇");
                    case '8' -> result.append("₈");
                    case '9' -> result.append("₉");
                    default -> result.append("<UNK>");
                }
            }

            return result.toString();
        }
    }
}
