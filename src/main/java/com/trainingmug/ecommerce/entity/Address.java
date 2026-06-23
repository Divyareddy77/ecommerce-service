package com.trainingmug.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;

   /* @OneToOne
    private Customer customer;*/

    /*@ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;*/


    /*@ManyToMany(mappedBy = "addresses")
    private List<Customer> customers;*/
}
