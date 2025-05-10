package com.example.todo_api.service.user;

//import com.example.todo_api.configuration.exception.FieldCannotEmpty;

import com.example.todo_api.configuration.exception.UserAlreadyExistException;
import com.example.todo_api.model.user.AppUser;
import com.example.todo_api.model.user.AppUserRequest;
import com.example.todo_api.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class AppUserServiceImp implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper = new ModelMapper();



    public AppUserServiceImp(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser insertNewUser(AppUserRequest appUserRequest) {


        if (appUserRepository.isEmailAlreadyExist(appUserRequest.getEmail())) {
            throw new UserAlreadyExistException("Email already exist");
        } else {
    String pass = passwordEncoder.encode(appUserRequest.getPassword());
    appUserRequest.setPassword(pass);
            return appUserRepository.insertNewUser(appUserRequest);
        }

    }

    @Override
    public boolean isAlreadyExited(String email) {
        return appUserRepository.isEmailAlreadyExist(email);
    }

    @Override
    public AppUser getUserByCurentId(Integer currentId) {
        return appUserRepository.getUserById(currentId);
    }


    //find user in database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.getAppUserByEmail(username);
    }
}
