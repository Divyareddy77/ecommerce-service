package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.dto.request.ProductRequestDto;
import com.trainingmug.ecommerce.dto.request.UpdateProductRequestDto;
import com.trainingmug.ecommerce.dto.response.ProductResponseDto;
import com.trainingmug.ecommerce.exception.ProductExistsException;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;
import com.trainingmug.ecommerce.entity.Product;
import com.trainingmug.ecommerce.repository.ProductRepository;
import com.trainingmug.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto) throws ProductExistsException {
        boolean exists = productRepository.findAll().stream().anyMatch(p ->p.getId()==productRequestDto.getId());
        if(exists){
            throw new ProductExistsException("product already exists");
        }
        Product product = modelMapper.map(productRequestDto,Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct,ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto update(UpdateProductRequestDto updateProductRequestDto) throws ProductExistsException {
        Product product = productRepository.findById(updateProductRequestDto.getId()).orElseThrow(() -> new ProductNotFoundException("Product not found with id "+updateProductRequestDto.getId()));
        product.setName(updateProductRequestDto.getName());
        product.setAvailable(updateProductRequestDto.isAvailable());
        product.setCategory(updateProductRequestDto.getCategory());
        product.setCompany(updateProductRequestDto.getCompany());
        product.setMaxRetailPrice(updateProductRequestDto.getMaxRetailPrice());
        product.setDiscountPercentage(updateProductRequestDto.getDiscountPercentage());
        product.setManufacturedYear(updateProductRequestDto.getManufacturedYear());
        product.setRating(updateProductRequestDto.getRating());
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct,ProductResponseDto.class);
    }

    @Override
    public void deleteById(int id) throws ProductNotFoundException {
        productRepository.deleteById(id);
    }
    @Override
    public List<Product> getProductsByAvailability(boolean isAvailable) {
        return productRepository.findAll().stream().filter(p->p.isAvailable()==isAvailable).toList();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findAll().stream().filter(p-> p.getCategory().equals(category)).toList();
    }

    @Override
    public ProductResponseDto getProductById(int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id "+id));
        return modelMapper.map(product,ProductResponseDto.class);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findAll().stream().map(Product::getCategory).distinct().toList();
    }

    @Override
    public List<Product> getProductsWithPriceGreaterThan(int price) {
        return productRepository.findAll().stream().filter(p->p.getMaxRetailPrice()>price).toList();

    }

    @Override
    public List<String> getNamesOfAllProducts() {
        return productRepository.findAll().stream().map(Product::getName).toList();
    }

    @Override
    public int getCountOfProductsAvailable() {
        return (int) productRepository.findAll().stream().filter(Product::isAvailable).count();
    }

    @Override
    public boolean anyProductFromGivenCompany(String company) {
        return productRepository.findAll().stream().anyMatch(p->p.getCompany().equals(company));
    }

    @Override
    public boolean areALlProductsAvailable() {
        return productRepository.findAll().stream().allMatch(Product::isAvailable);
    }

    @Override
    public Optional<Product> getFirstProduct() {
        return productRepository.findAll().stream().findFirst();
    }

    @Override
    public List<Product> getTopNMostExpensiveProducts(int N) {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getMaxRetailPrice).reversed()).limit(N).toList();
    }

    @Override
    public List<Product> getProductsByPriceInAscending() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getMaxRetailPrice)).toList();
    }

    @Override
    public List<Product> getProductsByNAmeInDescending() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getName).reversed()).toList();
    }

    @Override
    public Double sumOfAllProductPrices() {
        return productRepository.findAll().stream().mapToDouble(Product::getMaxRetailPrice).sum();
    }

    @Override
    public int priceAfterApplyingDiscounts() {
        return productRepository.findAll().stream().mapToInt(p->p.getMaxRetailPrice()-((p.getMaxRetailPrice()*p.getDiscountPercentage())/100)).sum();
    }

    @Override
    public List<Product> getProductsManufacturedAfterYear(int year) {
        return productRepository.findAll().stream().filter(p->p.getManufacturedYear()>year).toList();
    }

    @Override
    public List<Product> getAvailableAndPriceGreaterThan(int price) {
        return productRepository.findAll().stream().filter(p->p.isAvailable() && p.getMaxRetailPrice() > price).toList();
    }

    @Override
    public Map<String, Long> countOfProductsInCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory,Collectors.counting()));
    }

    @Override
    public Map<String, List<Product>> groupProductsByCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory));
    }

    @Override
    public Map<String, List<Product>> groupProductsByCompany() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCompany));
    }

    @Override
    public Map<Boolean, List<Product>> partitionIntoAvailableAndUnavailable() {
        return productRepository.findAll().stream().collect(Collectors.partitioningBy(Product::isAvailable));
    }

    @Override
    public Optional<Product> getMostExpensiveProduct() {
        return productRepository.findAll().stream().max(Comparator.comparing(Product::getMaxRetailPrice));
    }

    @Override
    public Optional<Product> getCheapestProduct() {
        return productRepository.findAll().stream().min(Comparator.comparing(Product::getMaxRetailPrice));
    }

    @Override
    public Map<Integer, Product> mapOfProductIdToProduct() {
        return productRepository.findAll().stream().collect(Collectors.toMap(Product::getId,p->p));
    }

    @Override
    public Map<String, Double> averagePriceOfProductsPerCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory,Collectors.averagingDouble(Product::getMaxRetailPrice)));
    }
}
