package com.example.todo_api.model.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Workspace {
    Integer workSpaceId;
    String workspaceName;
    Integer userId;
    Boolean isFavorite;
}
