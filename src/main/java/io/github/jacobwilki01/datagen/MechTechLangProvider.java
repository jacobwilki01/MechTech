package io.github.jacobwilki01.datagen;

import io.github.jacobwilki01.MechTech;
import io.github.jacobwilki01.material.MaterialRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MechTechLangProvider extends LanguageProvider {
    public MechTechLangProvider(PackOutput output) {
        super(output, MechTech.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        MaterialRegistry.registerLanguage(this::addItem, this::addBlock);
    }

    public static String toSentenceCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Convert the entire string to lowercase
        String lowerCaseString = input.toLowerCase();

        // Capitalize the first character
        char firstChar = lowerCaseString.charAt(0);
        char capitalizedFirstChar = Character.toUpperCase(firstChar);

        // Combine the capitalized first character with the rest of the string
        return capitalizedFirstChar + lowerCaseString.substring(1);
    }
}
