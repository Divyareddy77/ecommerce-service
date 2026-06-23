package com.trainingmug.ecommerce.exception;

import com.trainingmug.ecommerce.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleCustomerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponseDto.<String>builder()
                        .success(false)
                        .message(e.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .data(e.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<ApiResponseDto<String>> handleCustomerExistsException(CustomerExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponseDto.<String>builder()
                        .success(false)
                        .code(HttpStatus.CONFLICT.value())
                        .data(e.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<ApiResponseDto<String>> handleProductExistsException(ProductExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponseDto.<String>builder()
                        .success(false)
                        .code(HttpStatus.CONFLICT.value())
                        .data(e.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleProductNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponseDto.<String>builder()
                        .success(false)
                        .message(e.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .data(e.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<String>> handleAllExceptions(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponseDto.<String>builder()
                        .success(false)
                        .message(e.getMessage())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .data(e.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Map<String,String>>> handleValidationException(MethodArgumentNotValidException e){
        Map<String , String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiResponseDto.<Map<String, String>>builder()
                        .success(false)
                        .message("Validation failed")
                        .code(HttpStatus.BAD_REQUEST.value())
                        .data(errors)
                        .build()
        );

    }
}
