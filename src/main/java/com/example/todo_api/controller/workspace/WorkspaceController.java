package com.example.todo_api.controller.workspace;

import com.example.todo_api.configuration.exception.InvalidAccount;
import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.utils.MessageResponse;
import com.example.todo_api.model.workspace.*;
import com.example.todo_api.service.workspace.WorkspaceImp;
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
public class WorkspaceController {
    private final WorkspaceImp workspaceImp;

    @PostMapping("/workspaces")
    public ResponseEntity<?> createWorkspace(@Valid @RequestBody WorkspaceRequest workspaceRequest) {
//        System.out.println(currentId());
        workspaceImp.createWorkspace(workspaceRequest,currentId());
        MessageResponse<?> workspaceResponse = new MessageResponse<>(LocalDateTime.now(), 200, "success", workspaceRequest);
        return ResponseEntity.ok().body(workspaceResponse);
    }

    @GetMapping("/workspaces")
    public ResponseEntity<?> getWorkSpaceByUserId( ) {
//        System.out.println(currentId());
        List<WorkspaceDto> workspaceList = workspaceImp.getWorkByUserId(currentId());
        MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", workspaceList);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("workspaces/{workspaceId}")
    ResponseEntity<?> deleteWorkspace(
            @PathVariable Integer workspaceId
    ) {
        boolean isTaskDelete = workspaceImp.deleteTaskByWorkspaceId(workspaceId);
        if (isTaskDelete) {
            Workspace workspace = workspaceImp.deleteWorkspaceById(workspaceId);
            MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", workspace);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.ok().body("There no workspace with id:"+workspaceId);
        }

    }

    @PutMapping("workspaces/{workspaceId}")
    ResponseEntity<?> updateWorkspace(
            @PathVariable Integer workspaceId,
            @RequestBody WorkspaceUpdateDto workspaceRequest
    ) {
        workspaceImp.UpdateWorkspaceById(workspaceId,workspaceRequest);
        MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", workspaceRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("workspaces/add-to-favorite/{workSpaceId}")
    ResponseEntity<?> addToFavorite(
            @RequestBody FavoriteRequest favoriteRequest,
            @PathVariable  Integer workSpaceId

    ){
        workspaceImp.addWorkspaceFavorite(favoriteRequest, workSpaceId);
        MessageResponse<?> response = new MessageResponse<>(LocalDateTime.now(), 200, "success", favoriteRequest);
        return ResponseEntity.ok().body(response);
    }


    public static Integer currentId() {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}

