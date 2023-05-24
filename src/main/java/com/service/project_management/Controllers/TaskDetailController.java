package com.service.project_management.Controllers;

import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.ProjectDto;
import com.service.project_management.dto.TaskDtos.TaskDetailDto;
import com.service.project_management.dto.TaskDtos.TaskDetailForCreate;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.ProjectService;
import com.service.project_management.service.TaskDetailService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taskofproject")
public class TaskDetailController {

    @Autowired
    TaskDetailService taskDetailService;
    @Autowired
    TaskDetailRepo taskDetailRepo;
    @Autowired
    ProjectService projectService;

    // @GetMapping("/{projectId}/")
    // public ResponseEntity<Object> getTaskDetail(@PathVariable Integer projectId){
    //
    //
    // List<TaskDetails> Taskofproject =
    // taskDetailService.getTaskDetailOfProject(projectId);
    //
    // return
    // ResponseEntity.ok().body(Map.of("message","success","data",Taskofproject));
    //
    // }
    //
    // @GetMapping("/requireDetail/{projectId}/")
    // public ResponseEntity<Object> getDetail(@PathVariable Integer projectId){
    //
    //
    // List<TaskDetailDto> Taskofproject1 =
    // taskDetailService.getSomeDetail(projectId);
    //
    // return
    // ResponseEntity.ok().body(Map.of("taskName","success","data",Taskofproject1));
    //
    // }

    @GetMapping("/all-task")
    public List<TaskDetails> getOneTask() {
        return taskDetailService.allTask();
    }

    @GetMapping("/getOneTask/{id}")
    public TaskDetails getOneTask(@PathVariable("id") Integer taskId) {
        return projectService.getOneTaskDetail(taskId);
    }

    // create taskdetails
    @PostMapping("/create-task")
    public Integer createTask(@RequestBody TaskDetailForCreate taskDetail) {
        Integer status = this.taskDetailService.createTask(taskDetail);
        return status;
    }

    @PutMapping("/update-task/{taskId}")
    public Integer updateTask(@PathVariable("taskId") Integer taskId, @RequestBody TaskDetailDto taskDetailDto) {
        Optional<TaskDetails> mytaskdetail = taskDetailRepo.findById(taskId);
        if (!mytaskdetail.isPresent()) {
            throw new resourceNotFoundException("TaskDetail", "TaskID", taskId);
        } else {
            taskDetailService.updateTask(taskId, taskDetailDto);
            return 200;
        }
    }

    @DeleteMapping("/delete-task/{taskId}")
    public Integer deleteTask(@PathVariable("taskId") Integer taskId) {
        Optional<TaskDetails> mytaskdetail = taskDetailRepo.findById(taskId);
        if (!mytaskdetail.isPresent()) {
            throw new resourceNotFoundException("TaskDetail", "TaskID", taskId);
        } else {
            this.taskDetailService.deleteTask(taskId);
            return 200;
        }
    }

}
