package EJB;

import Models.BrandNewVehicle;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

/**
 * EJB for managing brandnewvehicle.
 * @author : akila wijesinghe - 12194813
 */
@Stateless
public class BrandNewVehicleEJB {
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    public BrandNewVehicle createBrandnew(BrandNewVehicle brandnew) {
        em.persist(brandnew);
        return brandnew;
    }
    
     /**
     * Finds all brand new cars.
     * @return A list of brand new cars.
     */
    public List<BrandNewVehicle> findBrandNew() {
        TypedQuery<BrandNewVehicle> query = em.createNamedQuery("findAllBrandNew", BrandNewVehicle.class);
        return query.getResultList();
    }

    public List<BrandNewVehicle> findBrandNewByRef(String referenceNumber) {
        TypedQuery<BrandNewVehicle> query = em.createNamedQuery("findBrandNewByRef", BrandNewVehicle.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }
}