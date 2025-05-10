package com.example.todo_api.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUser implements UserDetails {
private Integer id;
private String firstname;
private String lastname;
private String gender;
private String profile_url;
private String email;
private String password;
private Boolean isEnabled;
private Boolean isLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return   new ArrayList<>(){{
            add(new SimpleGrantedAuthority("ROLE_USER"));
        }};
    }


    //uniq for login
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
