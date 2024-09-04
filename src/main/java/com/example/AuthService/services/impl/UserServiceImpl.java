package com.example.AuthService.services.impl;

import com.example.AuthService.dto.AuthUserRequest;
import com.example.AuthService.dto.JwtResponse;
import com.example.AuthService.dto.RegisterUserRequest;
import com.example.AuthService.exceptions.AppError;
import com.example.AuthService.models.Role;
import com.example.AuthService.models.User;
import com.example.AuthService.repositories.RoleRepository;
import com.example.AuthService.repositories.UserRepository;
import com.example.AuthService.services.UserService;
import com.example.AuthService.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public ResponseEntity<?> registerUser(RegisterUserRequest registerUserRequest) throws RoleNotFoundException {
        String email = registerUserRequest.getEmail();
        String username = registerUserRequest.getUsername();
        String password = registerUserRequest.getPassword();
        String passwordConfirm = registerUserRequest.getPasswordConfirm();

        if(!isValidEmail(email)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Invalid email"), HttpStatus.BAD_REQUEST);
        }
        if(!userRepository.findByEmail(email).isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Email already in use"), HttpStatus.BAD_REQUEST);
        }
        if(username.length()>20){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Too long username"), HttpStatus.BAD_REQUEST);
        }
        if(!isValidUsername(username)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Invalid username"), HttpStatus.BAD_REQUEST);
        }
        if(!userRepository.findByUsername(username).isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Username already in use"), HttpStatus.BAD_REQUEST);
        }
        if(!isContainsUpperCase(password) || !isContainsLowerCase(password)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Unsecure password"), HttpStatus.BAD_REQUEST);
        }
        if(!password.equals(passwordConfirm)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Password and password confirm do not match"), HttpStatus.BAD_REQUEST);
        }
        String password_encoded = passwordEncoder.encode(password);


        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password_encoded);
        user.setRoles(List.of(addRoleToUser("ROLE_USER")));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    private boolean isValidUsername(String username){
        return !username.isEmpty() && username.matches("^[\\w]+$");
    }
    private boolean isValidEmail(String email){
        return !email.isEmpty() && email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$");
    }
    private Role addRoleToUser(String RoleName) throws RoleNotFoundException {
        return roleRepository.findByName(RoleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }
    private boolean isContainsUpperCase(String s){
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
    private boolean isContainsLowerCase(String s){
        for(char c : s.toCharArray()){
            if(Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }


}
