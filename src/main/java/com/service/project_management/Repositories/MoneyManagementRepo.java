package com.service.project_management.Repositories;

import com.service.project_management.Entities.MoneyManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyManagementRepo extends JpaRepository<MoneyManagement,Integer> {
}
