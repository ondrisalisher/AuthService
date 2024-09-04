package com.example.AuthService.services;

import com.example.AuthService.dto.RegisterUserRequest;
import org.springframework.http.ResponseEntity;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    public ResponseEntity<?> registerUser(RegisterUserRequest registerUserRequest) throws RoleNotFoundException;
}
