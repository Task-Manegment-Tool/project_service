package com.blue.project_service.exception;

public class ProjectWithUserIdNotFoundException extends RuntimeException {
    public ProjectWithUserIdNotFoundException(String message) {
        super(message);
    }
}
