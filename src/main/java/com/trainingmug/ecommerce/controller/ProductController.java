package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.dto.request.ProductRequestDto;
import com.trainingmug.ecommerce.dto.request.UpdateProductRequestDto;
import com.trainingmug.ecommerce.dto.response.ApiResponseDto;
import com.trainingmug.ecommerce.dto.response.ProductResponseDto;
import com.trainingmug.ecommerce.entity.Product;
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
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> save(@RequestBody ProductRequestDto productRequestDto){

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDto.<ProductResponseDto>builder()
                            .success(true)
                            .message("Product saved successfully")
                            .code(HttpStatus.CREATED.value())
                            .data(productService.save(productRequestDto))
                            .build()
            );

    }
    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAllProducts(){
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .message("Products fetched successfully")
                        .code(HttpStatus.OK.value())
                        .data(productService.getAll())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductById(@PathVariable int id){

            return ResponseEntity.ok(
                    ApiResponseDto.<ProductResponseDto>builder()
                            .success(true)
                            .message("Product fetched successfully")
                            .code(HttpStatus.OK.value())
                            .data(productService.getProductById(id))
                            .build()
            );

    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> update(@PathVariable int id, @RequestBody UpdateProductRequestDto updateProductRequestDto){

            ProductResponseDto updatedProduct = productService.update(updateProductRequestDto);
            return ResponseEntity.ok(
                    ApiResponseDto.<ProductResponseDto>builder()
                            .success(true)
                            .message("Product updated successfully")
                            .code(HttpStatus.OK.value())
                            .data(productService.update(updateProductRequestDto))
                            .build()
            );

    }
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable int id){

            productService.deleteById(id);
            return ResponseEntity.ok(
                    ApiResponseDto.<Void>builder()
                            .success(true)
                            .message("Product deleted successfully")
                            .code(HttpStatus.OK.value())
                            .build()
            );

    }
   /* @GetMapping("/available")
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
    *//*

     *//*
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
*/
}
