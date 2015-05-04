package de.hsweingarten.dapro.command;

import de.hsweingarten.dapro.service.IDatabaseService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;

/**
 * Command for loading the values for filtering the Car Models
 */
public class LoadColumnValuesCommand extends Service<HashMap<String, Collection<String>>> {

    @Inject
    public IDatabaseService databaseService;

    /**
     * Creates the Task
     *
     * @return The created Task
     */
    @Override
    protected Task<HashMap<String, Collection<String>>> createTask() {
        return new Task<HashMap<String, Collection<String>>>() {
            @Override
            protected HashMap<String, Collection<String>> call() throws Exception {
                return databaseService.getColumnValues();
            }
        };
    }
}
