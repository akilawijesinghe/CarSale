package Controllers;

import models.BrandNewVehicle;
import EJB.BrandNewVehicleEJB;
import EJB.CarEJB;
import EJB.UsedVehicleEJB;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.UsedVehicle;

/**
 * Controller for managing customers.
 *
 * @author : akila wijesinghe - 12194813
 */
@Named
@SessionScoped

public class CarController implements Serializable {

    @EJB
    private CarEJB carEJB;
    @EJB
    private BrandNewVehicleEJB brandnewvehicleEJB;
    @EJB
    private UsedVehicleEJB usedvehicleEJB;

    private BrandNewVehicle brandnewvehicle = new BrandNewVehicle();
    private UsedVehicle usedvehicle = new UsedVehicle();
    private String referenceNumber = new String();

    private List<BrandNewVehicle> brandNewCarList = new ArrayList<BrandNewVehicle>();
    private List<BrandNewVehicle> brandNewCarListByRef = new ArrayList<BrandNewVehicle>();

    private List<UsedVehicle> usedCarList = new ArrayList<UsedVehicle>();
    private List<UsedVehicle> usedCarListByRef = new ArrayList<UsedVehicle>();

    public String doCreateBrandNewVehicle() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if (ctx.getMessageList().size() != 0) {
            return null;
        }

        try {
            brandnewvehicle = brandnewvehicleEJB.createBrandnew(brandnewvehicle);
            brandNewCarList = brandnewvehicleEJB.findBrandNew();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the brand new car : " + brandnewvehicle.getMake() + " " + brandnewvehicle.getModel(), null));
            this.brandnewvehicle = new BrandNewVehicle();
            return "index.xhtml";
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brand new car hasn't been created", e.getMessage()));
        }
        return null;

    }

    public String displayBrandNewByREF(String referenceNumber) {
        brandNewCarListByRef = brandnewvehicleEJB.findBrandNewByRef(referenceNumber);
        return "searchList.xhtml";
    }

    public String doSearchBrandNewVehicleByREF() {
        brandNewCarListByRef = brandnewvehicleEJB.findBrandNewByRef(referenceNumber);
        this.referenceNumber = new String();
        return "searchList.xhtml";
    }

    public BrandNewVehicle getBrandnewvehicle() {
        return brandnewvehicle;
    }

    public void setBrandnewvehicle(BrandNewVehicle brandnewvehicle) {
        this.brandnewvehicle = brandnewvehicle;
    }

    public List<BrandNewVehicle> getBrandNewCarList() {
        brandNewCarList = brandnewvehicleEJB.findBrandNew();
        return brandNewCarList;
    }

    public void setBrandNewCarList(List<BrandNewVehicle> brandNewCarList) {
        this.brandNewCarList = brandNewCarList;
    }

    public List<BrandNewVehicle> getBrandNewCarListByRef() {
        return brandNewCarListByRef;
    }

    public void setBrandNewCarListByRef(List<BrandNewVehicle> brandNewCarListByRef) {
        this.brandNewCarListByRef = brandNewCarListByRef;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String doCreateUsedVehicle() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if (ctx.getMessageList().size() != 0) {
            return null;
        }

        try {
            usedvehicle.setCarCount(1);
            usedvehicle = usedvehicleEJB.createUsed(usedvehicle);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the used car : " + usedvehicle.getMake() + " " + usedvehicle.getModel(), null));
            this.usedvehicle = new UsedVehicle();
            usedCarList = usedvehicleEJB.findUsed();
            return "index.xhtml";
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Used car hasn't been created", e.getMessage()));
        }
        return null;

    }

    public String displayUsedByREF(String referenceNumber) {
        usedCarListByRef = usedvehicleEJB.findUsedByRef(referenceNumber);
        return "searchList.xhtml";
    }

    public String doSearchUsedVehicleByREF() {
        usedCarListByRef = usedvehicleEJB.findUsedByRef(referenceNumber);
        this.referenceNumber = new String();
        return "searchList.xhtml";
    }

    public UsedVehicle getUsedvehicle() {
        return usedvehicle;
    }

    public void setUsedvehicle(UsedVehicle usedvehicle) {
        this.usedvehicle = usedvehicle;
    }

    public List<UsedVehicle> getUsedCarList() {
        usedCarList = usedvehicleEJB.findUsed();
        return usedCarList;
    }

    public void setUsedCarList(List<UsedVehicle> usedCarList) {
        this.usedCarList = usedCarList;
    }

    public List<UsedVehicle> getUsedCarListByRef() {
        return usedCarListByRef;
    }

    public void setUsedCarListByRef(List<UsedVehicle> usedCarListByRef) {
        this.usedCarListByRef = usedCarListByRef;
    }

}
