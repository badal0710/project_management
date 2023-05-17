package com.service.project_management.Repositories;

import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project,Integer> {


    @Query(value = "select count(project_id) from project", nativeQuery = true)
    Integer countProject();

    @Query(value = "select * from project p where p.project_location_id= :l_id",nativeQuery = true)
    List<Project> findProjectByLocation(@Param("l_id")Integer locationId);

    @Query(value = "select * from project p where p.project_status= :stu_id",nativeQuery = true)
    List<Project> getProjectByStatus(@Param("stu_id")Integer projectStatus);

    @Query(value = "select * from project p where p.project_starting_date>=:s_id and p.project_deadline<=:d_id",nativeQuery = true)
    List<Project> getProjectByDate(@Param("s_id")String starting_date,@Param("d_id")String ending_date);

    @Query(value = "select * from project p where p.project_starting_date>=:s_id and p.project_deadline<=:d_id and p.project_status= :ps_id",nativeQuery = true)
    List<Project> getProjectByDateAndStatus(@Param("s_id")String starting_date,@Param("d_id")String ending_date,@Param("ps_id")Integer projectStatus);


}
