package de.hsweingarten.dapro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Entity for the car Table in the car_rental Database
 */
@Entity
@Table(name = "car", schema = "", catalog = "car_rental")
public class CarEntity implements Serializable {
    private Integer id;
    private String licensePlateNumber;
    private Integer mileage;
    private Date supervision;
    private Date purchase;
    private CarModelEntity carModel;
    private Collection<CommodateEntity> commodates;

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
     * Get License Plate Number
     *
     * @return
     */
    @Basic
    @Column(name = "license_plate_number")
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    /**
     * Set License Plate Number
     *
     * @param licensePlateNumber
     */
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    /**
     * Get Mileage
     *
     * @return
     */
    @Basic
    @Column(name = "mileage")
    public Integer getMileage() {
        return mileage;
    }

    /**
     * Set Mileage
     *
     * @param mileage
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * Get Supervision
     *
     * @return
     */
    @Basic
    @Column(name = "supervision")
    public Date getSupervision() {
        return supervision;
    }

    /**
     * Set Supervision
     *
     * @param supervision
     */
    public void setSupervision(Date supervision) {
        this.supervision = supervision;
    }

    /**
     * Get Purchase
     *
     * @return
     */
    @Basic
    @Column(name = "purchase")
    public Date getPurchase() {
        return purchase;
    }

    /**
     * Set Purchase
     *
     * @param purchase
     */
    public void setPurchase(Date purchase) {
        this.purchase = purchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (carModel != null ? !carModel.equals(carEntity.carModel) : carEntity.carModel != null) return false;
        if (id != null ? !id.equals(carEntity.id) : carEntity.id != null) return false;
        if (licensePlateNumber != null ? !licensePlateNumber.equals(carEntity.licensePlateNumber) : carEntity.licensePlateNumber != null)
            return false;
        if (mileage != null ? !mileage.equals(carEntity.mileage) : carEntity.mileage != null) return false;
        if (purchase != null ? !purchase.equals(carEntity.purchase) : carEntity.purchase != null) return false;
        if (supervision != null ? !supervision.equals(carEntity.supervision) : carEntity.supervision != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (licensePlateNumber != null ? licensePlateNumber.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        result = 31 * result + (supervision != null ? supervision.hashCode() : 0);
        result = 31 * result + (purchase != null ? purchase.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
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
     * Get Commodate Entities
     *
     * @return Collection of Commodate Entites
     */
    @OneToMany(mappedBy = "car")
    public Collection<CommodateEntity> getCommodates() {
        return commodates;
    }

    /**
     * Set Commodate Entities
     *
     * @param commodates Collection of Commodate Entities
     */
    public void setCommodates(Collection<CommodateEntity> commodates) {
        this.commodates = commodates;
    }
}
