package business.Logics;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless
public class CarEJB
{
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;
    
    public Car find(Long id) {
        return this.em.find(Car.class, id);
    }
}