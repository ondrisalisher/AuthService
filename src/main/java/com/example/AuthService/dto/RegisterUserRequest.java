package com.example.AuthService.dto;

import lombok.Data;

@Data
public class RegisterUserRequest{
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
}
