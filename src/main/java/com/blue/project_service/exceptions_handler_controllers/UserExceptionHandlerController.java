package com.blue.project_service.exceptions_handler_controllers;

import com.blue.project_service.exception.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandlerController {
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<String> handleUserIdNotFoundException(UserIdNotFoundException userIdNotFoundExceptioner) {
        return new ResponseEntity<>(userIdNotFoundExceptioner.getMessage(), HttpStatus.OK);
    }
}
