package com.example.AuthService.dto;

import lombok.Data;

@Data
public class AuthUserRequest {
    private String username;
    private String password;
}
