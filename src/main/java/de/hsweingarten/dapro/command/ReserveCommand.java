package de.hsweingarten.dapro.command;

import de.hsweingarten.dapro.service.IDatabaseService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * Command for reserving a car
 */
public class ReserveCommand extends Service<String> {

    @Inject
    public IDatabaseService databaseService;
    public int customerId;
    public int carModelId;
    public LocalDate startDate;
    public LocalDate endDate;

    /**
     * Creates the Task
     *
     * @return The created Task
     */
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                return databaseService.reserveCar(customerId, carModelId, startDate, endDate);
            }
        };
    }
}
