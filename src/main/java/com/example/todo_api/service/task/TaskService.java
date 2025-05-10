package com.example.todo_api.service.task;

import com.example.todo_api.model.task.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskService {
    List<Task> getAllTaskByWorkspaceId(Integer id);
    Task insertTask(TaskRequest taskRequest);
    Task updateTask(Integer taskId, TaskUpdateRequest taskRequest);
    void changeStatus(Integer taskId, StatusRequest status);
    List<TaskTrackingResponse>  getTrackingTask(Integer month,Integer workspaceId);
List<TaskCount> getTaskCount(Integer month,Integer workspace_id);
}
