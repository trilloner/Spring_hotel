package com.example.hotel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationDto {

    public Long id;
    @NotBlank(message = "Please enter your email")
    public String email;
    @Length(max = 15, message = "Username is too long")
    public String name;
    @Length(min = 8, message = "Password is too short")
    public String password;
}
