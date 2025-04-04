package com.Test.test.service;

import com.Test.test.entity.Task;
import com.Test.test.entity.TaskApproval;
import com.Test.test.entity.User;
import com.Test.test.repository.TaskApprovalRepository;
import com.Test.test.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskApprovalRepository taskApprovalRepository;

    public Task createTask(User user, String title, String description, List<User> approvers) {
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        taskRepository.save(task);

        // Create TaskApproval records for each approver
        for (User approver : approvers) {
            TaskApproval taskApproval = new TaskApproval();
            taskApproval.setTask(task);
            taskApproval.setApprover(approver);
            taskApprovalRepository.save(taskApproval);
        }

        return task;
    }

    public void approveTask(Long taskId, User approver) {
        List<TaskApproval> approvals = taskApprovalRepository.findByTaskIdAndStatus(taskId, "Pending");

        if (!approvals.isEmpty()) {
            TaskApproval taskApproval = approvals.get(0);
            taskApproval.setStatus("Approved");
            taskApprovalRepository.save(taskApproval);

            // Check if all 3 approvers have approved the task
            List<TaskApproval> allApproved = taskApprovalRepository.findByTaskIdAndStatus(taskId, "Approved");
            if (allApproved.size() == 3) {
                Task task = taskApproval.getTask();
                task.setStatus("Approved");
                taskRepository.save(task);
            }
        }
    }
}