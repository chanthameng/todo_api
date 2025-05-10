package com.example.todo_api.model.user;
import com.example.todo_api.model.utils.MessageConstance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = MessageConstance.BLANK_FIELD)
    @NotNull(message = MessageConstance.NULL_FIELD)
    private String email;

    @NotBlank(message = MessageConstance.BLANK_FIELD)
    @NotNull(message = MessageConstance.NULL_FIELD)
    private String password;
}