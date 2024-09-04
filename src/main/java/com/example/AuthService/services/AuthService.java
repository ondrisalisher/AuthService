package com.example.AuthService.services;

import com.example.AuthService.dto.AuthUserRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<?> authUser(AuthUserRequest authUserRequest);
}
