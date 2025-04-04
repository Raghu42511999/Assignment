package com.Test.test.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TaskApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;

    private String status = "Pending"; // Approval status

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
}