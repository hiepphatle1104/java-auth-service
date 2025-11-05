package com.swappie.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
