/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class presents a customer database model
 *
 * @author : kasun eranda - 12216898
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = "findAllCustomers", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "findWithParam", query = "select c from Customer c where c.name = :name"),
    @NamedQuery(name = "findById", query = "select c from Customer c where c.id = :id")
})
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "address")
    protected String address;
    @Column(name = "phone")
    protected String phone;
    @Column(name = "email")
    protected String email;
    
    @OneToMany
     private List<Order> orders;
    

}
