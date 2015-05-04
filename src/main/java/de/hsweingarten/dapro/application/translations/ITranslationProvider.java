package de.hsweingarten.dapro.application.translations;

/**
 * Interface of ResourceBundleTranslationProvider for Dependency Injection
 */
public interface ITranslationProvider {

    /**
     * Returns the Translation of the specified key
     *
     * @param key Identifier of the String
     * @return Translation of the specified key
     */
    String getString(String key);
}
