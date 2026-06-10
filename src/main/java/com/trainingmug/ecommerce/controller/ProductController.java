package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;
import com.trainingmug.ecommerce.model.Customer;
import com.trainingmug.ecommerce.model.Product;
import com.trainingmug.ecommerce.service.CustomerService;
import com.trainingmug.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody Product product){
        try{
            Product updatedProduct = productService.update(product);
            return ResponseEntity.ok(updatedProduct);
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts(){
        return ResponseEntity.ok(productService.getProductsByAvailability(true));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getProductByCategory(category));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<String >> getAllCategories(){
        return ResponseEntity.ok(productService.getAllCategories());
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Product>> getProductsWithPriceGreaterThan(@PathVariable int price){
        return ResponseEntity.ok(productService.getProductsWithPriceGreaterThan(price));
    }
    @GetMapping("/names")
    public ResponseEntity<List<String>> getNamesOfAllProducts(){
        return ResponseEntity.ok(productService.getNamesOfAllProducts());
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getCountOfProductsAvailable(){
        return ResponseEntity.ok(productService.getCountOfProductsAvailable());
    }
    @GetMapping("/company/{company}")
    public ResponseEntity<Boolean> anyProductFromGivenCompany(@PathVariable String company){
        return ResponseEntity.ok(productService.anyProductFromGivenCompany(company));
    }
    @GetMapping("/first")
    public ResponseEntity<Optional<Product>> getFirstProduct(){
        return ResponseEntity.ok(productService.getFirstProduct());
    }
    @GetMapping("/top-expensive/{N}")
    public ResponseEntity<List<Product>> getTopNMostExpensiveProducts(@PathVariable int N){
        return ResponseEntity.ok(productService.getTopNMostExpensiveProducts(N));
    }
    @GetMapping("/price-ascending")
    public ResponseEntity<List<Product>> getProductsByPriceInAscending(){
        return ResponseEntity.ok(productService.getProductsByPriceInAscending());
    }
    /*

     */
    @GetMapping("/name-descending")
    public ResponseEntity<List<Product>> getProductsByNAmeInDescending(){
        return ResponseEntity.ok(productService.getProductsByNAmeInDescending());
    }
    @GetMapping("/sum")
    public ResponseEntity<Double> sumOfAllProductPrices(){
        return ResponseEntity.ok(productService.sumOfAllProductPrices());
    }
    @GetMapping("/price-after-discount")
    public ResponseEntity<Integer> priceAfterApplyingDiscounts(){
        return ResponseEntity.ok(productService.priceAfterApplyingDiscounts());
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Product>> getProductsManufacturedAfterYear(@PathVariable int year){
        return ResponseEntity.ok(productService.getProductsManufacturedAfterYear(year));
    }
    @GetMapping("/price-greater-than/{price}")
    public ResponseEntity<List<Product>> getAvailableAndPriceGreaterThan(@PathVariable int price){
        return ResponseEntity.ok(productService.getAvailableAndPriceGreaterThan(price));
    }
    @GetMapping("/count-category")
    public ResponseEntity<Map<String,Long>> countOfProductsInCategory(){
        return ResponseEntity.ok(productService.countOfProductsInCategory());
    }
    @GetMapping("/group-by-category")
    public ResponseEntity<Map<String,List<Product>>> groupProductsByCategory(){
        return ResponseEntity.ok(productService.groupProductsByCategory());
    }
    @GetMapping("/group-by-company")
    public ResponseEntity<Map<String,List<Product>>> groupProductsByCompany(){
        return ResponseEntity.ok(productService.groupProductsByCompany());
    }
    @GetMapping("/partition-available-unavailable")
    public ResponseEntity<Map<Boolean,List<Product>>> partitionIntoAvailableAndUnavailable(){
        return ResponseEntity.ok(productService.partitionIntoAvailableAndUnavailable());
    }
    @GetMapping("/most-expensive")
    public ResponseEntity<Optional<Product>> getMostExpensiveProduct(){
        return ResponseEntity.ok(productService.getMostExpensiveProduct());
    }
    @GetMapping("/cheapest")
    public ResponseEntity<Optional<Product>> getCheapestProduct(){
        return ResponseEntity.ok(productService.getCheapestProduct());
    }
    @GetMapping("/map-id-product")
    public ResponseEntity<Map<Integer,Product>> mapOfProductIdToProduct(){
        return ResponseEntity.ok(productService.mapOfProductIdToProduct());
    }
    @GetMapping("/average-per-category")
    public ResponseEntity<Map<String,Double>> averagePriceOfProductsPerCategory(){
        return ResponseEntity.ok(productService.averagePriceOfProductsPerCategory());
    }
    @GetMapping("/are-all-products-available")
    public ResponseEntity<Boolean> areALlProductsAvailable(){
        return ResponseEntity.ok(productService.areALlProductsAvailable());
    }

}
