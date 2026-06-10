package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.exception.ProductExistsException;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;
import com.trainingmug.ecommerce.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Product save(Product product) throws ProductNotFoundException;
    Product update(Product product) throws ProductExistsException;
    public void deleteById(int id) throws ProductNotFoundException;
    List<Product> getProductsByAvailability(boolean isAvailable);
    List<Product> getProductByCategory(String category);
    Product getProductById(int id) throws ProductNotFoundException;
    List<String> getAllCategories();
    List<Product> getProductsWithPriceGreaterThan(int price);
    List<String> getNamesOfAllProducts();
    int getCountOfProductsAvailable();
    boolean anyProductFromGivenCompany(String company);
    boolean areALlProductsAvailable();
    Optional<Product> getFirstProduct();
    List<Product> getTopNMostExpensiveProducts(int N);
    List<Product> getProductsByPriceInAscending();
    List<Product> getProductsByNAmeInDescending();
    Double sumOfAllProductPrices();
    int priceAfterApplyingDiscounts();
    List<Product> getProductsManufacturedAfterYear(int year);
    List<Product> getAvailableAndPriceGreaterThan(int price);
    Map<String, Long> countOfProductsInCategory();
    Map<String, List<Product>> groupProductsByCategory();
    Map<String, List<Product>> groupProductsByCompany();
    Map<Boolean,List<Product>> partitionIntoAvailableAndUnavailable();
    Optional<Product> getMostExpensiveProduct();
    Optional<Product> getCheapestProduct();
    Map<Integer,Product> mapOfProductIdToProduct();
    Map<String,Double> averagePriceOfProductsPerCategory();
}
