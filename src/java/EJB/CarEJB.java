package EJB;

import Models.Car;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

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
    //retrives list of all cars
    public List<Car> findCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }
    
    public void updateCar(Car car) {
        em.merge(car);
    }
}