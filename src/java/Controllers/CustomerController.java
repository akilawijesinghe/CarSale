/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import EJB.CustomerEJB;
import Models.BrandNewVehicle;
import Models.Customer;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;

/**
 * Controller for managing customers.
 *
 * @author : kasun eranda - 12216898
 */
@Named(value = "customerController")
@SessionScoped
@Getter
@Setter

public class CustomerController implements Serializable {

    @EJB
    private CustomerEJB customerEJB;

    private Customer customer = new Customer();
    private Customer customerNew = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();
    private List<Customer> customerSearchList = new ArrayList<Customer>();

    private String searchKey = new String();

    private int customerId;

    /**
     * Gets the current customer.
     *
     * @return The current customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the current customer.
     *
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Creates a new customer.
     *
     * @return null to stay on the same page.
     */
    public String doCreateCustomer() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (ctx.getMessageList().size() != 0) {
            return null;
        }

        try {
            customer = customerEJB.createCustomer(customer);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A new customer has been created", null));
            this.customer = new Customer();
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer hasn't been created", e.getMessage()));
        }
        return "index.xhtml";
    }

    /**
     * Get all the customers
     */
    public List<Customer> getCustomerList() {
        customerList = customerEJB.findCustomers();
        return customerList;
    }

    /**
     * Search customers
     *
     * @return Customer
     */
    public String getCustomerByName() {
        customerSearchList = customerEJB.searchCustomer(searchKey);
        this.searchKey = new String();
        return "searchList.xhtml";
    }

    /**
     * Return the search result
     *
     * @return List<Customer>
     */
    public List<Customer> getCustomerSearchList() {
        return customerSearchList;
    }
    
    public String displayCustomerByID(int id) {
        this.customerNew = customerEJB.findACustomer(id);
        return "detail.xhtml";
    }

    /**
     * Display a customer details
     *
     * @param id customer id
     * @return String View
     */
    public String doViewDetails(int id) {
        System.out.println("Here banana");
        System.out.println(id);
        this.customerNew = customerEJB.findACustomer(id);
        return "detail.xhtml";
    }

}
