package business.Logics;

import jakarta.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "BrandNewVehicle.findAll", query = "SELECT bn FROM BrandNewVehicle bn"),
	@NamedQuery(name = "BrandNewVehicle.findREF", query = "SELECT bn FROM BrandNewVehicle bn where bn.referenceNumber= :preferenceNumber"),
})
public class BrandNewVehicle extends Car {
    private static final long serialVersionUID = 1L;
    
    private Integer warranty;
    private Integer extendingWarranty;
    private String  roadSideAssistancePackage;

    public BrandNewVehicle() {
    }

    public BrandNewVehicle(Integer warranty, Integer extendingWarranty, String roadSideAssistancePackage, Long id, String make, String model, String referenceNumber, String driveType, String color, String transmission, String engine, String fuelType, Integer doors, Integer seats, Integer price, Integer carCount) {
        super(id, make, model, referenceNumber, driveType, color, transmission, engine, fuelType, doors, seats, price, carCount);
        this.warranty = warranty;
        this.extendingWarranty = extendingWarranty;
        this.roadSideAssistancePackage = roadSideAssistancePackage;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Integer getExtendingWarranty() {
        return extendingWarranty;
    }

    public void setExtendingWarranty(Integer extendingWarranty) {
        this.extendingWarranty = extendingWarranty;
    }

    public String getRoadSideAssistancePackage() {
        return roadSideAssistancePackage;
    }

    public void setRoadSideAssistancePackage(String roadSideAssistancePackage) {
        this.roadSideAssistancePackage = roadSideAssistancePackage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BrandNewVehicle)) {
            return false;
        }
        BrandNewVehicle other = (BrandNewVehicle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "assignment2.Brandnew[ id=" + id + " ]";
    }
    
}
