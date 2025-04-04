package com.Test.test.repository;


import com.Test.test.entity.TaskApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskApprovalRepository extends JpaRepository<TaskApproval, Long> {
    List<TaskApproval> findByTaskIdAndStatus(Long taskId, String status);
}