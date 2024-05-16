/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package EJB;

import java.util.List;

import Models.Customer;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * EJB for managing customers.
 * @author : kasun eranda - 12216898
 */
@Stateless
@LocalBean
public class CustomerEJB {

    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    /**
     * Finds all customers.
     * @return A list of customers.
     */
    public List<Customer> findCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        return query.getResultList();
    }

    /**
     * Creates a new customer.
     * @param customer The customer to create.
     * @return The created customer.
     */
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }
}