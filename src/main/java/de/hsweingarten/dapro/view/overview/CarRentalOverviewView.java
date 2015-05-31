package de.hsweingarten.dapro.view.overview;

import de.hsweingarten.dapro.application.translations.ITranslationProvider;
import de.hsweingarten.dapro.exceptions.ModelSelectionException;
import de.hsweingarten.dapro.exceptions.NumberSelectionException;
import de.hsweingarten.dapro.vo.CarModelVO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A View which gives an Overview about the rentable Cars.
 * The selection can be filtered by values from ComboBoxes.
 */
public class CarRentalOverviewView implements ICarRentalOverviewView {

    private BorderPane view;
    private TableView<CarModelVO> carTable;
    private ComboBox<String> descriptionCmbBox;
    private ComboBox<String> manufacturerCmbBox;
    private ComboBox<String> carTypeCmbBox;
    private ComboBox<String> seatsCmbBox;
    private ComboBox<String> fuelCmbBox;
    private NumberTextField customerTxtField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button reservationBtn;

    @Inject
    public ITranslationProvider translationProvider;

    @Inject
    public ICarRentalOverviewMediator mediator;

    /**
     * Updates the CarList
     *
     * @param cars Car Models which will replace the existing ones in the list
     */
    @Override
    public void updateCarList(Collection<CarModelVO> cars) {
        carTable.getItems().setAll(cars);
    }

    /**
     * Updates the DescriptionValuesList
     *
     * @param descriptions Strings which will replace the existing ones in the list
     */
    @Override
    public void updateDescriptionValuesList(Collection<String> descriptions) {
        descriptionCmbBox.getItems().setAll(addEmptyStringToBeginning(descriptions));
    }

    /**
     * Updates the ManufacturerValuesList
     *
     * @param manufacturers Strings which will replace the existing ones in the list
     */
    @Override
    public void updateManufacturerValuesList(Collection<String> manufacturers) {
        manufacturerCmbBox.getItems().setAll(addEmptyStringToBeginning(manufacturers));
    }

    /**
     * Updates the CarTypeValuesList
     *
     * @param carTypes Strings which will replace the existing ones in the list
     */
    @Override
    public void updateCarTypeValuesList(Collection<String> carTypes) {
        carTypeCmbBox.getItems().setAll(addEmptyStringToBeginning(carTypes));
    }

    /**
     * Updates the SeatsValuesList
     *
     * @param seats Strings which will replace the existing ones in the list
     */
    @Override
    public void updateSeatsValuesList(Collection<String> seats) {
        seatsCmbBox.getItems().setAll(addEmptyStringToBeginning(seats));
    }

    /**
     * Updates the FuelValuesList
     *
     * @param fuels Strings which will replace the existing ones in the list
     */
    @Override
    public void updateFuelValuesList(Collection<String> fuels) {
        fuelCmbBox.getItems().setAll(addEmptyStringToBeginning(fuels));
    }

    /**
     * Shows an Information Dialog with a specified Text
     *
     * @param text
     */
    @Override
    public void showInformationDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Information");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Returns a HashMap with Filters for filtering the Car Models
     *
     * @return HashMap with Filters
     */
    @Override
    public HashMap<String, String> getFilters() {
        HashMap<String, String> map = new HashMap<>();
        map.put("description", emptyStringToNull(descriptionCmbBox.getValue()));
        map.put("manufacturer", emptyStringToNull(manufacturerCmbBox.getValue()));
        map.put("carType", emptyStringToNull(carTypeCmbBox.getValue()));
        map.put("seats", emptyStringToNull(seatsCmbBox.getValue()));
        map.put("fuel", emptyStringToNull(fuelCmbBox.getValue()));
        return map;
    }

    /**
     * Returns the picked Customer ID
     *
     * @return
     */
    @Override
    public int getCustomerId() {
        try {
            String text = customerTxtField.getText();

            if (text.equals("")) {
                throw new NumberSelectionException();
            }

            return Integer.parseInt(customerTxtField.getText());
        }
        catch (NumberFormatException exception) {
            throw new NumberSelectionException();
        }
    }

    /**
     * Returns the ID of the selected Car Model
     *
     * @return
     */
    @Override
    public int getSelectedCarModelId() throws ModelSelectionException {
        CarModelVO selectedModel = carTable.getSelectionModel().getSelectedItem();
        if (selectedModel != null) {
            return selectedModel.getId();
        }

        throw new ModelSelectionException();
    }

    /**
     * Returns the picked Start Date
     *
     * @return
     */
    @Override
    public LocalDate getStartDate() {
        return startDatePicker.getValue();
    }

    /**
     * Returns the picked End Date
     *
     * @return
     */
    @Override
    public LocalDate getEndDate() {
        return endDatePicker.getValue();
    }

