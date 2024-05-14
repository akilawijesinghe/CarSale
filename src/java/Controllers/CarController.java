package Controllers;



import business.Logics.BrandNewVehicle;
import business.Logics.BrandNewVehicleEJB;
import business.Logics.CarEJB;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
//import java.io.Serializable;
//import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Named;
//import jakarta.faces.bean.RequestScoped;
//import jakarta.faces.bean.SessionScoped;
//import jakarta.enterprise.context.RequestScoped;
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
//        brandnewList = brandnewEJB.findBrandnews();
//        flash("Successfully created the brand new car: " + brandnew.make+" "+brandnew.model);
//		this.brandnew=new BrandNewVehicle();
        return "listBrandNewVehicles.xhtml";
    }  

    public BrandNewVehicle getBrandnewvehicle() {
        return brandnewvehicle;
    }
  
}