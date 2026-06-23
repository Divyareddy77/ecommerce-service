package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.dto.request.SignUpRequestDto;
import com.trainingmug.ecommerce.dto.request.UpdateCustomerRequestDto;
import com.trainingmug.ecommerce.dto.response.ApiResponseDto;
import com.trainingmug.ecommerce.dto.response.CustomerResponseDto;
import com.trainingmug.ecommerce.entity.Customer;
import com.trainingmug.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //CRUD operations (End Points)
    //Save ( POST -> body)
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        //1. Throw CustomerExistsException if customer exists
        //2. save customer
        //3. return saved customer

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDto.<CustomerResponseDto>builder()
                            .success(true)
                            .message("signup successful")
                            .code(HttpStatus.CREATED.value())
                            .data(customerService.register(signUpRequestDto))
                            .build()
            );

        //ResponseEntity Types
        /*
        201 Created -> ResponseEntity<Customer>
        409 Conflict -> ResponseEntity<String>
        500 Internal Server Error -> ResponseEntity<String>
         */
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CustomerResponseDto>>> getAll(){
        //GET -> 200 Ok
        //ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
        return ResponseEntity.ok(
                ApiResponseDto.<List<CustomerResponseDto>>builder()
                        .success(true)
                        .message("customers fetched successfully")
                        .code(HttpStatus.OK.value())
                        .data(customerService.getAll())
                        .build()
        );
    }
    //http://localhost:8080/api/customers/1
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Customer>> getById(@PathVariable int id){

            return ResponseEntity.ok(
                    ApiResponseDto.<Customer>builder()
                            .success(true)
                            .message("customer fetched successfully")
                            .code(HttpStatus.OK.value())
                            .data(customerService.getById(id))
                            .build()
            );

    }
    @PutMapping
    public ResponseEntity<ApiResponseDto<UpdateCustomerRequestDto>> update(@RequestBody UpdateCustomerRequestDto updateCustomerRequestDto){

            return ResponseEntity.ok(
                    ApiResponseDto.<UpdateCustomerRequestDto>builder()
                            .success(true)
                            .message("customer updated successfully")
                            .code(HttpStatus.OK.value())
                            .data(customerService.update(updateCustomerRequestDto))
                            .build()
            );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable int id){

            customerService.delete(id);
            return ResponseEntity.ok(
                    ApiResponseDto.<Void>builder()
                            .success(true)
                            .message("customer deleted successfully")
                            .code(HttpStatus.OK.value())
                            .build()
            );

    }

    /*
    http://localhost:8080/api/customers?id=1
    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam int id){}
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(
                ApiResponseDto.<CustomerResponseDto>builder()
                        .success(true)
                        .message("Login successful")
                        .code(HttpStatus.OK.value())
                        .data(customerService.login(loginRequestDto))
                        .build()
        );
    }
    @GetMapping("/created-between/{start}/{end}")
    public ResponseEntity<ApiResponseDto<List<CustomerResponseDto>>> getCustomersCreatedBetween(@PathVariable LocalDateTime start,@PathVariable LocalDateTime end){
        return ResponseEntity.ok(
                ApiResponseDto.<List<CustomerResponseDto>>builder()
                        .success(true)
                        .message("Customers fetched successfully")
                        .code(HttpStatus.OK.value())
                        .data(customerService.getsCustomersCreatedBetween(start,end))
                        .build()
        );
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDto<List<CustomerResponseDto>>> getCustomersNameLike(@PathVariable String name){
        return ResponseEntity.ok(
                ApiResponseDto.<List<CustomerResponseDto>>builder()
                        .success(true)
                        .message("Customers fetched successfully")
                        .code(HttpStatus.OK.value())
                        .data(customerService.getCustomersNameLike(name))
                        .build()
        );
    }
    @GetMapping("/{name}/sorted")
    public ResponseEntity<ApiResponseDto<List<CustomerResponseDto>>> getByNameOrderByCreatedAtDesc(@PathVariable String name){
        return ResponseEntity.ok(
                ApiResponseDto.<List<CustomerResponseDto>>builder()
                        .success(true)
                        .message("Customers fetched successfully")
                        .code(HttpStatus.OK.value())
                        .data(customerService.getByNameOrderByCreatedAtDesc(name))
                        .build()
        );
    }


}
