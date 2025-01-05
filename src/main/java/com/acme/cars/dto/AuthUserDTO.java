package com.acme.cars.dto;

import lombok.Data;

@Data
public class AuthUserDTO {
    private String email;
    private String password;
}