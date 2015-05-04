package de.hsweingarten.dapro.application.guice;

import com.google.inject.Injector;
import javafx.concurrent.Service;

import javax.inject.Inject;

/**
 * Provides Instances of Commands which are Services
 */
public class CommandProvider implements ICommandProvider {

    @Inject
    public Injector injector;

    /**
     * Provides an Instance of a specified Command
     *
     * @param type Class of a Command
     * @param <T> The Command has to extend Service
     * @return  Instance of the specified Command
     */
    public <T extends Service> T get(Class<T> type) {
        return injector.getInstance(type);
    }
}
