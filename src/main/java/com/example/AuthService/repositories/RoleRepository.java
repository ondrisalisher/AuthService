package com.example.AuthService.repositories;

import com.example.AuthService.models.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaSpecificationExecutor<Role>, CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
