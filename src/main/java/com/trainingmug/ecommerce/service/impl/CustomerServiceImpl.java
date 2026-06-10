package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.enums.Status;
import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.entity.Customer;
import com.trainingmug.ecommerce.repository.CustomerRepository;
import com.trainingmug.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) throws CustomerExistsException {
        customerRepository.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new CustomerExistsException("Customer exists with email: " + customer.getEmail() );
        });
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastLoggedInAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer update(Customer customer) throws CustomerNotFoundException {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customer.getId()));
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(int id) throws CustomerNotFoundException {
        customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }
}
