package com.example.todo_api.model.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageResponse<T> {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    public MessageResponse(LocalDateTime timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}

