package com.example.AuthService.controllers;

import com.example.AuthService.dto.AuthUserRequest;
import com.example.AuthService.dto.RegisterUserRequest;
import com.example.AuthService.services.AuthService;
import com.example.AuthService.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws RoleNotFoundException {
        return userService.registerUser(registerUserRequest);
    }

    @GetMapping("/auth")
    public ResponseEntity<?> authUser(@RequestBody AuthUserRequest authUserRequest){
        return authService.authUser(authUserRequest);
    }
}
