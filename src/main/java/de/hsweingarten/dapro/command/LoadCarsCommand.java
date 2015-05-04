package de.hsweingarten.dapro.command;

import de.hsweingarten.dapro.service.IDatabaseService;
import de.hsweingarten.dapro.vo.CarModelVO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;

/**
 * Command for loading a filtered list of Car Models
 */
public class LoadCarsCommand extends Service<Collection<CarModelVO>> {

    @Inject
    public IDatabaseService databaseService;
    public HashMap<String, String> filters;

    /**
     * Creates the Task
     *
     * @return The created Task
     */
    @Override
    protected Task<Collection<CarModelVO>> createTask() {
        return new Task<Collection<CarModelVO>>() {
            @Override
            protected Collection<CarModelVO> call() throws Exception {
                return databaseService.getCarList(filters);
            }
        };
    }
}
