package com.trainingmug.ecommerce.repository;

import com.trainingmug.ecommerce.model.Product;
import com.trainingmug.ecommerce.service.ProductService;
import com.trainingmug.ecommerce.util.CsvReader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products;
    private final CsvReader csvReader;

    public ProductRepository(CsvReader csvReader) throws IOException {
        this.csvReader = csvReader;
        this.products = this.csvReader.getDataFromCsv();
    }
    public Product save(Product product){
        products.add(product);
        return product;
    }

    public List<Product> findAll(){
        return products;
    }
    public Optional<Product> findBYId(int id){
        return products.stream().filter(p->p.getId()==id).findFirst();
    }
    public Product update(Product product){
        return products.stream().filter(p->p.getId()==product.getId()).map(p->{
            p.setName(product.getName());
            p.setMaxRetailPrice(product.getMaxRetailPrice());
            p.setDiscountPercentage(p.getDiscountPercentage());
            p.setAvailable(product.isAvailable());
            p.setCompany(product.getCompany());
            p.setCategory(product.getCategory());
            p.setManufacturedYear(product.getManufacturedYear());
            p.setRating(p.getRating());
            return p;
        }).findFirst().orElse(null);
    }
    public void delete(int id){
        products.removeIf(p->p.getId()==id);
    }
}
