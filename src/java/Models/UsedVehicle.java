package models;

import jakarta.persistence.*;

/**
 * This class presents a usedvehicle database model
 *
 * @author : akila wijesinghe - 12194813
 */
@Entity
@NamedQuery(name = "findAllUsed", query = "SELECT u FROM UsedVehicle u")
@NamedQuery(name = "findUsedByRef", query = "SELECT u FROM UsedVehicle u where u.referenceNumber= :referenceNumber")

public class UsedVehicle extends Car {
    private static final long serialVersionUID = 1L;
    
    private Integer odometer;
    private String regoNo;
    private String regoExpiary;
    private String serviceHistory;
    private String VIN;
    private String carHistory;
    
    public UsedVehicle() {
    }

    public UsedVehicle(Integer odometer, String regoNo, String regoExpiary, String serviceHistory, String VIN, String carHistory, Long id, String make, String model, String referenceNumber, String driveType, String bodyColor, String transmissionType, String engineType, String fuelType, Integer numberOfDoors, Integer numberOfSeats, Integer price, Integer carCount) {
        super(id, make, model, referenceNumber, driveType, bodyColor, transmissionType, engineType, fuelType, numberOfDoors, numberOfSeats, price, carCount);
        this.odometer = odometer;
        this.regoNo = regoNo;
        this.regoExpiary = regoExpiary;
        this.serviceHistory = serviceHistory;
        this.VIN = VIN;
        this.carHistory = carHistory;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public String getRegoNo() {
        return regoNo;
    }

    public void setRegoNo(String regoNo) {
        this.regoNo = regoNo;
    }

    public String getRegoExpiary() {
        return regoExpiary;
    }

    public void setRegoExpiary(String regoExpiary) {
        this.regoExpiary = regoExpiary;
    }

    public String getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(String serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getCarHistory() {
        return carHistory;
    }

    public void setCarHistory(String carHistory) {
        this.carHistory = carHistory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsedVehicle)) {
            return false;
        }
        UsedVehicle other = (UsedVehicle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsedVehicle{" + "id=" + id + '}';
    }
    
}
