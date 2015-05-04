package de.hsweingarten.dapro.application.translations;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provides Translations from the Resource Bundle
 */
public class ResourceBundleTranslationProvider implements ITranslationProvider {

    private final ResourceBundle bundle;

    public ResourceBundleTranslationProvider() {
        Locale locale = Locale.ENGLISH;
        bundle = ResourceBundle.getBundle("translations.CarRental", locale);
    }

    /**
     * Returns the Translation of the specified key
     *
     * @param key Identifier of the String
     * @return Translation of the specified key
     */
    @Override
    public String getString(String key) {
        return bundle.getString(key);
    }
}
