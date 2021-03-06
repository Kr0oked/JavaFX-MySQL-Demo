package de.hsweingarten.dapro.view.overview;

import com.google.inject.Inject;
import de.hsweingarten.dapro.application.guice.ICommandProvider;
import de.hsweingarten.dapro.command.LoadCarsCommand;
import de.hsweingarten.dapro.command.LoadColumnValuesCommand;
import de.hsweingarten.dapro.command.ReserveCommand;
import de.hsweingarten.dapro.exceptions.ModelSelectionException;
import de.hsweingarten.dapro.exceptions.NumberSelectionException;
import de.hsweingarten.dapro.vo.CarModelVO;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CarRentalOverviewMediator implements ICarRentalOverviewMediator {

    @Inject
    public ICommandProvider commandProvider;
    @Inject
    public ICarRentalOverviewView view;

    /**
     * Initializes values for the View
     */
    @Override
    public void init() {
        LoadColumnValuesCommand command = commandProvider.get(LoadColumnValuesCommand.class);
        command.setOnSucceeded(new LoadColumnValuesSucceededHandler());
        command.start();

        updateCarList();
    }

    /**
     * Updates the Car List with filters provided from the View
     */
    @Override
    public void updateCarList() {
        LoadCarsCommand command = commandProvider.get(LoadCarsCommand.class);
        command.setOnSucceeded(new LoadCarsSucceededHandler());
        command.filters = view.getFilters();
        command.start();
    }

    /**
     * Reserves a Car with the values provided from the View
     */
    @Override
    public void reserveCar() {
        try {
            ReserveCommand command = commandProvider.get(ReserveCommand.class);
            command.setOnSucceeded(new ReserveSucceededHandler());
            command.customerId = view.getCustomerId();
            command.carModelId = view.getSelectedCarModelId();

            LocalDate startDate = view.getStartDate();
            LocalDate endDate = view.getEndDate();

            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                view.showInformationDialog("Invalid Date Input");
            }
            else {
                command.startDate = startDate;
                command.endDate = endDate;
                command.start();
            }
        }
        catch (ModelSelectionException exception) {
            view.showInformationDialog(exception.getMessage());
        }
        catch (NumberSelectionException exception) {
            view.showInformationDialog(exception.getMessage());
        }
    }

    /**
     * Handler for updating the View when a LoadColumnValuesCommand succeeded
     */
    private class LoadColumnValuesSucceededHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent event) {
            HashMap<String, ObservableList<String>> map = (HashMap<String, ObservableList<String>>)event.getSource().getValue();

            for(Map.Entry<String, ObservableList<String>> e : map.entrySet()) {
                if (e.getKey().equals("description")) {
                    view.updateDescriptionValuesList(e.getValue());
                }
                else if (e.getKey().equals("manufacturer")) {
                    view.updateManufacturerValuesList(e.getValue());
                }
                else if (e.getKey().equals("carType")) {
                    view.updateCarTypeValuesList(e.getValue());
                }
                else if (e.getKey().equals("seats")) {
                    view.updateSeatsValuesList(e.getValue());
                }
                else if (e.getKey().equals("fuel")) {
                    view.updateFuelValuesList(e.getValue());
                }
            }
        }
    }

    /**
     * Handler for updating the View when a LoadCars Command succeeded
     */
    private class LoadCarsSucceededHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent event) {
            view.updateCarList((Collection<CarModelVO>) event.getSource().getValue());
        }
    }

    private class ReserveSucceededHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent event) {
            view.showInformationDialog((String) event.getSource().getValue());
        }
    }
}
