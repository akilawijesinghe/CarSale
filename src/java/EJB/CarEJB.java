package EJB;

import models.Car;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * EJB for managing cars.
 * @author : akila wijesinghe - 12194813
 */
@Stateless
public class CarEJB
{
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;
    
    public Car find(Long id) {
        return this.em.find(Car.class, id);
    }
}