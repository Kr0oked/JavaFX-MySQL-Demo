package de.hsweingarten.dapro.vo;

import javafx.beans.property.*;

/**
 * Model of a Car for holding the data from the Database
 */
public class CarModelVO {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty description = new SimpleStringProperty(this, "description");
    private StringProperty manufacturer = new SimpleStringProperty(this, "manufacturer");
    private StringProperty carType = new SimpleStringProperty(this, "carType");
    private IntegerProperty seats = new SimpleIntegerProperty(this, "seats");
    private IntegerProperty kW = new SimpleIntegerProperty(this, "kW");
    private StringProperty fuel = new SimpleStringProperty(this, "fuel");
    private FloatProperty pricePerDay = new SimpleFloatProperty(this, "pricePerDay");
    private FloatProperty pricePerKM = new SimpleFloatProperty(this, "pricePerKM");
    private IntegerProperty axes = new SimpleIntegerProperty(this, "axes");
    private IntegerProperty loadVolume = new SimpleIntegerProperty(this, "loadVolume");
    private IntegerProperty loadCapacity = new SimpleIntegerProperty(this, "loadCapacity");
    private StringProperty driverLicense = new SimpleStringProperty(this, "driverLicense");

    /**
     * Get ID
     *
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Get ID Property
     *
     * @return
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Get Description
     *
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Get Description Property
     *
     * @return
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Set Description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Get Manufacturer Name
     *
     * @return
     */
    public String getManufacturer() {
        return manufacturer.get();
    }

    /**
     * Get Manufacturer Name Property
     *
     * @return
     */
    public StringProperty manufacturerProperty() {
        return manufacturer;
    }

    /**
     * Set Manufacturer Name
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    /**
     * Get Car Type
     *
     * @return
     */
    public String getCarType() {
        return carType.get();
    }

    /**
     * Get Car Type Property
     *
     * @return
     */
    public StringProperty carTypeProperty() {
        return carType;
    }

    /**
     * Set Car Type
     *
     * @param carType
     */
    public void setCarType(String carType) {
        this.carType.set(carType);
    }

    /**
     * Get number of Seats
     *
     * @return
     */
    public int getSeats() {
        return seats.get();
    }

    /**
     * Get number of Seats Property
     *
     * @return
     */
    public IntegerProperty seatsProperty() {
        return seats;
    }

    /**
     * Set number of Seats
     *
     * @param seats
     */
    public void setSeats(int seats) {
        this.seats.set(seats);
    }

    /**
     * Get kW
     *
     * @return
     */
    public int getKW() {
        return kW.get();
    }

    /**
     * Get kW Property
     *
     * @return
     */
    public IntegerProperty kWProperty() {
        return kW;
    }

    /**
     * Set kW
     *
     * @param kW
     */
    public void setKW(int kW) {
        this.kW.set(kW);
    }

    /**
     * Get Fuel Type
     *
     * @return
     */
    public String getFuel() {
        return fuel.get();
    }

    /**
     * Get Fuel Type Property
     *
     * @return
     */
    public StringProperty fuelProperty() {
        return fuel;
    }

    /**
     * Set Fuel Type
     *
     * @param fuel
     */
    public void setFuel(String fuel) {
        this.fuel.set(fuel);
    }

    /**
     * Get Price per Day
     *
     * @return
     */
    public float getPricePerDay() {
        return pricePerDay.get();
    }

    /**
     * Get Price per Day Property
     *
     * @return
     */
    public FloatProperty pricePerDayProperty() {
        return pricePerDay;
    }

    /**
     * Set Price per Day
     *
     * @param pricePerDay
     */
    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay.set(pricePerDay);
    }

    /**
     * Get Price per kM
     *
     * @return
     */
    public float getPricePerKM() {
        return pricePerKM.get();
    }

    /**
     * Get Price per kM Property
     *
     * @return
     */
    public FloatProperty pricePerKMProperty() {
        return pricePerKM;
    }

    /**
     * Set Price per kM
     *
     * @param pricePerKM
     */
    public void setPricePerKM(float pricePerKM) {
        this.pricePerKM.set(pricePerKM);
    }

    /**
     * Get number of Axes
     *
     * @return
     */
    public int getAxes() {
        return axes.get();
    }

    /**
     * Get number of Axes Property
     *
     * @return
     */
    public IntegerProperty axesProperty() {
        return axes;
    }

    /**
     * Set number of Axes
     *
     * @param axes
     */
    public void setAxes(int axes) {
        this.axes.set(axes);
    }

    /**
     * Get Load Volume
     *
     * @return
     */
    public int getLoadVolume() {
        return loadVolume.get();
    }

    /**
     * Get Load Volume Property
     *
     * @return
     */
    public IntegerProperty loadVolumeProperty() {
        return loadVolume;
    }

    /**
     * Set Load Volume Property
     *
     * @param loadVolume
     */
    public void setLoadVolume(int loadVolume) {
        this.loadVolume.set(loadVolume);
    }

    /**
     * Get Load Capacity
     *
     * @return
     */
    public int getLoadCapacity() {
        return loadCapacity.get();
    }

    /**
     * Get Load Capacity Property
     *
     * @return
     */
    public IntegerProperty loadCapacityProperty() {
        return loadCapacity;
    }

    /**
     * Set Load Capacity
     *
     * @param loadCapacity
     */
    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity.set(loadCapacity);
    }

    /**
     * Get Driver License
     *
     * @return
     */
    public String getDriverLicense() {
        return driverLicense.get();
    }

    /**
     * Get Driver License Property
     *
     * @return
     */
    public StringProperty driverLicenseProperty() {
        return driverLicense;
    }

    /**
     * Set Driver License
     *
     * @param driverLicense
     */
    public void setDriverLicense(String driverLicense) {
        this.driverLicense.set(driverLicense);
    }
}
