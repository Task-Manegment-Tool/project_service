package com.blue.project_service.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateProjectRequest {
    private String projectName;
    private String client;
    private String domain;
    private LocalDate startDate;
}
