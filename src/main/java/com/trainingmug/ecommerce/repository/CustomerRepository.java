package com.trainingmug.ecommerce.repository;

import com.trainingmug.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findDistinctByEmailAndPassword(String email,String password);
    List<Customer> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Customer> findByNameLike(String namePattern);
    List<Customer> findByNameOrderByCreatedAtDesc(String name);
}
