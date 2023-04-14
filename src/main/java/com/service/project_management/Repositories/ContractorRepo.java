package com.service.project_management.Repositories;

import com.service.project_management.Entities.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepo extends JpaRepository<Contractor,Integer> {
}
