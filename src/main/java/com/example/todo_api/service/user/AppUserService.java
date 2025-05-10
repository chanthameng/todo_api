package com.example.todo_api.service.user;

import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.user.AppUserRequest;
import com.example.todo_api.model.workspace.WorkspaceRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;

public interface AppUserService {
    AppUser insertNewUser(AppUserRequest appUserRequest);
    public boolean isAlreadyExited(String email);

    UserDetails getUserByCurentId(Integer currentId);
}
