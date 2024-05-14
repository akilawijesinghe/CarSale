package business.Logics;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;


@Stateless
public class BrandNewVehicleEJB {
    @PersistenceContext(unitName = "CarSalesPU")
    private EntityManager em;

//    public List<BrandNewVehicle> findBrandnews() {
//        TypedQuery<BrandNewVehicle> query = em.createNamedQuery(BrandNewVehicle.FIND_ALL, BrandNewVehicle.class);
//        return query.getResultList();
//    }

//    public List<BrandNewVehicle> findBrandnewbyRFN(String rfn) {
//	TypedQuery<BrandNewVehicle> query = em.createNamedQuery(BrandNewVehicle.FIND_RFN, BrandNewVehicle.class);
//	query.setParameter("prfn", rfn);
//        List<BrandNewVehicle> brandnews=query.getResultList();
//	int len=brandnews.size();
//	for (int i = 0; i < len; i++) {
//            BrandNewVehicle bn=brandnews.get(i);
//            System.out.println(bn.getRfn());
//	}
//	return brandnews; 
//    }
//
//    public void reduceAmount(BrandNewVehicle bn, int rn) {
//	int i;
//	i=bn.getnOfCars().intValue();
//	i=i-rn;
//	bn.setnOfCars(Integer.valueOf(i));
//	em.merge(bn);
//    }

    public BrandNewVehicle createBrandnew(BrandNewVehicle brandnew) {
        em.persist(brandnew);
        return brandnew;
    }
}