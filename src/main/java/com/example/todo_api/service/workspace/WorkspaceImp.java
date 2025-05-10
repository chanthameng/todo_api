package com.example.todo_api.service.workspace;

import com.example.todo_api.configuration.exception.InvalidAccount;
import com.example.todo_api.configuration.exception.UserNotFoundException;
import com.example.todo_api.model.utils.MessageConstance;
import com.example.todo_api.model.workspace.*;
import com.example.todo_api.repository.WorkSpaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceImp implements WorkspaceService {
    private final WorkSpaceRepository workSpaceRepository;
    private final ModelMapper mapper = new ModelMapper();

    public WorkspaceImp(WorkSpaceRepository workSpaceRepository) {
        this.workSpaceRepository = workSpaceRepository;
    }

    @Override
    public List<WorkspaceDto> getWorkByUserId(Integer id) {
        if (workSpaceRepository.countUserById(id)!=0){
            List<Workspace> workspaceList = workSpaceRepository.getWorkByUserId(id);
            List<WorkspaceDto> workspaceDtos = workspaceList.stream()
                    .map(workspace -> mapper.map(workspace,WorkspaceDto.class))
                    .collect(Collectors.toList());

            return workspaceDtos;
        }else {
            throw new UserNotFoundException(MessageConstance.USER_NOT_FOUND);
        }

    }

    @Override
    public Workspace createWorkspace(WorkspaceRequest workspaceRequest,Integer id) {
        try{
            return workSpaceRepository.createWorkspace(workspaceRequest,id);
        } catch (Exception e){
            throw new UserNotFoundException(MessageConstance.USER_NOT_FOUND);
        }
    }

    @Override
    public boolean deleteTaskByWorkspaceId(Integer workspaceId) {
        return workSpaceRepository.taskByWorkspaceId(workspaceId);
    }

    @Override
    public Workspace deleteWorkspaceById(Integer workspaceId) {
        return workSpaceRepository.deleteWorkspace(workspaceId);
    }


    @Override
    public Workspace UpdateWorkspaceById(Integer workspaceId,WorkspaceUpdateDto workspaceUpdateDto) {
        if (workSpaceRepository.workspaceCount(workspaceId)!=0){
            return workSpaceRepository.updateWorkspace(workspaceId,workspaceUpdateDto);
        }else {
            throw new UserNotFoundException(MessageConstance.WORKSPACE_NOT_FOUND);
        }

    }

    @Override
    public Workspace addWorkspaceFavorite(FavoriteRequest favoriteRequest,Integer workspaceID) {
        if (workSpaceRepository.workspaceCount(workspaceID)!=0){
            return workSpaceRepository.addWorkspaceFavorite(favoriteRequest,workspaceID);
        }else {
            throw new UserNotFoundException(MessageConstance.WORKSPACE_NOT_FOUND);
        }

    }

}
