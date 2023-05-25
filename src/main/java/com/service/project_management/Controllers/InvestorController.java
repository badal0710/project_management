package com.service.project_management.Controllers;

import com.service.project_management.Entities.Investor;
import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Repositories.InvestorRepo;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    @Autowired
    InvestorService investorService;
    @Autowired
    InvestorRepo investorRepo;

    @GetMapping("/allInvestor")
        public ResponseEntity<Object> getAllInvestor() {
        return ResponseEntity.ok().body(this.investorService.getAllInvestor());
    }

    @PostMapping("/create-investor")
    public Integer createInvestor(@RequestBody Investor investor){
        Integer status=this.investorService.createInvestor(investor);
        return status;
    }

    @GetMapping("/totalInvestor")
    public Integer totaInvestor(){
        return investorService.countInvestor();
    }

    @DeleteMapping("/delete-investor/{id}")
    public Integer deleteInvestor(@PathVariable("id") Integer Id){
        Integer status = investorService.deleteInvestor(Id);
        return status;
    }

    @GetMapping("/{investorId}/")
    public ResponseEntity<Object> getProjects(@PathVariable Integer investorId,
            @RequestParam(defaultValue = "0", required = false) Integer projectId,
            @RequestParam(defaultValue = "0", required = false) String projectLocationName) {
        Optional<Investor> myinvestor = investorRepo.findById(investorId);
        if (!myinvestor.isPresent()) {
            throw new resourceNotFoundException("investor", "investorId", investorId);
        } else {
            if (projectId.equals(0)) {
                List<Investor_Project> investorProjects = investorService.getAllInvesterProjects(investorId);
                return ResponseEntity.ok().body(investorProjects);
            } else if (projectLocationName.equals(0)) {
                List<Investor_Project> projectLocation = investorService.getProjectLocationOfInvestor(investorId,
                        projectLocationName);
                return ResponseEntity.ok().body(projectLocation);

            } else {
                Optional<Investor_Project> project = investorService.getInvesterProject(investorId, projectId);

                return ResponseEntity.ok().body(project);
            }
        }
    }




    @GetMapping("/get-not-invested-projects/{investorId}")
    public List<Project> getNotInvestedProjects(@PathVariable("investorId")Integer investorId){

        List<Project> project=this.investorService.getNotInvestedProjects(investorId);
        return ResponseEntity.ok().body(project).getBody();

    }



}
