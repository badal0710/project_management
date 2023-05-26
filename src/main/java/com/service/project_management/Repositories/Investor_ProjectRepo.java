package com.service.project_management.Repositories;

import com.service.project_management.Entities.Investor_Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Investor_ProjectRepo extends JpaRepository<Investor_Project, Integer> {

    @Query(value = "select * from investor_project p where p.status='pending'",nativeQuery = true)
    List<Investor_Project> getPendingInvestmentRequest();

    @Query(value = "select * from project where project_id not in (select project_Id from investor_project where investor_id= :i_id)", nativeQuery = true)
    List<Integer> findNotInvestedProject(@Param("i_id") Integer investorId);

    @Query(value = "select invested_share from investor_project where investor_id= :i_id and project_id= :p_id", nativeQuery = true)
    Integer getInvestedAmount(@Param("i_id") Integer i_id, @Param("p_id") Integer p_id);

    @Query(value = "select * from investor_project pi where pi.investor_id= :i_id", nativeQuery = true)
    List<Investor_Project> findByInvestorId(@Param("i_id") Integer i_id);

    @Query(value = "select * from investor_project pi where pi.investor_id = :i_id and pi.project_id = :p_id", nativeQuery = true)
    Optional<Investor_Project> findProjectByIp(@Param("i_id") Integer investorId, @Param("p_id") Integer projectId);

    @Query(value = "delete from investor_project pi where pi.investor_project_id = :i_id", nativeQuery = true)
    Integer deleteOneProjectInvestor(@Param("i_id") Integer Id);

    @Query(value = "select * from investor_project pi where pi.investor_id= :i_id and pi.state= :l_name", nativeQuery = true)
    List<Investor_Project> findProjectLocationByInvestorId(@Param("i_id") Integer investorId,
            @Param("l_name") String projectLocationName);

    // for the project
    @Query(value = "select * from investor_project pi where pi.project_id =:p_id", nativeQuery = true)
    List<Investor_Project> findAllInvestorProject(@Param("p_id") Integer projectId);

    @Query(value = "select * from investor_project pi where pi.project_id =:p_id and pi.state=:l_name", nativeQuery = true)
    Optional<Investor_Project> findInvestorByProjectAndLocation(@Param("p_id") Integer projectId,
            @Param("l_name") String projectLocationName);

}
