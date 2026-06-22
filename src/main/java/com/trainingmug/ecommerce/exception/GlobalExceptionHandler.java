package com.trainingmug.ecommerce.exception;

import com.trainingmug.ecommerce.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleCustomerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Ap
        );
    }
    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<String> handleCustomerExistsException(CustomerExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<String> handleProductExistsException(ProductExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
