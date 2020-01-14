package com.example.springbootweb1.exception;

public class UserNotExitException extends RuntimeException {

    public UserNotExitException() {
        super("用户不存在!");
    }
}
