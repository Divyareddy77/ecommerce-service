package com.trainingmug.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDto<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;
}