    /**
     * Returns the View
     *
     * @return
     */
    public Pane getView() {
        if (view == null) {
            view = new BorderPane();

            carTable = new TableView();
            carTable.getColumns().addAll(getColumns());

            GridPane bottom = new GridPane();
            bottom.setHgap(6);
            bottom.setVgap(2);

            Label descriptionLabel = new Label(translationProvider.getString("carRentalOverview.label.description"));
            Label manufacturerLabel = new Label(translationProvider.getString("carRentalOverview.label.manufacturer"));
            Label carTypeLabel = new Label(translationProvider.getString("carRentalOverview.label.carType"));
            Label seatsLabel = new Label(translationProvider.getString("carRentalOverview.label.seats"));
            Label fuelLabel = new Label(translationProvider.getString("carRentalOverview.label.fuel"));

            bottom.add(descriptionLabel, 1, 1);
            bottom.add(manufacturerLabel, 1, 2);
            bottom.add(carTypeLabel, 1, 3);
            bottom.add(seatsLabel, 1, 4);
            bottom.add(fuelLabel, 1, 5);

            Label userLabel = new Label("User");
            Label startLabel = new Label("Start Date");
            Label endLabel = new Label("End Date");
            Label reservationLabel = new Label("Reservation");

            bottom.add(userLabel, 3, 1);
            bottom.add(startLabel, 3, 2);
            bottom.add(endLabel, 3, 3);
            bottom.add(reservationLabel, 3, 4);

            ComboBoxChangedHandler comboBoxChangedHandler = new ComboBoxChangedHandler();
            descriptionCmbBox = new ComboBox<>();
            descriptionCmbBox.setOnAction(comboBoxChangedHandler);
            descriptionCmbBox.setMinWidth(200);
            manufacturerCmbBox = new ComboBox<>();
            manufacturerCmbBox.setOnAction(comboBoxChangedHandler);
            manufacturerCmbBox.setMinWidth(200);
            carTypeCmbBox = new ComboBox<>();
            carTypeCmbBox.setOnAction(comboBoxChangedHandler);
            carTypeCmbBox.setMinWidth(200);
            seatsCmbBox = new ComboBox<>();
            seatsCmbBox.setOnAction(comboBoxChangedHandler);
            seatsCmbBox.setMinWidth(200);
            fuelCmbBox = new ComboBox<>();
            fuelCmbBox.setOnAction(comboBoxChangedHandler);
            fuelCmbBox.setMinWidth(200);

            bottom.add(descriptionCmbBox, 2, 1);
            bottom.add(manufacturerCmbBox, 2, 2);
            bottom.add(carTypeCmbBox, 2, 3);
            bottom.add(seatsCmbBox, 2, 4);
            bottom.add(fuelCmbBox, 2, 5);

            customerTxtField = new NumberTextField();
            customerTxtField.setMinWidth(200);
            startDatePicker = new DatePicker();
            startDatePicker.setMinWidth(200);
            endDatePicker = new DatePicker();
            endDatePicker.setMinWidth(200);
            reservationBtn = new Button("Reservation");
            reservationBtn.setMinWidth(200);
            reservationBtn.setOnAction(new ButtonChangedHandler());

            bottom.add(customerTxtField, 4, 1);
            bottom.add(startDatePicker, 4, 2);
            bottom.add(endDatePicker, 4, 3);
            bottom.add(reservationBtn, 4, 4);

            view.setCenter(carTable);
            view.setBottom(bottom);

            mediator.init();
        }

        return view;
    }

    /**
     * Get the Columns for the TableView of CarModels
     *
     * @return
     */
    private Collection<TableColumn<CarModelVO, String>> getColumns() {
        Collection<TableColumn<CarModelVO, String>> columns = new ArrayList<>();

        TableColumn columnId = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.id"));
        columnId.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("id"));
        columns.add(columnId);

        TableColumn columnDescription = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.description"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("description"));
        columns.add(columnDescription);

        TableColumn columnManufacturer = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.manufacturer"));
        columnManufacturer.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("manufacturer"));
        columns.add(columnManufacturer);

        TableColumn columnCarType = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.carType"));
        columnCarType.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("carType"));
        columns.add(columnCarType);

        TableColumn columnSeats = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.seats"));
        columnSeats.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("seats"));
        columns.add(columnSeats);

        TableColumn columnKW = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.kW"));
        columnKW.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("kW"));
        columns.add(columnKW);

        TableColumn columnFuel = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.fuel"));
        columnFuel.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("fuel"));
        columns.add(columnFuel);

        TableColumn columnPricePerDay = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.pricePerDay"));
        columnPricePerDay.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("pricePerDay"));
        columns.add(columnPricePerDay);

        TableColumn columnPricePerKM = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.pricePerKM"));
        columnPricePerKM.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("pricePerKM"));
        columns.add(columnPricePerKM);

        TableColumn columnAxes = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.axes"));
        columnAxes.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("axes"));
        columns.add(columnAxes);

        TableColumn columnLoadVolume = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.loadVolume"));
        columnLoadVolume.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("loadVolume"));
        columns.add(columnLoadVolume);

        TableColumn columnLoadCapacity = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.loadCapacity"));
        columnLoadCapacity.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("loadCapacity"));
        columns.add(columnLoadCapacity);

        TableColumn columnDriverLicense = new TableColumn<CarModelVO, String>(translationProvider.getString("carRentalOverview.column.driverLicense"));
        columnDriverLicense.setCellValueFactory(new PropertyValueFactory<CarModelVO, String>("driverLicense"));
        columns.add(columnDriverLicense);

        return columns;
    }

    /**
     * Adds an empty String to the Beginning of a Collection
     *
     * @param collection
     * @return
     */
    private Collection<String> addEmptyStringToBeginning(Collection<String> collection) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("");
        linkedList.addAll(collection);

        return linkedList;
    }

    /**
     * Returns Null if text is an empty String
     *
     * @param text
     * @return
     */
    private String emptyStringToNull(String text) {
        if (text != null) {
            if (text.equals("")) {
                return null;
            }
        }
        return text;
    }

    /**
     * Handler which gets called when a ComboBox get changed
     */
    private class ComboBoxChangedHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mediator.updateCarList();
        }
    }

    private class ButtonChangedHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            mediator.reserveCar();
        }
    }

    /**
     * Text Field for Numbers only
     */
    private class NumberTextField extends TextField {
        @Override
        public void replaceText(int start, int end, String text) {
            if (validate(text)) {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {
            return ("".equals(text) || text.matches("[0-9]"));
        }
    }
}
