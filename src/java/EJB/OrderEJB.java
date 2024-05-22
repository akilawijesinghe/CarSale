/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EJB;


import Models.Car;
import Models.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class OrderEJB {

    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    public Order createOrder(Order order) {
        em.persist(order);
        return order;
    }
public List<Order> findOrders() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM CustomerOrder o", Order.class);
        return query.getResultList();
    }
public Order findOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public void updateCar(Car car) {
        em.merge(car);
    }

    public void deleteOrder(Order order) {
        if (!em.contains(order)) {
            order = em.merge(order);
        }
        em.remove(order);
    }
    public List<Order> findOrdersByCustomer(int customerId) {
        TypedQuery<Order> query = em.createNamedQuery("CustomerOrder.findByCustomerId", Order.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
      public List<Order> searchOrderByOrderId(int orderId) {
        TypedQuery<Order> query = em.createNamedQuery("findOrderById", Order.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }

  
}

