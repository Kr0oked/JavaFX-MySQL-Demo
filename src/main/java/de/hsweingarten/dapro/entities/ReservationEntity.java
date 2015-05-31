package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity for the reservation Table in the car_rental Database
 */
@Entity
@Table(name = "reservation", schema = "", catalog = "car_rental")
public class ReservationEntity implements Serializable {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private CarModelEntity carModel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (carModel != null ? !carModel.equals(that.carModel) : that.carModel != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    /**
     * Get Car Model Entity
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "car_model_id", referencedColumnName = "ID")
    public CarModelEntity getCarModel() {
        return carModel;
    }

    /**
     * Set Car Model Entity
     *
     * @param carModel
     */
    public void setCarModel(CarModelEntity carModel) {
        this.carModel = carModel;
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
     * @param customerByCustomerId
     */
    public void setCustomer(CustomerEntity customerByCustomerId) {
        this.customer = customerByCustomerId;
    }
}
