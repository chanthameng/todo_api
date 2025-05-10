package com.example.todo_api.controller.auth;

import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.service.user.AppUserServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RequestMapping("/api/todo/v1")
public class ProfileController {
    private final AppUserServiceImp appUserService;
    @GetMapping("/profile")
    ResponseEntity<?> getProfileByUserId(){
        System.out.println(currentId());
            AppUser appUser = appUserService.getUserByCurentId(currentId());
        return ResponseEntity.ok().body(appUser);
    }
    public Integer currentId() {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
