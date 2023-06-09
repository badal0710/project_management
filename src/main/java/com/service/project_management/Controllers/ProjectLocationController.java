package com.service.project_management.Controllers;

import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Entities.ProjectLocation;
import com.service.project_management.Repositories.ProjectLocationRepo;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.ProjectByLocationService;
import com.service.project_management.service.ProjectLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/apii/projectbylocation")
public class ProjectLocationController {

    @Autowired
    ProjectByLocationService projectByLocationService;

    @Autowired
    ProjectLocationService projectLocationService;

    @Autowired
    private ProjectLocationRepo projectLocationRepo;

    @PostMapping("create-location")
    public Integer createLocation(@RequestBody ProjectLocation projectLocation){
        try {
            projectLocationRepo.save(projectLocation);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }

    @GetMapping("/{locationId}/")
    public ResponseEntity<Object> getProject(@PathVariable Integer locationId) {
        Optional<ProjectLocation> myprojectLocation = projectLocationRepo.findById(locationId);
        if (!myprojectLocation.isPresent()) {
            throw new resourceNotFoundException("ProjectLocation", "LocationID", locationId);
        } else {

            List<Project> projectLocation = projectByLocationService.getAllProjectwithLocation(locationId);

            return ResponseEntity.ok().body(Map.of("message", "success", "data", projectLocation));
        }
    }

    @GetMapping("/allLocation")
    public List<ProjectLocation> allLocation() {
        return projectLocationRepo.findAll();
    }

    @DeleteMapping("/projectLocation/{id}")
    public String deleteByProjectLocation(@PathVariable("id") Integer projectLocationId) {

        Optional<ProjectLocation> myprojectLocation = projectLocationRepo.findById(projectLocationId);
        if(!myprojectLocation.isPresent()){
            throw new resourceNotFoundException("projectLocation", "projectLocationID", projectLocationId);
        }else{
            projectLocationRepo.deleteById(projectLocationId);
            return "project Deleted successfully";
        }
    }

}
