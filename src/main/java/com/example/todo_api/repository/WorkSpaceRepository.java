package com.example.todo_api.repository;

import com.example.todo_api.model.workspace.FavoriteRequest;
import com.example.todo_api.model.workspace.Workspace;
import com.example.todo_api.model.workspace.WorkspaceRequest;
import com.example.todo_api.model.workspace.WorkspaceUpdateDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkSpaceRepository {
    @Results(id = "workspaceMap", value = {
            @Result(property = "workSpaceId", column = "workspace_id"),
            @Result(property = "workspaceName", column = "workspace_name"),
            @Result(property = "isFavorite",column = "is_favorite"),
            @Result(property = "userId", column = "user_id"),

    })


    @Select("""
            SELECT * FROM workspaces WHERE user_id = #{id}
            """)
    List<Workspace> getWorkByUserId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM users WHERE user_id = #{id};")
    Integer countUserById(@Param("id") Integer id);

    @Select("INSERT INTO workspaces (workspace_name,user_id)"
            + "VALUES(#{request.workspaceName},#{userId}) returning * ")
    @ResultMap("workspaceMap")
    Workspace createWorkspace(@Param("request") WorkspaceRequest workspaceRequest,@Param("userId") Integer id);

    @Delete("DELETE FROM tasks WHERE workspace_id = #{id};")
    boolean taskByWorkspaceId(@Param("id") Integer workspaceId);
    @Select("""
           DELETE FROM workspaces WHERE workspace_id = #{id} returning *
            """)
    Workspace deleteWorkspace(@Param("id") Integer id);
    @Select("""
        UPDATE workspaces SET workspace_name = #{request.workspaceName} WHERE workspace_id = #{id} returning *
            """)
    @ResultMap("workspaceMap")
    Workspace updateWorkspace(@Param("id")Integer workspaceId,@Param("request") WorkspaceUpdateDto workspaceUpdateDto);

    @Select("SELECT COUNT(*) FROM workspaces WHERE workspace_id = #{id};")
    Integer workspaceCount(@Param("id") Integer id);

    @Select("""
        UPDATE workspaces SET is_favorite = #{request.isFavorite} WHERE workspace_id = #{workspaceId} returning *
            """)
    @ResultMap("workspaceMap")
    Workspace addWorkspaceFavorite(@Param("request") FavoriteRequest favoriteRequest,@Param("workspaceId") Integer workspaceId);



}
