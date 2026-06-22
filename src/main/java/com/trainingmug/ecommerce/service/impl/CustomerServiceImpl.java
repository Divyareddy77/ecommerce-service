package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.dto.request.SignUpRequestDto;
import com.trainingmug.ecommerce.dto.request.UpdateCustomerRequestDto;
import com.trainingmug.ecommerce.dto.response.CustomerResponseDto;
import com.trainingmug.ecommerce.entity.Address;
import com.trainingmug.ecommerce.enums.Gender;
import com.trainingmug.ecommerce.enums.Status;
import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.entity.Customer;
import com.trainingmug.ecommerce.repository.AddressRepository;
import com.trainingmug.ecommerce.repository.CustomerRepository;
import com.trainingmug.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    @Override
    public CustomerResponseDto register(SignUpRequestDto signUpRequestDto) throws CustomerExistsException {
        customerRepository.findByEmail(signUpRequestDto.getEmail()).ifPresent(c -> {
            throw new CustomerExistsException("Customer exists with email: " + signUpRequestDto.getEmail() );
        });
        /*Customer customer = Customer.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .phone(signUpRequestDto.getPhone())
                .gender(signUpRequestDto.getGender())
                .build();*/
        Customer customer = modelMapper.map(signUpRequestDto,Customer.class);
        customer.setStatus(Status.ACTIVE);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastLoggedInAt(LocalDateTime.now());
        //customer.getAddress().setCustomer(customer);
        //customer.getAddresses().forEach(address -> address.setCustomer(customer));
        List<Address> addresses = customer.getAddresses()
                .stream()
                .map(address -> {
                    if(address.getId() != null){
                        return addressRepository.findById(address.getId()).orElseThrow();
                    }
                    return address;
                }).toList();
        customer.setAddresses(addresses);




        /*Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponseDto.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .status(savedCustomer.getStatus())
                .build();

         */
        return modelMapper.map(customerRepository.save(customer),CustomerResponseDto.class);

    }

    @Override
    public Customer getById(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public UpdateCustomerRequestDto update(UpdateCustomerRequestDto updateCustomerRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(updateCustomerRequestDto.getId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + updateCustomerRequestDto.getId()));
        customer.setName(updateCustomerRequestDto.getName());
        customer.setPhone(updateCustomerRequestDto.getPhone());
        customer.setGender(updateCustomerRequestDto.getGender());
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer,UpdateCustomerRequestDto.class);
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer,CustomerResponseDto.class)).toList();
    }

    @Override
    public void delete(int id) throws CustomerNotFoundException {
        customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDto login(LoginRequestDto loginRequestDto) throws CustomerNotFoundException {

        /*Customer customer = customerRepository.findDistinctByEmailAndPassword(loginRequestDto.getEmail(),loginRequestDto.getPassword()).orElseThrow(() ->new CustomerNotFoundException("Customer not found with email "+loginRequestDto.getEmail()));
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .status(customer.getStatus())
                .build();*/
        return modelMapper.map(customerRepository.findDistinctByEmailAndPassword(loginRequestDto.getEmail(),loginRequestDto.getPassword()).orElseThrow(() -> new CustomerNotFoundException("Customer not found"+loginRequestDto.getEmail())),CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> getsCustomersCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return customerRepository.findByCreatedAtBetween(start, end).stream().map(customer -> modelMapper.map(customer,CustomerResponseDto.class)).toList();
    }

    @Override
    public List<CustomerResponseDto> getCustomersNameLike(String namePattern) {
        return customerRepository.findByNameLike("%"+namePattern+"%").stream().map(customer -> modelMapper.map(customer,CustomerResponseDto.class)).toList();
    }

    @Override
    public List<CustomerResponseDto> getByNameOrderByCreatedAtDesc(String name) {
        return customerRepository.findByNameOrderByCreatedAtDesc(name).stream().map(customer -> modelMapper.map(customer,CustomerResponseDto.class)).toList();
    }
}
