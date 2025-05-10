package com.example.todo_api.model.task;

import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusRequest {
    @NotNull(message = MessageConstance.NULL_FIELD)
    Integer status;
}
