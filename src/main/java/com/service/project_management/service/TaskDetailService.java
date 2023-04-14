package com.service.project_management.service;


import com.service.project_management.Entities.*;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.ProjectDto;
import com.service.project_management.dto.TaskDtos.TaskDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TaskDetailService {





    @Autowired
    TaskDetailRepo taskDetailRepo;

    @Autowired
    private ModelMapper modelMapper;



    //Crud of task
     public TaskDetailDto createTask(TaskDetailDto taskDetailDto){

         TaskDetails tc=this.dtoToTask1(taskDetailDto);

         TaskDetails savedUser=this.taskDetailRepo.save(tc);

         return  this.taskToDto1(savedUser);


     }



    public Optional<TaskDetailDto> updateTask(Integer taskId, TaskDetailDto taskDetailDto) {


        TaskDetails taskDetails = this.taskDetailRepo.findById(taskId).get();


        if (Objects.nonNull(taskDetailDto.getTaskName())
                && !"".equalsIgnoreCase(taskDetailDto.getTaskName())) {

            taskDetails.setTaskName(taskDetailDto.getTaskName());
        }

        if (Objects.nonNull(taskDetailDto.getTaskStatus())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getTaskStatus()))) {
            taskDetails.setTaskStatus(taskDetailDto.getTaskStatus());
        }


        if (Objects.nonNull(taskDetailDto.getTaskDeadLine())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getTaskDeadLine()))) {
            taskDetails.setTaskDeadLine(taskDetailDto.getTaskDeadLine());
        }

        if (Objects.nonNull(taskDetailDto.getTaskStartingDate())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getTaskStartingDate()))) {
            taskDetails.setTaskStartingDate(taskDetailDto.getTaskStartingDate());
        }

        if (Objects.nonNull(taskDetailDto.getAllocatedBudget())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getAllocatedBudget()))) {
            taskDetails.setAllocatedBudget(taskDetailDto.getAllocatedBudget());
        }

        if (Objects.nonNull(taskDetailDto.getContractorId())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getContractorId()))) {

            Contractor contractor=new Contractor();
            contractor.setContractorId(taskDetailDto.getContractorId());
            taskDetails.setContractor(contractor);


        }

        if (Objects.nonNull(taskDetailDto.getProjectId())
                && !"".equalsIgnoreCase(String.valueOf(taskDetailDto.getProjectId()))) {

            Project project=new Project();
            project.setProjectId(taskDetailDto.getProjectId());
            taskDetails.setProject(project);


        }
        TaskDetails taskDetails1=this.taskDetailRepo.save(taskDetails);

        return Optional.ofNullable(this.modelMapper.map(taskDetails1, TaskDetailDto.class));

    }

    public ResponseEntity<Boolean> deleteTask(Integer taskId) {
        boolean isDeleted = false;

        this.taskDetailRepo.deleteById(taskId);
        isDeleted = true;
        return ResponseEntity.ok(isDeleted);


    }











//    public List<TaskDetails> getTaskDetailOfProject(Integer project_id) {
//        List<TaskDetails> projects = taskDetailRepo.getProjectTaskDetail(project_id);
//        List<TaskDetails> ty=projects.get()
//        return  projects;
//    }
//
//
//    public List<TaskDetailDto> getSomeDetail(Integer project_id) {
//        List<TaskDetails> tdo = taskDetailRepo.getProjectTaskDetail(project_id);
//        List<TaskDetailDto> tyd= tdo.stream().map(td -> this.taskToDto(td)).collect(Collectors.toList());
//
//
//
//
//        return  tyd;
//    }
//



    public TaskDetailDto taskToDto(TaskDetails taskDetails){
        TaskDetailDto td=this.modelMapper.map(taskDetails,TaskDetailDto.class);
        td.setTaskId(taskDetails.getTaskId());
        td.setContractorName(taskDetails.getContractor().getContractorName());
        td.setContractorId(taskDetails.getContractor().getContractorId());
        td.setTaskName(taskDetails.getTaskName());
        td.setTaskStartingDate(taskDetails.getTaskStartingDate());
        td.setTaskDeadLine(taskDetails.getTaskDeadLine());

//
//        TaskDetailDto td2=this.modelMapper.map(taskDetails,TaskDetailDto.class);
//        td2.setTaskId(taskDetails.getTaskId());
//        td2.setTaskName(taskDetails.getTaskName());
//        td2.setTaskDeadLine(taskDetails.getTaskDeadLine());
//        td2.setTaskStartingDate(taskDetails.getTaskStartingDate());
//        td2.setContractorId(taskDetails.getContractor().getContractorId());
//        td2.setProjectId(taskDetails.getProject().getProjectId());
//        td2.setTaskStatus(taskDetails.getTaskStatus());

//        List<TaskDetailDto> result=new ArrayList<>();
//         result.add(td);
//         result.add(td2);

        return  td;
    }

    public TaskDetails dtoToTask(TaskDetailDto taskDetailDto){
         TaskDetails td=this.modelMapper.map(taskDetailDto,TaskDetails.class);
         return td;

    }

    public TaskDetailDto taskToDto1(TaskDetails taskDetails){
         TaskDetailDto td2=this.modelMapper.map(taskDetails,TaskDetailDto.class);
         td2.setTaskId(taskDetails.getTaskId());
         td2.setTaskName(taskDetails.getTaskName());
         td2.setTaskStatus(taskDetails.getTaskStatus());
         td2.setProjectId(taskDetails.getProject().getProjectId());
         td2.setTaskDeadLine(taskDetails.getTaskDeadLine());
        td2.setTaskStartingDate(taskDetails.getTaskStartingDate());
        td2.setContractorId(taskDetails.getContractor().getContractorId());
        td2.setAllocatedBudget(taskDetails.getAllocatedBudget());


         return  td2;

    }

    public TaskDetails dtoToTask1(TaskDetailDto taskDetailDto){
         TaskDetails td1=this.modelMapper.map(taskDetailDto,TaskDetails.class);

         td1.setTaskId(taskDetailDto.getTaskId());
         td1.setTaskStatus(taskDetailDto.getTaskStatus());
         td1.setTaskName(taskDetailDto.getTaskName());
         td1.setAllocatedBudget(taskDetailDto.getAllocatedBudget());
         td1.setTaskStartingDate(taskDetailDto.getTaskStartingDate());
         td1.setTaskDeadLine(taskDetailDto.getTaskDeadLine());


         //second method if you want to set data in to object or in service method do this below things

        Contractor co = new Contractor();
        co.setContractorId(taskDetailDto.getContractorId());
        td1.setContractor(co);

        Project p=new Project();
        p.setProjectId(taskDetailDto.getProjectId());
        td1.setProject(p);

        return td1;

    }



}
