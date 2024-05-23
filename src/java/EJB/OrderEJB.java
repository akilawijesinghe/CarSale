package EJB;

import Models.Car;
import Models.Order;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class OrderEJB {

    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    public List<Order> findOrders() {
        TypedQuery<Order> query = em.createNamedQuery("Order.findAllOrder", Order.class);
        return query.getResultList();
    }

    public List<Order> findOrderById(Long orderid) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByID", Order.class);
        query.setParameter("id", orderid);
        return query.getResultList();
    }
    
    public Order find(Long id) {
        return em.find(Order.class, id);
    }

    public Order createOrder(Order order) {
        em.persist(order);
        return order;
    }

    public void deleteOrderByID(Long orderid) {
        Order deleteOrder = em.find(Order.class, orderid);
        em.remove(deleteOrder);
    }

    public Order mergeCarOrder(Order order) {
        em.merge(order);
        return order;
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
}
