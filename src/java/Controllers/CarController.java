package controllers;

import business.Logics.BrandNewVehicle;
import business.Logics.BrandNewVehicleEJB;
import business.Logics.CarEJB;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

@Named
@SessionScoped

public class CarController implements Serializable{
    
    @EJB private CarEJB carEJB;
    @EJB private BrandNewVehicleEJB brandnewvehicleEJB;

    private BrandNewVehicle brandnewvehicle = new BrandNewVehicle();
    private String referenceNumber= new String();   
    
    private void flash(String message, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, message, ""));
    }
    
    private void flash(String message) {
        this.flash(message, FacesMessage.SEVERITY_INFO);
    }
    
    public String CreateBrandNewVehicle() {
        brandnewvehicle = brandnewvehicleEJB.createBrandnew(brandnewvehicle);
        return "listBrandNewVehicles.xhtml";
    }  

    public BrandNewVehicle getBrandnewvehicle() {
        return brandnewvehicle;
    }
  
}