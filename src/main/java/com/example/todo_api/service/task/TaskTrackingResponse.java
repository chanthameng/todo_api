package com.example.todo_api.service.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskTrackingResponse {
    Integer workspace_id;
    Integer year;
    Integer month;
    Integer status;
    Integer task_count;
}
