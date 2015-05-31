package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity for the car_type Table in the car_rental Database
 */
@Entity
@Table(name = "car_type", schema = "", catalog = "car_rental")
public class CarTypeEntity implements Serializable {
    private Integer id;
    private String type;
    private Collection<CarModelEntity> carModels;
    private Collection<FeaturesEntity> features;

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
     * Get Type
     *
     * @return
     */
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    /**
     * Set Type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarTypeEntity that = (CarTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    /**
     * Get Car Models
     *
     * @return Collection of Car Model Entities
     */
    @OneToMany(mappedBy = "carType")
    public Collection<CarModelEntity> getCarModels() {
        return carModels;
    }

    /**
     * Set Car Models
     *
     * @param carModels Collection of Car Model Entities
     */
    public void setCarModels(Collection<CarModelEntity> carModels) {
        this.carModels = carModels;
    }

    /**
     * Get Features
     *
     * @return Collection of Feature Entities
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "link_car_type_features", catalog = "car_rental", joinColumns = {
            @JoinColumn(name = "features_id", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "car_type_id", nullable = false, updatable = false) }
    )
    public Collection<FeaturesEntity> getFeatures() {
        return features;
    }

    /**
     * Set Features
     *
     * @param features Collection of Features Entities
     */
    public void setFeatures(Collection<FeaturesEntity> features) {
        this.features = features;
    }
}
