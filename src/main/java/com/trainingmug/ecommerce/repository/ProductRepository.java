package com.trainingmug.ecommerce.repository;

import com.trainingmug.ecommerce.entity.Product;
import com.trainingmug.ecommerce.util.CsvReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
