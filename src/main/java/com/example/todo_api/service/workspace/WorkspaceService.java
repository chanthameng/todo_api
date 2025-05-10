package com.example.todo_api.service.workspace;

import com.example.todo_api.model.workspace.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> getWorkByUserId(Integer id);
    Workspace createWorkspace(WorkspaceRequest workspaceRequest,Integer id);

    boolean deleteTaskByWorkspaceId(Integer workspaceId);

    Workspace deleteWorkspaceById(Integer workspaceId);
    Workspace UpdateWorkspaceById(Integer workspaceId,WorkspaceUpdateDto workspaceUpdateDto);
    Workspace addWorkspaceFavorite(FavoriteRequest favoriteRequest,Integer workspaceId);
}
