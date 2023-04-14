package com.service.project_management.Controllers;


import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.ProjectDto;
import com.service.project_management.dto.TaskDtos.TaskDetailDto;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.TaskDetailService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/taskofproject")
public class TaskDetailController {

    @Autowired
    TaskDetailService taskDetailService;
    @Autowired
    TaskDetailRepo taskDetailRepo;

//    @GetMapping("/{projectId}/")
//    public ResponseEntity<Object> getTaskDetail(@PathVariable Integer projectId){
//
//
//        List<TaskDetails> Taskofproject = taskDetailService.getTaskDetailOfProject(projectId);
//
//        return ResponseEntity.ok().body(Map.of("message","success","data",Taskofproject));
//
//    }
//
//    @GetMapping("/requireDetail/{projectId}/")
//    public ResponseEntity<Object> getDetail(@PathVariable Integer projectId){
//
//
//        List<TaskDetailDto> Taskofproject1 = taskDetailService.getSomeDetail(projectId);
//
//        return ResponseEntity.ok().body(Map.of("taskName","success","data",Taskofproject1));
//
//    }


    //create taskdetails
     @PostMapping("/create-task")
     public ResponseEntity<Object> createTask(@RequestBody TaskDetailDto taskDetailDto) {


         try {
             TaskDetailDto taskDetails1 = this.taskDetailService.createTask(taskDetailDto);
             return ResponseEntity.ok(HttpStatus.OK);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body("Unable to create Task " + e);
         }
     }

    @PutMapping("/update-task/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable("taskId") Integer taskId, @RequestBody TaskDetailDto taskDetailDto) {
        Optional<TaskDetails> mytaskdetail = taskDetailRepo.findById(taskId);
        if(!mytaskdetail.isPresent()){
            throw new resourceNotFoundException("TaskDetail", "TaskID", taskId);
        }else{
            Optional<TaskDetailDto> taskdetailupdate = taskDetailService.updateTask(taskId, taskDetailDto);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable("taskId")Integer taskId){
        Optional<TaskDetails> mytaskdetail = taskDetailRepo.findById(taskId);
        if(!mytaskdetail.isPresent()){
            throw new resourceNotFoundException("TaskDetail", "TaskID", taskId);
        }else{
        this.taskDetailService.deleteTask(taskId);
        return ResponseEntity.ok().body("Task is deleted successfully");
        }
    }





}
