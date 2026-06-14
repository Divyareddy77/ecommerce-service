package com.trainingmug.ecommerce.dto.request;

import com.trainingmug.ecommerce.enums.Gender;
import lombok.Data;

@Data
public class UpdateCustomerRequestDto {
    private int id;
    private String name;
    private String phone;
    private Gender gender;
}
