package de.hsweingarten.dapro.application.guice;

import javafx.concurrent.Service;

/**
 * Interface of CommandProvider for Dependency Injection
 */
public interface ICommandProvider {

    /**
     * Provides an Instance of a specified Command
     *
     * @param type Class of a Command
     * @param <T> The Command has to extend Service
     * @return  Instance of the specified Command
     */
    <T extends Service> T get(Class<T> type);
}
