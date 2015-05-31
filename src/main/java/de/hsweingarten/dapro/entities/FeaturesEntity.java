package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity for the features Table in the car_rental Database
 */
@Entity
@Table(name = "features", schema = "", catalog = "car_rental")
public class FeaturesEntity implements Serializable {
    private Integer id;
    private String description;
    private Collection<CarTypeEntity> carTypes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeaturesEntity that = (FeaturesEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    /**
     * Get Car Types
     *
     * @return Collection of Car Type Entities
     */
    @ManyToMany(mappedBy = "features")
    public Collection<CarTypeEntity> getCarTypes() {
        return carTypes;
    }

    /**
     * Set Car Types
     *
     * @param carTypes Collection of Car Type Entities
     */
    public void setCarTypes(Collection<CarTypeEntity> carTypes) {
        this.carTypes = carTypes;
    }
}
