package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.dto.request.LoginRequestDto;
import com.trainingmug.ecommerce.dto.request.SignUpRequestDto;
import com.trainingmug.ecommerce.dto.request.UpdateCustomerRequestDto;
import com.trainingmug.ecommerce.dto.response.CustomerResponseDto;
import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public interface CustomerService {
    CustomerResponseDto register(SignUpRequestDto signUpRequestDto) throws CustomerExistsException;
    Customer getById(int id) throws CustomerNotFoundException;
    UpdateCustomerRequestDto update(UpdateCustomerRequestDto updateCustomerRequestDto) throws CustomerNotFoundException;
    List<CustomerResponseDto> getAll();
    void delete(int id) throws CustomerNotFoundException;
    CustomerResponseDto login(LoginRequestDto loginRequestDto) throws CustomerNotFoundException;
    List<CustomerResponseDto> getsCustomersCreatedBetween(LocalDateTime start, LocalDateTime end);
    List<CustomerResponseDto> getCustomersNameLike(String namePattern);
    List<CustomerResponseDto> getByNameOrderByCreatedAtDesc(String name);
}
