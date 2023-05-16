package com.service.project_management.Repositories;

import com.service.project_management.Entities.ProjectLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectLocationRepo extends JpaRepository<ProjectLocation,Integer> {

    @Query(value = "select * from project_location p where p.area= :area and p.city= :city and p.state= :state",nativeQuery = true)
    ProjectLocation findExisitingRecord(@Param("area")String area,@Param("city")String city,@Param("state")String state );

}
