package com.example.AuthService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user_id;

    @ManyToOne
    private User creator_id;

    private String task_name;

    private String task_text;

    private LocalDateTime notification_time;

    private LocalDateTime deadline;
}
