package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity for the car_model Table in the car_rental Database
 */
@Entity
@Table(name = "car_model", schema = "", catalog = "car_rental")
public class CarModelEntity implements Serializable {
    private Integer id;
    private String description;
    private String manufacturer;
    private Integer seats;
    private Integer kw;
    private String fuel;
    private Float pricePerDay;
    private Float pricePerKm;
    private Integer axes;
    private Integer loadVolume;
    private Integer loadCapacity;
    private String driverLicense;
    private Collection<CarEntity> cars;
    private CarTypeEntity carType;
    private Collection<ReservationEntity> reservations;

    /**
     * Get ID
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get Description
     *
     * @return
     */
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Set Description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get Manufacturer
     *
     * @return
     */
    @Basic
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Set Manufacturer
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Get Seats
     *
     * @return
     */
    @Basic
    @Column(name = "seats")
    public Integer getSeats() {
        return seats;
    }

    /**
     * Set Seats
     *
     * @param seats
     */
    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    /**
     * Get Kw
     *
     * @return
     */
    @Basic
    @Column(name = "kw")
    public Integer getKw() {
        return kw;
    }

    /**
     * Set Kw
     *
     * @param kw
     */
    public void setKw(Integer kw) {
        this.kw = kw;
    }

    /**
     * Get Fuel
     *
     * @return
     */
    @Basic
    @Column(name = "fuel")
    public String getFuel() {
        return fuel;
    }

    /**
     * Set Fuel
     *
     * @param fuel
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * Get Price per Day
     *
     * @return
     */
    @Basic
    @Column(name = "price_per_day")
    public Float getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Set Price per Day
     *
     * @param pricePerDay
     */
    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Get Price per Km
     *
     * @return
     */
    @Basic
    @Column(name = "price_per_km")
    public Float getPricePerKm() {
        return pricePerKm;
    }

    /**
     * Set Price per Km
     *
     * @param pricePerKm
     */
    public void setPricePerKm(Float pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    /**
     * Get Axes
     *
     * @return
     */
    @Basic
    @Column(name = "axes")
    public Integer getAxes() {
        return axes;
    }

    /**
     * Set Axes
     *
     * @param axes
     */
    public void setAxes(Integer axes) {
        this.axes = axes;
    }

    /**
     * Get Load Volume
     *
     * @return
     */
    @Basic
    @Column(name = "load_volume")
    public Integer getLoadVolume() {
        return loadVolume;
    }

    /**
     * Set Load Volume
     *
     * @param loadVolume
     */
    public void setLoadVolume(Integer loadVolume) {
        this.loadVolume = loadVolume;
    }

    /**
     * Get Load Capacity
     *
     * @return
     */
    @Basic
    @Column(name = "load_capacity")
    public Integer getLoadCapacity() {
        return loadCapacity;
    }

    /**
     * Set Load Capacity
     *
     * @param loadCapacity
     */
    public void setLoadCapacity(Integer loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    /**
     * Get Driver License
     *
     * @return
     */
    @Basic
    @Column(name = "driver_license")
    public String getDriverLicense() {
        return driverLicense;
    }

    /**
     * Set Driver License
     *
     * @param driverLicense
     */
    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarModelEntity that = (CarModelEntity) o;

        if (axes != null ? !axes.equals(that.axes) : that.axes != null) return false;
        if (carType != null ? !carType.equals(that.carType) : that.carType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (driverLicense != null ? !driverLicense.equals(that.driverLicense) : that.driverLicense != null)
            return false;
        if (fuel != null ? !fuel.equals(that.fuel) : that.fuel != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (kw != null ? !kw.equals(that.kw) : that.kw != null) return false;
        if (loadCapacity != null ? !loadCapacity.equals(that.loadCapacity) : that.loadCapacity != null) return false;
        if (loadVolume != null ? !loadVolume.equals(that.loadVolume) : that.loadVolume != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (pricePerDay != null ? !pricePerDay.equals(that.pricePerDay) : that.pricePerDay != null) return false;
        if (pricePerKm != null ? !pricePerKm.equals(that.pricePerKm) : that.pricePerKm != null) return false;
        if (seats != null ? !seats.equals(that.seats) : that.seats != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (kw != null ? kw.hashCode() : 0);
        result = 31 * result + (fuel != null ? fuel.hashCode() : 0);
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (pricePerKm != null ? pricePerKm.hashCode() : 0);
        result = 31 * result + (axes != null ? axes.hashCode() : 0);
        result = 31 * result + (loadVolume != null ? loadVolume.hashCode() : 0);
        result = 31 * result + (loadCapacity != null ? loadCapacity.hashCode() : 0);
        result = 31 * result + (driverLicense != null ? driverLicense.hashCode() : 0);
        return result;
    }

    /**
     * Get Car Entities
     *
     * @return
     */
    @OneToMany(mappedBy = "carModel")
    public Collection<CarEntity> getCars() {
        return cars;
    }

    /**
     * Set Car Entities
     *
     * @param cars Collection of Car Entities
     */
    public void setCars(Collection<CarEntity> cars) {
        this.cars = cars;
    }

    /**
     * Get Car Type Entities
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "car_type_id", referencedColumnName = "ID")
    public CarTypeEntity getCarType() {
        return carType;
    }

    /**
     * Set Car Type Entities
     *
     * @param carType Collection of Car Type Entities
     */
    public void setCarType(CarTypeEntity carType) {
        this.carType = carType;
    }

    /**
     * Get Reservation Entities
     *
     * @return
     */
    @OneToMany(mappedBy = "carModel")
    public Collection<ReservationEntity> getReservations() {
        return reservations;
    }

    /**
     * Set Reservation Entities
     *
     * @param reservations Collection of Reservation Entities
     */
    public void setReservations(Collection<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
