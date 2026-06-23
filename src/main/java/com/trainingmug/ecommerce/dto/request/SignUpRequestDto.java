package com.trainingmug.ecommerce.dto.request;

import com.trainingmug.ecommerce.entity.Address;
import com.trainingmug.ecommerce.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Data
public class SignUpRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 20,message = "Name must be between 5 to 20 characters")
    @Pattern(
            regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$",
            message = "Only alphabets and space is allowed"

    )
    private String name;
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format"
    )
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 5, max = 20,message = "Password should be between 5 to 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long")
    private String password;
    @NotBlank(message = "phone is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be 10 digits and start with 6, 7, 8, or 9"
    )
    private String phone;
    private Gender gender;
    //private Address address;
    //private List<Address> addresses;
}
