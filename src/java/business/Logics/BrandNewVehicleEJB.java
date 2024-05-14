package business.Logics;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;


@Stateless
public class BrandNewVehicleEJB {
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

    public BrandNewVehicle createBrandnew(BrandNewVehicle brandnew) {
        em.persist(brandnew);
        return brandnew;
    }
}