package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.entity.Customer;
import com.trainingmug.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //CRUD operations (End Points)
    //Save ( POST -> body)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        //1. Throw CustomerExistsException if customer exists
        //2. save customer
        //3. return saved customer

            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));

        //ResponseEntity Types
        /*
        201 Created -> ResponseEntity<Customer>
        409 Conflict -> ResponseEntity<String>
        500 Internal Server Error -> ResponseEntity<String>
         */
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        //GET -> 200 Ok
        //ResonseEntity.status(HttpStatus.OK).body(customerService.getAll());
        return ResponseEntity.ok(customerService.getAll());
    }
    //http://localhost:8080/api/customers/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){

            return ResponseEntity.ok(customerService.getById(id));

    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer){

            return ResponseEntity.ok(customerService.update(customer));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){

            customerService.delete(id);
            return ResponseEntity.noContent().build();

    }

    /*
    http://localhost:8080/api/customers?id=1
    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam int id){}
     */


}
