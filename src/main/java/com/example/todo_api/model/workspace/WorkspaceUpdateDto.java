package com.example.todo_api.model.workspace;

import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class WorkspaceUpdateDto {
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    @NotNull(message = MessageConstance.NULL_FIELD)
    String workspaceName;
}
