package com.example.todo_api.model.workspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDto {
   Integer workSpaceId;
   String workspaceName;
   Boolean isFavorite;

}
