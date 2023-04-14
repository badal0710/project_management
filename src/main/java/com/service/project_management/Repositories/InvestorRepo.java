package com.service.project_management.Repositories;

import com.service.project_management.Entities.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepo extends JpaRepository<Investor,Integer> {
}
