package com.service.project_management.service;

import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Entities.ProjectLocation;
import com.service.project_management.Repositories.ProjectLocationRepo;
import com.service.project_management.Repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ProjectByLocationService {



    @Autowired
    ProjectLocationRepo projectLocationRepo;

    @Autowired
    ProjectRepo projectRepo;




    public List<Project> getAllProjectwithLocation(Integer locationId) {

        List<Project> projectdetails = projectRepo.findProjectByLocation(locationId);
        return projectdetails;
    }

}
