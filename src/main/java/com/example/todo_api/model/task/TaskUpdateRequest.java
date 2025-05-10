package com.example.todo_api.model.task;

import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskUpdateRequest {
    @NotNull(message = MessageConstance.NULL_FIELD)
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    String taskTitle;
    @NotNull(message = MessageConstance.NULL_FIELD)
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    String description;
    @NotNull(message = MessageConstance.NULL_FIELD)
    Timestamp startDate;
    @NotNull(message = MessageConstance.NULL_FIELD)
    Timestamp dueDate;
    @NotNull(message = MessageConstance.NULL_FIELD)
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    String tag;
}
