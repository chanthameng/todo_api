package com.example.todo_api.controller.task;

import com.example.todo_api.model.task.*;
import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.utils.MessageResponse;
import com.example.todo_api.model.workspace.WorkspaceDto;
import com.example.todo_api.repository.TaskRepository;
import com.example.todo_api.service.task.TaskService;
import com.example.todo_api.service.task.TaskServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Controller
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RequestMapping("/api/todo/v1")
public class TaskController {
    private final TaskService taskServiceImp;

    @PostMapping("/tasks")
    public ResponseEntity<?> addTaskToWorkspace(@Valid @RequestBody TaskRequest taskRequest) {
        MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", taskServiceImp.insertTask(taskRequest));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getTaskByWorkspaceId(@RequestParam Integer workspaceId) {
        List<Task> taskList = taskServiceImp.getAllTaskByWorkspaceId(workspaceId);
        MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", taskList);
//        System.out.println(currentId());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(
            @PathVariable Integer taskId,
            @RequestBody TaskUpdateRequest taskRequest
    ) {
        taskServiceImp.updateTask(taskId, taskRequest);
        return ResponseEntity.ok().body(taskRequest);
    }

    @PutMapping("/tasks/change-status/{taskId}")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable Integer taskId,
            @RequestBody StatusRequest taskStatus
    ) {
        taskServiceImp.changeStatus(taskId, taskStatus);
        return ResponseEntity.ok().body(taskStatus);
    }


    @GetMapping("/tasks/get-monthly-tracking/{month}/{workspace_id}")
    public ResponseEntity<?> getTracking(
            @PathVariable Integer month,
            @PathVariable Integer workspace_id){
        return ResponseEntity.ok(taskServiceImp.getTrackingTask(month, workspace_id));
    }

    @GetMapping("tasks/getTaskCountByStatus/{month}/{workspace_id}")
    public ResponseEntity<?> getTaskCount(
            @PathVariable Integer month,
            @PathVariable Integer workspace_id
    ){
        List<TaskCount> taskCounts = taskServiceImp.getTaskCount(month, workspace_id);
        MessageResponse<?> messageResponse = new MessageResponse<>(LocalDateTime.now(),200,"success",taskCounts);
        return ResponseEntity.ok(messageResponse);
    }

}
