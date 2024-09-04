package com.example.AuthService.services.impl;

import com.example.AuthService.dto.AuthUserRequest;
import com.example.AuthService.dto.JwtResponse;
import com.example.AuthService.exceptions.AppError;
import com.example.AuthService.services.AuthService;
import com.example.AuthService.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    public ResponseEntity<?> authUser(AuthUserRequest authUserRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authUserRequest.getUsername(),
                            authUserRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong username or password"), HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtils.generateToken(userDetailsService.loadUserByUsername(authUserRequest.getUsername()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
