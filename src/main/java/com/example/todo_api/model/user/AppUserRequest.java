package com.example.todo_api.model.user;

import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserRequest {
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    @NotNull(message = MessageConstance.NULL_FIELD)
    private String firstname;

    @NotBlank(message = MessageConstance.BLANK_FIELD)
    private String lastname;

    @NotNull(message = MessageConstance.NULL_FIELD)
    private String gender;
    private String profile_url;

    @Email(message = MessageConstance.INVALID_EMAIL)
    private String email;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = MessageConstance.STRONG_PASSWORD)
@NotBlank(message = MessageConstance.BLANK_FIELD)
    private String password;
}
