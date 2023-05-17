package com.service.project_management.Repositories;

import com.service.project_management.Entities.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvestorRepo extends JpaRepository<Investor,Integer> {

    @Query(value = "select count(investor_id) from investor", nativeQuery = true)
    Integer countInvestor();

}
