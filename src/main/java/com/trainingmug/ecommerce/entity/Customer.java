package com.trainingmug.ecommerce.entity;

import com.trainingmug.ecommerce.enums.Gender;
import com.trainingmug.ecommerce.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;



import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status; //Backend operation
    private LocalDateTime createdAt;
    private LocalDateTime lastLoggedInAt;

    /*@OneToOne(cascade = CascadeType.ALL)
    private Address address;*/


    /*@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;*/


    /*@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses;*/



    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "customer_addresses",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;*/



}
