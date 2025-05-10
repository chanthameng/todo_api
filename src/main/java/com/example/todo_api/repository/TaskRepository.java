package com.example.todo_api.repository;

import com.example.todo_api.model.task.*;
import com.example.todo_api.model.workspace.Workspace;
import com.example.todo_api.model.workspace.WorkspaceUpdateDto;
import com.example.todo_api.service.task.TaskTrackingResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {
    @Results(id = "task", value = {
            @Result(property = "taskId", column = "task_id"),
            @Result(property = "taskTitle", column = "task_title"),
            @Result(property = "description",column = "description"),
            @Result(property = "startDate",column = "start_date"),
            @Result(property = "dueDate", column = "due_date"),
            @Result(property = "tag",column = "tag"),
            @Result(property = "status", column = "status"),
            @Result(property = "workspaceId", column = "workspace_id")

    })
    @Select("SELECT * FROM tasks WHERE workspace_id = #{id}")
    List<Task> getAllTaskByWorkspaceId(@Param("id") Integer id);

    @ResultMap("task")
    @Select("INSERT INTO tasks (task_title,description,start_date,due_date,tag,workspace_id)"
            + "VALUES(#{request.taskTitle},#{request.description},#{request.startDate},#{request.dueDate},#{request.tag},#{request.workspaceId}) returning * ")
    Task insertTask(@Param("request")TaskRequest taskRequest);
//UPDATE tasks SET task_title='aaa',description='ddd',start_date=' 2024-03-20 11:31:08.843000',due_date='2024-03-20 11:31:08.843000',tag='ddd',status=2 WHERE task_id=2;
    @Select("""
        UPDATE tasks SET 
        task_title = #{request.taskTitle},
        description= #{request.description},
        start_date= #{request.startDate},
        due_date= #{request.dueDate},
        tag=#{request.tag}
        WHERE task_id = #{id} returning *
            """)
    @ResultMap("task")
    Task updateWorkspace(@Param("id")Integer taskId, @Param("request") TaskUpdateRequest workspaceUpdateDto);

    @Update("""
        UPDATE tasks SET 
        status = #{status.status}
        WHERE task_id = #{id} 
            """)
    void updateStatus(@Param("id") Integer taskId,@Param("status") StatusRequest status);

    //SELECT COUNT(*) FROM tasks WHERE task_id = 7;
    @Select("SELECT COUNT(*) FROM tasks WHERE task_id =#{id}")
    Integer getTaskCountById(@Param("id") Integer taskId);

    //
    @Select("""
            SELECT
                workspace_id,
                EXTRACT(YEAR FROM due_date) AS year,
                EXTRACT(MONTH FROM due_date) AS month,
                status,
                COUNT(*) AS task_count
            FROM
                tasks
            WHERE
                            EXTRACT(MONTH FROM due_date) = #{month}-- Replace <your_desired_month> with the desired month value
              AND workspace_id = #{workspace_id}
            GROUP BY
                workspace_id,
                EXTRACT(YEAR FROM due_date),
                EXTRACT(MONTH FROM due_date),
                status
            ORDER BY
                workspace_id,
                year,
                month,
                status;
            """)
    List<TaskTrackingResponse> getTaskTracking(@Param("month") Integer month_order,@Param("workspace_id") Integer id);


    @Results(id = "taskCount", value = {
            @Result(property = "status", column = "status"),
            @Result(property = "count", column = "task_count"),

    })
    @Select("""
            SELECT
            status,
            COUNT(*) AS task_count
            FROM 
            tasks
            WHERE
            EXTRACT(MONTH FROM due_date) = #{month}
            AND workspace_id = #{workspace_id}
            GROUP BY
            status
            ORDER BY 
            status
            
            """)
    List<TaskCount> taskCount(@Param("month") Integer month,@Param("workspace_id") Integer workspace_id);

}
