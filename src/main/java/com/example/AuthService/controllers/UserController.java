package com.example.AuthService.controllers;

import com.example.AuthService.dto.AuthUserRequest;
import com.example.AuthService.dto.RegisterUserRequest;
import com.example.AuthService.services.AuthService;
import com.example.AuthService.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthService authService;
//    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws RoleNotFoundException {
        log.info("Controller register is called");
        return userService.registerUser(registerUserRequest);
    }

    @GetMapping("/auth")
    public ResponseEntity<?> authUser(@RequestBody AuthUserRequest authUserRequest){
        log.info("Controller auth is called");
        return authService.authUser(authUserRequest);
    }
}
