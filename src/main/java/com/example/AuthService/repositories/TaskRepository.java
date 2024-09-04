package com.example.AuthService.repositories;

import com.example.AuthService.models.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaSpecificationExecutor<Task>, CrudRepository<Task, Long> {
}
