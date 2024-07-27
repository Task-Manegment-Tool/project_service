package com.blue.project_service.transformers;

import com.blue.project_service.dtos.CreateProjectRequest;
import com.blue.project_service.models.Project;

public class ProjectTransformers {
    public static Project convertCreateProjectRequestToProject(CreateProjectRequest createProjectRequest) {
        return Project.builder()
                .projectName(createProjectRequest.getProjectName())
                .client(createProjectRequest.getClient())
                .domain(createProjectRequest.getDomain())
                .startDate(createProjectRequest.getStartDate())
                .userId(createProjectRequest.getUserId())
                .build();
    }
}
