package com.example.todo_api.service.task;

import com.example.todo_api.configuration.exception.TaskNotFoundException;
import com.example.todo_api.configuration.exception.UserNotFoundException;
import com.example.todo_api.model.task.*;
import com.example.todo_api.model.utils.MessageConstance;
import com.example.todo_api.model.utils.MessageResponse;
import com.example.todo_api.repository.TaskRepository;
import com.example.todo_api.repository.WorkSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final WorkSpaceRepository workSpaceRepository;

    public TaskServiceImp(TaskRepository taskRepository, WorkSpaceRepository workSpaceRepository) {
        this.taskRepository = taskRepository;
        this.workSpaceRepository = workSpaceRepository;
    }

    @Override
    public List<Task> getAllTaskByWorkspaceId(Integer id) {
        if (workSpaceRepository.workspaceCount(id) != 0) {
            return taskRepository.getAllTaskByWorkspaceId(id);
        } else {
            throw new UserNotFoundException(MessageConstance.WORKSPACE_NOT_FOUND);
        }

    }

    @Override
    public Task insertTask(TaskRequest taskRequest) {
        if (workSpaceRepository.workspaceCount(taskRequest.getWorkspaceId()) != 0) {
            return taskRepository.insertTask(taskRequest);
        } else {
            throw new UserNotFoundException(MessageConstance.WORKSPACE_NOT_FOUND);
        }

    }

    @Override
    public Task updateTask(Integer taskId, TaskUpdateRequest taskRequest) {
        return taskRepository.updateWorkspace(taskId, taskRequest);
    }

    @Override
    public void changeStatus(Integer taskId, StatusRequest status) {
        System.out.println(taskRepository.getTaskCountById(taskId));
        if (taskRepository.getTaskCountById(taskId) !=0){
            taskRepository.updateStatus(taskId, status);
        }else {
            throw new TaskNotFoundException(MessageConstance.TASK_NOT_FOUND);
        }

    }

    @Override
    public List<TaskTrackingResponse>  getTrackingTask(Integer month, Integer workspaceId) {
        return taskRepository.getTaskTracking(month,workspaceId);
    }

    @Override
    public List<TaskCount> getTaskCount(Integer month, Integer workspace_id) {
        return taskRepository.taskCount(month, workspace_id);
    }
}
