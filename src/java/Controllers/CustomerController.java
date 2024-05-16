/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import EJB.CustomerEJB;
import Models.Customer;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 * Controller for managing customers.
 * @author : kasun eranda - 12216898
 */
@Named(value = "customerController")
@RequestScoped
public class CustomerController implements Serializable {

    @EJB
    private CustomerEJB customerEJB;

    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();

    /**
     * Gets the current customer.
     * @return The current customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the current customer.
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Creates a new customer.
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
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer hasn't been created", e.getMessage()));
        }
        return null;
    }
    
    /**
     * Get all the customers
     */
    public List<Customer> getCustomerList() {
        
	customerList = customerEJB.findCustomers();
	return customerList;
    }
}
