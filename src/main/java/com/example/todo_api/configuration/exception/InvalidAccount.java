package com.example.todo_api.configuration.exception;

public class InvalidAccount extends RuntimeException{
    public InvalidAccount(String message) {
        super(message);
    }

}
