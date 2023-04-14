package com.service.project_management.Repositories;

import com.service.project_management.Entities.Investor_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Investor_ProjectRepo  extends JpaRepository<Investor_Project,Integer> {

    @Query(value = "select * from investor_project pi where pi.investor_id =?1",nativeQuery = true)
    List<Investor_Project> findByInvestorId(Integer investorId);

    @Query(value = "select * from investor_project pi where pi.investor_id = :i_id and pi.project_id = :p_id",nativeQuery = true)
    Optional<Investor_Project> findProjectByIp(@Param("i_id")Integer investorId,@Param("p_id") Integer projectId);

    @Query(value = "select * from investor_project pi where pi.investor_id= :i_id and pi.state= :l_name",nativeQuery = true)
    List<Investor_Project> findProjectLocationByInvestorId(@Param("i_id")Integer investorId,@Param("l_name") String projectLocationName);



    //for the project
    @Query(value = "select * from investor_project pi where pi.project_id =:p_id",nativeQuery = true)
    List<Investor_Project> findAllInvestorProject(@Param("p_id")Integer projectId);

    @Query(value = "select * from investor_project pi where pi.project_id =:p_id and pi.state=:l_name",nativeQuery = true)
    Optional<Investor_Project> findInvestorByProjectAndLocation(@Param("p_id")Integer projectId,@Param("l_name")String projectLocationName);






}
