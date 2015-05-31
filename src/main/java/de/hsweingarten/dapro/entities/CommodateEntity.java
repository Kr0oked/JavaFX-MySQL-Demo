package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity for the commodate Table in the car_rental Database
 */
@Entity
@Table(name = "commodate", schema = "", catalog = "car_rental")
public class CommodateEntity implements Serializable {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private Integer startMileage;
    private Integer endMileage;
    private Float price;
    private String payed;
    private CarEntity car;
    private CustomerEntity customer;

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
     * Get Start
     *
     * @return
     */
    @Basic
    @Column(name = "start")
    public Timestamp getStart() {
        return start;
    }

    /**
     * Set Start
     *
     * @param start
     */
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /**
     * Get End
     *
     * @return
     */
    @Basic
    @Column(name = "end")
    public Timestamp getEnd() {
        return end;
    }

    /**
     * Set End
     *
     * @param end
     */
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /**
     * Get Start Mileage
     *
     * @return
     */
    @Basic
    @Column(name = "start_mileage")
    public Integer getStartMileage() {
        return startMileage;
    }

    /**
     * Set Start Mileage
     *
     * @param startMileage
     */
    public void setStartMileage(Integer startMileage) {
        this.startMileage = startMileage;
    }

    /**
     * Get End Mileage
     *
     * @return
     */
    @Basic
    @Column(name = "end_mileage")
    public Integer getEndMileage() {
        return endMileage;
    }

    /**
     * Set End Mileage
     *
     * @param endMileage
     */
    public void setEndMileage(Integer endMileage) {
        this.endMileage = endMileage;
    }

    /**
     * Get Price
     *
     * @return
     */
    @Basic
    @Column(name = "price")
    public Float getPrice() {
        return price;
    }

    /**
     * Set Price
     *
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Get Payed
     *
     * @return
     */
    @Basic
    @Column(name = "payed")
    public String getPayed() {
        return payed;
    }

    /**
     * Set Payed
     *
     * @param payed
     */
    public void setPayed(String payed) {
        this.payed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodateEntity that = (CommodateEntity) o;

        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (endMileage != null ? !endMileage.equals(that.endMileage) : that.endMileage != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (payed != null ? !payed.equals(that.payed) : that.payed != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (startMileage != null ? !startMileage.equals(that.startMileage) : that.startMileage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (startMileage != null ? startMileage.hashCode() : 0);
        result = 31 * result + (endMileage != null ? endMileage.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (payed != null ? payed.hashCode() : 0);
        return result;
    }

    /**
     * Get Car Entity
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "ID")
    public CarEntity getCar() {
        return car;
    }

    /**
     * Set Car Entity
     *
     * @param carByCarId
     */
    public void setCar(CarEntity carByCarId) {
        this.car = carByCarId;
    }

    /**
     * Get Customer Entity
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Set Customer Entity
     *
     * @param customer
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
