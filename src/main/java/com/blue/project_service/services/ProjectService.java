package com.blue.project_service.services;

import com.blue.project_service.dtos.CreateProjectRequest;
import com.blue.project_service.exception.ProjectWithUserIdNotFoundException;
import com.blue.project_service.exception.UserIdNotFoundException;
import com.blue.project_service.models.Project;
import com.blue.project_service.models.Task;
import com.blue.project_service.models.User;
import com.blue.project_service.repositorys.ProjectRepository;
import com.blue.project_service.transformers.ProjectTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private  TaskService taskService;

    @Autowired
    private  UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String createProject(CreateProjectRequest createProjectRequest) {
        Project project = ProjectTransformers.convertCreateProjectRequestToProject(createProjectRequest);
        Optional<User> optionalUser = Optional.ofNullable(userService.findUserbyId(project.getUserId()).getBody());
        try {
            optionalUser.get();
        }
        catch (Exception e){
            throw new UserIdNotFoundException("user with user id: " + project.getUserId() + " not present in user table");
        }
       Project cretedProject = projectRepository.save(project);
        return "project save into the database successfully \r\n " +
                "project id is : " + cretedProject.getId();
    }

    public Project getProjectById(Integer projectId) {

        Project optionalProject = projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("Project not Found"));
            List<Task> taskList =taskService.getTasksByProjectId(projectId).getBody();
            optionalProject.setTaskList(taskList);

            return optionalProject;

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

    public Project getProjectByUserId(Long userId) {
        Optional<Project> optionalProject = Optional.ofNullable(projectRepository.findByUserId(userId));
        Project project;
        if (optionalProject.isPresent()) {
            project = optionalProject.get();
        } else {
            throw new ProjectWithUserIdNotFoundException("user id: " + userId + " not present into the database");
        }
        return project;
    }
}
