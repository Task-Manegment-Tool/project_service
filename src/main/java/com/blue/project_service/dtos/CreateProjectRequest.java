package com.blue.project_service.dtos;

import com.blue.project_service.models.Task;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateProjectRequest {
    private String projectName;
    private String client;
    private String domain;
    private LocalDate startDate;
    private  Long userId;
    private List<Task> taskList;
}
