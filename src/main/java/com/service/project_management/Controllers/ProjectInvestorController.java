package com.service.project_management.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Repositories.InvestorRepo;
import com.service.project_management.Repositories.Investor_ProjectRepo;
import com.service.project_management.dto.ProjectInvestor;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.InvestorService;

@RestController
@RequestMapping("/projectInvestor")
public class ProjectInvestorController {
    
    @Autowired
    private Investor_ProjectRepo investor_ProjectRepo;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private InvestorRepo investorRepo;

    @GetMapping("/getInvestedAmount/{email}/{projectid}")
    public ResponseEntity<Integer> getInvestedAmount(@PathVariable("email") String email, @PathVariable("projectid") Integer projectId){
        System.out.println(email+" = "+projectId);
        int investorId = investorRepo.getInvestorId(email);
        Integer data = this.investor_ProjectRepo.getInvestedAmount(investorId,projectId);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        List<Investor_Project> data = this.investorService.getAllProjectInvestor();
        return ResponseEntity.ok().body(data);
    }

    @PostMapping("/create")
    public Integer createProjectInvestor(@RequestBody ProjectInvestor data){
        Integer status=this.investorService.createProjectInvestor(data);
        return status;
    }

    @DeleteMapping("/delete/{id}")
    public Integer deleteProjectInvestor(@PathVariable("id") Integer id){
        Optional<Investor_Project> investor_project = investor_ProjectRepo.findById(id);
        if(!investor_project.isPresent()){
            throw new resourceNotFoundException("investor_project", "investor_project_ID", id);
        }else{
            int status = this.investorService.deleteOneProjectInvestor(id);
            return status;
        }
    }

}
