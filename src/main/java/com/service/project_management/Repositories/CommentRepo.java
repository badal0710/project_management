package com.service.project_management.Repositories;


import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.ProjectLocation;
import com.service.project_management.Entities.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Integer> {


    @Query(value = "select * from  task_comments d where d.task_id= :p_id",nativeQuery = true)
    List<Comments> getCommentsByTaskId(@Param("p_id")Integer taskId);




}