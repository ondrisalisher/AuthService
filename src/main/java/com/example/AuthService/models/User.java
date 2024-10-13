package com.example.AuthService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Email(message = "Invalid email")
    private String email;

    @Column(unique = true)
    @Size(max=20, message = "Long username")
    private String username;

    private String password;

    @ManyToMany
    Collection<User> friends;

    @ManyToMany
    Collection<Role> roles;
}
