package com.service.project_management.Controllers;
import com.service.project_management.Entities.Investor;
import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.InvestorRepo;
import com.service.project_management.Repositories.Investor_ProjectRepo;
import com.service.project_management.Repositories.ProjectRepo;
import com.service.project_management.dto.ProjectDto;
import com.service.project_management.dto.ProjectDtoCreate;
import com.service.project_management.dto.TaskDtos.TaskDetailDto;
import com.service.project_management.dto.TaskDtos.TaskDetailForTheProject;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.InvestorService;
import com.service.project_management.service.ProjectService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@Validated
public class ProjectController {

    @Autowired
    InvestorService investerService;
    @Autowired
    InvestorRepo investorRepo;

    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    Investor_ProjectRepo investor_ProjectRepo;

    @PostMapping(value = "/create-project", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(code = 500, message = "++++++++++++++++++")
    public ResponseEntity<Object> createProject(@RequestBody @Valid ProjectDtoCreate projectDto) {
      
        try {
            projectService.createProject(projectDto);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to create project " + e);
        }
    }

    @GetMapping("/totalProject")
    public Integer totaProject() {
        return projectRepo.countProject();
    }

    @GetMapping("/{projectId}")

    public ResponseEntity<Object> getProject(@PathVariable Integer projectId) {

        Optional<Project> project = projectService.getProjectById(projectId);

        if (!project.isPresent()) {
            throw new resourceNotFoundException("project", "projectId", projectId);
        } else {
            return ResponseEntity.ok().body(project);
        }

    }

    @GetMapping("/AllProjects")
    public ResponseEntity<Object> getAllProject() {
        return ResponseEntity.ok().body(this.projectService.getAllProject());
    }

    @GetMapping("/getAllProjectOfOneInvestor/{investorEmail}")
    public ResponseEntity<Object> getAllProject(@PathVariable("investorEmail") String investorEmail) {
        return ResponseEntity.ok().body(this.projectService.getAllProject(investorEmail));
    }

    @PutMapping("/update-project/{projectId}")
    public ResponseEntity<Object> updateProject(@PathVariable("projectId") String projectId,
            @RequestBody ProjectDtoCreate projectDto) throws Exception {
        Integer Id = Integer.parseInt(projectId);
        String result = projectService.updateProjectById(Id, projectDto);
        if (result.equals("success")) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete-project/{projectId}")
    public ResponseEntity<Object> deleteProject(@PathVariable Integer projectId) {
        try {
            investor_ProjectRepo.deleteByProjectId(projectId);
            projectRepo.deleteById(projectId);
            return ResponseEntity.ok().body("project is deleted");
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.badRequest().body("unable to delete project");
        }
    }

    @GetMapping("/project-investor/{projectId}")
    public ResponseEntity<Object> getInvestorForProject(@PathVariable("projectId") Integer projectId,
            @RequestParam(required = false, defaultValue = "0") Integer investorId) {
        Optional<Project> myproject = projectRepo.findById(projectId);
        if (!myproject.isPresent()) {
            throw new resourceNotFoundException("project", "projectID", projectId);
        } else {

            if (investorId.equals(0)) {
                List<Investor_Project> allInvestorOfProject = projectService.getAllInvestorProject(projectId);
                return ResponseEntity.ok().body(allInvestorOfProject);
            } else {
                Optional<Investor> myinvestor = investorRepo.findById(investorId);
                if (!myinvestor.isPresent()) {

                    throw new resourceNotFoundException("investor", "investorID", investorId);

                } else {
                    Optional<Investor_Project> allProjectRelatedToInvestor = projectService
                            .getProjectInvestor(projectId, investorId);
                    return ResponseEntity.ok().body(allProjectRelatedToInvestor);
                }
            }

        }
    }

    @GetMapping("/task-details/{projectId}/filterDates")
    public ResponseEntity<Object> getDetail(@PathVariable("projectId") Integer projectId,
            @RequestParam(defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate starting_date,
            @RequestParam(defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ending_date) {

        Optional<Project> myproject = projectRepo.findById(projectId);
        if (!myproject.isPresent()) {
            throw new resourceNotFoundException("project", "projectID", projectId);
        } else {
            if (starting_date == null && ending_date == null) {
                List<TaskDetails> Taskofproject1 = projectService.getSomeDetail(projectId);

                return ResponseEntity.ok().body(Taskofproject1);

            } else {
                List<TaskDetailForTheProject> tasklist = projectService.getTaskBetweenDates(projectId, starting_date,
                        ending_date);

                return ResponseEntity.ok().body(tasklist);
            }
        }
    }

    @GetMapping("/project-status-date/{startingDate/{endingDate}/{projectStatus}")
    public ResponseEntity<Object> getProjectStatusAndDate(
            @RequestParam(defaultValue = "0", required = false) Integer projectStatus,
            @RequestParam(defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate starting_date,
            @RequestParam(defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ending_date) {

        if (starting_date == null && ending_date == null) {
            List<ProjectDto> ProjectStatus = projectService.getProjectAccordingToStatus(projectStatus);

            return ResponseEntity.ok().body(ProjectStatus);

        } else if (projectStatus.equals(0)) {
            List<ProjectDto> tasklist = projectService.projectBtwnStartingAndEnding(starting_date, ending_date);

            return ResponseEntity.ok().body(tasklist);
        } else {
            List<ProjectDto> projectDetails = projectService.projectBasedOnStartingAndEndingDate(projectStatus,
                    starting_date, ending_date);

            return ResponseEntity.ok().body(projectDetails);
        }

    }

    @GetMapping("/project-task-dependency/{projectId}")
    public ResponseEntity<Object> getDependantTask(@PathVariable("projectId") Integer projectId) {
        List<TaskDetailDto> taskList = projectService.getTaskWithDependency(projectId);
        return ResponseEntity.ok().body(taskList);
    }

}
