package com.service.project_management.Repositories;

import com.service.project_management.Entities.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDetailRepo extends JpaRepository<TaskDetails,Integer> {

    // @Modifying
    // @Query(value = "update task_details set task_status= :status where task_id= :taskId",nativeQuery = true)
    // int updatemyTaskStatus(@Param("taskId") Integer taskId ,@Param("status") String status);

    @Query(value = "select * from task_details td where td.project_id=:l_id",nativeQuery = true)
    List<TaskDetails>getProjectTaskDetail(@Param("l_id")Integer projectId); 

    @Query(value = "select * from task_details td where td.task_id=:l_id",nativeQuery = true)
    TaskDetails getOneTaskDetail(@Param("l_id")Integer taskId); 

    @Query(value = "select * from task_details td where td.task_starting_date>=:s_id and td.task_deadline<=:e_id and td.project_id=:p_id",nativeQuery = true)
    List<TaskDetails> getTaskByDate(@Param("p_id")Integer projectId,@Param("s_id") String taskStartingDate,@Param("e_id")String taskDeadLine);


//     @Query(value = "select new com.service.project_management.Entities(d.task_id, d.task_starting_date,d.task_deadline) from task_details d where d.project_id=:p_id",nativeQuery = true)
//    List<TaskDetails> getTaskByProject(@Param("p_id")Integer projectId);


  @Query(value = "select * from task_details d where d.project_id= :p_id",nativeQuery = true)
  List<TaskDetails> getTaskByProject(@Param("p_id")Integer projectId);



}
