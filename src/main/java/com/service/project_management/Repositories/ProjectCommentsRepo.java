package com.service.project_management.Repositories;

import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.ProjectComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectCommentsRepo extends JpaRepository<ProjectComments,Integer> {


    @Query(value = "select * from  project_comments d where d.project_id= :p_id",nativeQuery = true)
    List<ProjectComments> getCommentByProject(@Param("p_id")Integer projectId);

}
