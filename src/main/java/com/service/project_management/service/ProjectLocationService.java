package com.service.project_management.service;


import com.service.project_management.Repositories.ProjectLocationRepo;
import com.service.project_management.Repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLocationService {

   @Autowired
    ProjectLocationRepo projectLocationRepo;

    public void deleteByProjectLocation(Integer projectLocationId) {
        projectLocationRepo.deleteById(projectLocationId);
    }
}
