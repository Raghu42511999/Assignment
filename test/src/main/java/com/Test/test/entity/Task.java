package com.Test.test.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Creator of the task

    private String title;
    private String description;

    private String status = "Pending"; // Task status

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}

