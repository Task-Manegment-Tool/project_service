package com.blue.project_service.services;

import com.blue.project_service.models.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "TASK-SERVICE")
public interface TaskService {

    @GetMapping("/task/projectId/{projectId}")
     ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable("projectId") Integer projectId);
}
