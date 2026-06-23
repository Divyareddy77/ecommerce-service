package com.trainingmug.ecommerce.dto.request;

import com.trainingmug.ecommerce.entity.Address;
import com.trainingmug.ecommerce.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDto {
    @NotBlank(message = "Name is required")

    private String name;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    //private Address address;
    //private List<Address> addresses;
}
