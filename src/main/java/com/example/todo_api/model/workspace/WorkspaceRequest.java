package com.example.todo_api.model.workspace;

import com.example.todo_api.Const;
import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceRequest {
    @NotNull(message = MessageConstance.NULL_FIELD)
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    String workspaceName;
//    @NotNull(message = MessageConstance.NULL_FIELD)
//    Integer userId;
}
