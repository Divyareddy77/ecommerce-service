package com.trainingmug.ecommerce.dto.request;

import com.trainingmug.ecommerce.entity.Address;
import com.trainingmug.ecommerce.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    //private Address address;
    private List<Address> addresses;
}
