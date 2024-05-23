package Controllers;

import EJB.BrandNewVehicleEJB;
import java.io.Serializable;
import java.util.List;

import EJB.OrderEJB;
import EJB.CustomerEJB;
import EJB.CarEJB;
import EJB.UsedVehicleEJB;
import Models.BrandNewVehicle;
import Models.Order;
import Models.Customer;
import Models.Car;
import Models.UsedVehicle;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Named(value = "orderController")
@RequestScoped
@Getter
@Setter
public class OrderController implements Serializable {

    @EJB
    private OrderEJB orderEJB;

    @EJB
    private CustomerEJB customerEJB;

    @EJB
    private CarEJB carEJB;

    @EJB
    private OrderEJB OrderEJB;

    @EJB
    private BrandNewVehicleEJB brandnewvehicleEJB;
    @EJB
    private UsedVehicleEJB usedvehicleEJB;

    private Customer customer = new Customer();

    private List<BrandNewVehicle> brandNewCarList = new ArrayList<BrandNewVehicle>();
    private List<BrandNewVehicle> brandNewCarListByRef = new ArrayList<BrandNewVehicle>();

    private List<UsedVehicle> usedCarList = new ArrayList<UsedVehicle>();
    private List<UsedVehicle> usedCarListByRef = new ArrayList<UsedVehicle>();

    private Order order = new Order();
    private Long selectedCustomerId;
    private Long searchorder;
    private Long selectedCarId;
    private List<Order> orderList = new ArrayList<Order>();

    private Customer searchCustomerList = new Customer();
    private List<Order> orderSearchList = new ArrayList<Order>();

    private Long searchOrderId;

    public String doSearchCarOrder() {
        orderSearchList = orderEJB.findOrderById(searchOrderId);
        return "listSearch.xhtml";
    }

    public String displayCustomer(int id) {
        this.customer = customerEJB.findACustomer(id);
        return "detail.xhtml";
    }

    public String doCreateOrder() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Customer orderCustomer = this.getCustomerById(this.selectedCustomerId);
        Car orderCar = this.getCarById(this.selectedCarId);
        if (orderCustomer == null || orderCar == null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order hasn't been created", null));
        } else {
            order.setCar(orderCar);
            order.setCustomer(orderCustomer);
            order.setUnitPrice(orderCar.getPrice().floatValue());
            orderCustomer.getOrders().add(order);
            this.order = OrderEJB.mergeCarOrder(order);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A new order has been created", null));
        }

        int deduuct = this.order.getQuantity().intValue();

        if (orderCar instanceof UsedVehicle) {
            UsedVehicle usedV = (UsedVehicle) orderCar;
            usedvehicleEJB.reduceAmount(usedV, deduuct);
            this.usedCarList = usedvehicleEJB.findUsed();
        } else {
            BrandNewVehicle brandNew = (BrandNewVehicle) orderCar;
            brandnewvehicleEJB.reduceAmount(brandNew, deduuct);
            this.brandNewCarList = brandnewvehicleEJB.findBrandNew();
        }

        this.orderList = orderEJB.findOrders();
        return "index.xhtml";
    }

    private Customer getCustomerById(Long customerId) {
        return customerEJB.find(customerId);
    }

    private Car getCarById(Long carId) {
        return carEJB.find(carId);
    }

    public List<Customer> getCustomerList() {
        return customerEJB.findCustomers();
    }

    public List<Car> getCarList() {
        return carEJB.findCars();
    }

    public List<Order> getOrderList() {
        orderList = orderEJB.findOrders();
        return orderList;
    }
  

    public List<Order> getOrderSearchList() {
        return orderSearchList;
    }
    
    public String displayCar(Car car) {
        if (car instanceof BrandNewVehicle) {
            brandNewCarListByRef = brandnewvehicleEJB.findBrandNewByRef(car.getReferenceNumber());
            return "brandNewSearchList.xhtml";
        } else {
            usedCarListByRef = usedvehicleEJB.findUsedByRef(car.getReferenceNumber());
            return "usedSearchList.xhtml";
        }
    }

    public String doDeleteOrder(Long orderId) {
        Order delOrder = orderEJB.find(orderId); // Assuming a method to find order by ID
        if (delOrder == null) {
            return "error.xhtml";
        }

        Customer customerDel = delOrder.getCustomer();
        if (customerDel.getOrders() == null) {
            customerDel.setOrders(new ArrayList<Order>());
        }

        customerDel.getOrders().remove(delOrder);
        customerEJB.mergeCustomer(customerDel);

        orderEJB.deleteOrderByID(delOrder.getId());

        Car deleteCar = delOrder.getCar();
        int add = delOrder.getQuantity().intValue();

        if (deleteCar instanceof UsedVehicle) {
            UsedVehicle dedcustomer = (UsedVehicle) deleteCar;
            usedvehicleEJB.reduceAmount(dedcustomer, -add);
            this.usedCarList = usedvehicleEJB.findUsed();
        } else {
            BrandNewVehicle dedbrandNewCar = (BrandNewVehicle) deleteCar;
            brandnewvehicleEJB.reduceAmount(dedbrandNewCar, -add);
            this.brandNewCarList = brandnewvehicleEJB.findBrandNew();
        }

        this.orderList = orderEJB.findOrders();

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index?faces-redirect=true"; // Adds a redirect parameter to the URL
    }

}
