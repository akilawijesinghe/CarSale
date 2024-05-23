package Models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "CUSTOMER_ORDER")
@NamedQueries({
    @NamedQuery(name = "Order.findAllOrder", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Order.findByID", query = "SELECT o FROM Order o where o.id= :id")
})

public class Order implements Serializable {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected Float unitPrice;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date timeCreated = new Date();

    @Column(nullable = false)
    protected Integer quantity = 1;

    @ManyToOne(cascade = {
        CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CARID", referencedColumnName = "ID")
    private Car car;

    public Long getId() {
        return this.id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTotalPrice() {
        return this.unitPrice * this.quantity;
    }

    public Date getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", timeCreated=" + timeCreated + ", customer=" + customer + ", car=" + car + '}';
    }
}
