package com.service.project_management.service;


import com.service.project_management.Entities.*;
import com.service.project_management.Repositories.Investor_ProjectRepo;
import com.service.project_management.Repositories.ProjectLocationRepo;
import com.service.project_management.Repositories.ProjectRepo;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.ProjectDto;
import com.service.project_management.dto.TaskDtos.TaskDetailDto;
import com.service.project_management.dto.TaskDtos.TaskDetailForTheProject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {


    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    TaskDetailRepo taskDetailRepo;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Investor_ProjectRepo investorProjectRepo;

    @Autowired
    ProjectLocationRepo projectLocationRepo;


    public Optional<Project> getProjectById(Integer projectId) {
        Optional<Project> projects = projectRepo.findById(projectId);
        return projects;
    }

    public List<Project> getAllProject() {
        List<Project> allproject = projectRepo.findAll();
        return allproject;
    }


    public Optional<ProjectDto> updateProjectById(Integer projectId, ProjectDto projectDto) {


        Project project1 = this.projectRepo.findById(projectId).get();


        if (Objects.nonNull(projectDto.getProjectName())
                && !"".equalsIgnoreCase(projectDto.getProjectName())) {

            project1.setProjectName(projectDto.getProjectName());
        }

        if (Objects.nonNull(projectDto.getProjectStatus())
                && !"".equalsIgnoreCase(String.valueOf(projectDto.getProjectStatus()))) {
            project1.setProjectStatus(projectDto.getProjectStatus());
        }


        if (Objects.nonNull(projectDto.getProjectDeadline())
                && !"".equalsIgnoreCase(String.valueOf(projectDto.getProjectDeadline()))) {
            project1.setProjectDeadline(projectDto.getProjectDeadline());
        }

        if (Objects.nonNull(projectDto.getProjectStartingDate())
                && !"".equalsIgnoreCase(String.valueOf(projectDto.getProjectStartingDate()))) {
            project1.setProjectStartingDate(projectDto.getProjectStartingDate());
        }

        if (Objects.nonNull(projectDto.getProjectTypeName())
                && !"".equalsIgnoreCase(projectDto.getProjectTypeName())) {
            project1.setProjectTypeName(projectDto.getProjectTypeName());
        }

        if (Objects.nonNull(projectDto.getProjectLocationId())
                && !"".equalsIgnoreCase(String.valueOf(projectDto.getProjectLocationId()))) {

            ProjectLocation pro = new ProjectLocation();
            pro.setProjectLocationId(projectDto.getProjectLocationId());
            pro.setArea(projectDto.getArea());
            pro.setCity(projectDto.getCity());
            pro.setState(projectDto.getState());
            project1.setProjectLocation(pro);


        }
        Project project2 = this.projectRepo.save(project1);
        return Optional.ofNullable(this.modelMapper.map(project2, ProjectDto.class));

    }


    //For the project-investor
    public List<Investor_Project> getAllInvestorProject(Integer projectId) {
        List<Investor_Project> in = investorProjectRepo.findAllInvestorProject(projectId);

        return in;

    }

    public Optional<Investor_Project> getProjectInvestor(Integer projectId, Integer investorId) {
        Optional<Investor_Project> in2 = investorProjectRepo.findProjectByIp(investorId, projectId);
        return in2;
    }


    //for the Task details

    public List<TaskDetailDto> getSomeDetail(Integer project_id) {
        List<TaskDetails> tdo = taskDetailRepo.getProjectTaskDetail(project_id);
        List<TaskDetailDto> tyd = tdo.stream().map(td -> this.taskToDto(td)).collect(Collectors.toList());


        return tyd;
    }


    public List<TaskDetailForTheProject> getTaskBetweenDates(Integer projectId, LocalDate taskStartingDate, LocalDate taskDeadLine) {

        List<TaskDetails> td1 = taskDetailRepo.getTaskByDate(projectId, taskStartingDate.toString(), taskDeadLine.toString());
        List<TaskDetailForTheProject> tyd1 = td1.stream().map(td2 -> this.taskToDto3(td2)).collect(Collectors.toList());
//        for (TaskDetails taskDetails : td1) {
//            tyd1.add(this.taskToDto3(taskDetails));
//        }
        //
        return tyd1;
    }

    //project By status
    public List<ProjectDto> getProjectAccordingToStatus(Integer projectStatus) {
        List<Project> projectDetails = this.projectRepo.getProjectByStatus(projectStatus);
        List<ProjectDto> projectDetailStatus = projectDetails.stream().map(pd -> this.projectToDto(pd)).collect(Collectors.toList());
        return projectDetailStatus;

    }

    //project by date

    public List<ProjectDto> projectBtwnStartingAndEnding(LocalDate starting_date, LocalDate ending_date) {

        List<Project> pro1 = this.projectRepo.getProjectByDate(starting_date.toString(), ending_date.toString());
        List<ProjectDto> datepro = pro1.stream().map(pd2 -> this.projectToDto(pd2)).collect(Collectors.toList());

        return datepro;
    }

    public List<ProjectDto> projectBasedOnStartingAndEndingDate(Integer projectStatus, LocalDate starting_date, LocalDate ending_date) {

        List<Project> pro1 = this.projectRepo.getProjectByDateAndStatus(starting_date.toString(), ending_date.toString(), projectStatus);
        List<ProjectDto> datepro = pro1.stream().map(pd2 -> this.projectToDto(pd2)).collect(Collectors.toList());

        return datepro;
    }

    // get project with dependency

    public List<TaskDetailDto> getTaskWithDependency(Integer projectId) {
        List<TaskDetails> task = this.taskDetailRepo.getTaskByProject(projectId);
        List<TaskDetailDto> taskD = task.stream().map(td1 -> this.taskToDto(td1)).collect(Collectors.toList());

        List<TaskDetailDto> result = new ArrayList<>();
        List<Integer> taskIds = taskD.stream().map(t -> t.getTaskId()).distinct().collect(Collectors.toList());

        for (int i = 0; i < taskIds.size() - 1; i++) {

            if (task.get(i + 1).getTaskStartingDate().isBefore(task.get(i).getTaskDeadLine())) {
                System.out.println("found" + task.get(i + 1).getTaskId() + "id greater than " + task.get(i).getTaskId());
                Integer id = task.get(i + 1).getTaskId();
//
//                Integer id2=task.get(i).getTaskId();
//                List<TaskDetailDto> responsev1 = taskD.stream().filter(t -> t.getTaskId() == id2).collect(Collectors.toList());
//                for (TaskDetailDto dto : responsev1) {
//                    result.add(dto);
//                }


                List<TaskDetailDto> responsev = taskD.stream().filter(t -> t.getTaskId() == id).collect(Collectors.toList());
                for (TaskDetailDto dto : responsev) {
                    result.add(dto);
                }
            }
        }


        return result;


    }


    public Project createProject(Project projectDto) {
        // Project Pro = this.dtoToProject(projectDto);
        ProjectLocation projectLocation = projectDto.getProjectLocation();
        ProjectLocation projectLocation2 = projectLocationRepo.findExisitingRecord(projectLocation.getArea(),projectLocation.getCity(),projectLocation.getState());
        if(projectLocation2!=null){
            projectDto.setProjectLocation(projectLocation2);
        }
        Project savedUser = this.projectRepo.save(projectDto);
        return savedUser;
        // return this.projectToDto(savedUser);


    }

    public ProjectDto projectToDto(Project project) {
        ProjectDto td = this.modelMapper.map(project, ProjectDto.class);


        return td;
    }

    public Project dtoToProject(ProjectDto projectDto) {
        Project prod = this.modelMapper.map(projectDto, Project.class);


        return prod;
    }


    public TaskDetailDto taskToDto(TaskDetails taskDetails) {
        TaskDetailDto td = this.modelMapper.map(taskDetails, TaskDetailDto.class);
        td.setTaskId(taskDetails.getTaskId());
        td.setContractorName(taskDetails.getContractor().getContractorName());
        td.setContractorId(taskDetails.getContractor().getContractorId());
        td.setTaskName(taskDetails.getTaskName());
        td.setTaskStartingDate(taskDetails.getTaskStartingDate());
        td.setTaskDeadLine(taskDetails.getTaskDeadLine());
        td.setTaskStatus(taskDetails.getTaskStatus());
        td.setEmail(taskDetails.getContractor().getEmail());
        td.setJobRole(taskDetails.getContractor().getJobRole());


        return td;

    }


    public TaskDetailForTheProject taskToDto3(TaskDetails taskDetails) {
        TaskDetailForTheProject td4 = this.modelMapper.map(taskDetails, TaskDetailForTheProject.class);
        return td4;
    }


}
