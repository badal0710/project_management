package com.service.project_management.Repositories;

import com.service.project_management.Entities.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractorRepo extends JpaRepository<Contractor,Integer> {

    @Query(value = "select count(contractor_id) from contractor", nativeQuery = true)
    Integer countContractor();

    @Query(value = "select * from contractor where contractor_id=:id", nativeQuery = true)
    Contractor getOneContractor(@Param("id")Integer cid);
}
