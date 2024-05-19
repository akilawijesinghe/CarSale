package EJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;
import Models.UsedVehicle;

/**
 * EJB for managing usedvehicle.
 * @author : akila wijesinghe - 12194813
 */
@Stateless
public class UsedVehicleEJB {
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    public UsedVehicle createUsed(UsedVehicle used) {
        em.persist(used);
        return used;
    }
    
     /**
     * Finds all customers.
     * @return A list of customers.
     */
    public List<UsedVehicle> findUsed() {
        TypedQuery<UsedVehicle> query = em.createNamedQuery("findAllUsed", UsedVehicle.class);
        return query.getResultList();
    }

    public List<UsedVehicle> findUsedByRef(String referenceNumber) {
        TypedQuery<UsedVehicle> query = em.createNamedQuery("findUsedByRef", UsedVehicle.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }
}