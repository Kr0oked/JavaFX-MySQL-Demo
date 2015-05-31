package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for the driver_license Table in the car_rental Database
 */
@Entity
@Table(name = "driver_license", schema = "", catalog = "car_rental")
public class DriverLicenseEntity implements Serializable {
    private String clazz;
    private Integer id;
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
     * Get Class
     *
     * @return
     */
    @Basic
    @Column(name = "class")
    public String getClazz() {
        return clazz;
    }

    /**
     * Set Class
     *
     * @param clazz
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverLicenseEntity that = (DriverLicenseEntity) o;

        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
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
