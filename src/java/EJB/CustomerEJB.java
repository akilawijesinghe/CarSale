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
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * EJB for managing customers.
 *
 * @author : kasun eranda - 12216898
 */
@Stateless
@LocalBean
public class CustomerEJB {

    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    /**
     * Finds all customers.
     *
     * @return A list of customers.
     */
    public List<Customer> findCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        return query.getResultList();
    }

    /**
     * Creates a new customer.
     *
     * @param customer The customer to create.
     * @return The created customer.
     */
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    /**
     * Search customers
     *
     * @param name name
     * @return
     */
    public List<Customer> searchCustomer(String name) {
        Query q = em.createNamedQuery("findWithParam");
        q.setParameter("name", name);

        return q.getResultList();
    }

    /**
     * Get a customer by id
     *
     * @param id customer id
     * @return
     */
    public Customer findACustomer(int id) {
        TypedQuery<Customer> query = em.createNamedQuery("findById", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Customer find(Long id) {
        return this.em.find(Customer.class, id);
    }

    public void mergeCustomer(Customer customer) {
        em.merge(customer);
    }
}
