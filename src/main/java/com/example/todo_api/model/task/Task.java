package com.example.todo_api.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    Integer taskId;
    String taskTitle;
    String description;
    Timestamp startDate;
    Timestamp dueDate;
    String tag;
    Integer status;
    Integer workspaceId;
}
