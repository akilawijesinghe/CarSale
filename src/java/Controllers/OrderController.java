package Controllers;

import java.io.Serializable;
import java.util.List;

import EJB.OrderEJB;
import EJB.CustomerEJB;
import EJB.CarEJB;
import Models.Order;
import Models.Customer;
import Models.Car;
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
    
    private Customer customer = new Customer();

    private Order order = new Order();
    private int selectedCustomerId;
    private Long selectedCarId;
    private List<Order> orderList;
    private List<Order> orderSearchList = new ArrayList<>();
    private int searchOrderId;

public String doCreateOrder() {
    FacesContext ctx = FacesContext.getCurrentInstance();

    if (ctx.getMessageList().size() != 0) {
        return null;
    }

    try {
        Customer selectedCustomer = customerEJB.findACustomer(selectedCustomerId);
        Car selectedCar = carEJB.find(selectedCarId);
        
        if (selectedCar.getCarCount() < order.getQuantity()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not enough stock available", null));
            return null;
        }

        selectedCar.setCarCount(selectedCar.getCarCount() - order.getQuantity());
        carEJB.updateCar(selectedCar);

        order.setCustomer(selectedCustomer);
        order.setCar(selectedCar);
        order = orderEJB.createOrder(order);
        
        if (order != null && order.getId() != null) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A new order has been created", null));
        } else {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order hasn't been created", null));
        }
    } catch (Exception e) {
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order hasn't been created", e.getMessage()));
        e.printStackTrace(); 
    }
    return "index.xhtml";
}

    public List<Customer> getCustomerList() {
        return customerEJB.findCustomers();
    }

    public List<Car> getCarList() {
        return carEJB.findCars();
    }
    public List<Order> getOrderList() {
        if (orderList == null) {
            orderList = orderEJB.findOrders();
        }
        return orderList;
    }
    public void deleteOrder(Long orderId) {
        Order order = orderEJB.findOrderById(orderId);
        if (order != null) {
            Car car = order.getCar();
            car.setCarCount(car.getCarCount() + order.getQuantity());
            orderEJB.updateCar(car);
            orderEJB.deleteOrder(order);
        }
        // Refresh the order list
        orderList = orderEJB.findOrders();
    }
    
    public String getOrderByOrderId() {
        orderSearchList = orderEJB.searchOrderByOrderId(searchOrderId);
        return "searchList.xhtml";
    }

    public List<Order> getOrderSearchList() {
        return orderSearchList;
    }
    
//    public String doViewDetails(int id){
//        this.customer = customerEJB.findACustomer(id);
//        this.orderList=orderEJB.findOrdersByCustomer(id);
//        //return "../../pages/customers/detail.xhtml";
//        return "detail.xhtml";
//    }
}
