package com.blue.project_service.services;

import com.blue.project_service.dtos.CreateProjectRequest;
import com.blue.project_service.models.Project;
import com.blue.project_service.repositorys.ProjectRepository;
import com.blue.project_service.transformers.ProjectTransformers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String createProject(CreateProjectRequest createProjectRequest) {
        Project project = ProjectTransformers.convertCreateProjectRequestToProject(createProjectRequest);
        projectRepository.save(project);
        return "project save into the database successfully";
    }

    public Project getProjectById(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = optionalProject.get();
        return project;
    }

    public String updateProjectById(Integer projectId, Project projectUpdate) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            if (projectUpdate.getProjectName() != null) {
                project.setProjectName(projectUpdate.getProjectName());
            }
            if (projectUpdate.getClient() != null) {
                project.setClient(projectUpdate.getClient());
            }
            if (projectUpdate.getDomain() != null) {
                project.setDomain(projectUpdate.getDomain());
            }
            if (projectUpdate.getStartDate() != null) {
                project.setStartDate(projectUpdate.getStartDate());
            }
            projectRepository.save(project);
            return "project updated succesfully";
        } else {
            return "project by id " + projectId + "not found";
        }

    }

    public String deleteProjectById(Integer projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return "project with projectId: " + projectId + " is not present";
        } else {
            projectRepository.deleteById(projectId);
            return "project with projectId: " + projectId + " deleted succesfully";
        }
    }
}
