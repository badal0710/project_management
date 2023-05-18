package com.service.project_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.project_management.Entities.Investor;
import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Repositories.InvestorRepo;
import com.service.project_management.Repositories.Investor_ProjectRepo;
import com.service.project_management.Repositories.ProjectRepo;
import com.service.project_management.dto.ProjectInvestor;

@Service
@RequestMapping("investor")
public class InvestorService {

   @Autowired
    private ProjectRepo projectRepo;

   @Autowired
   private InvestorRepo investorRepo;

   @Autowired
   private Investor_ProjectRepo investor_ProjectRepo;

    @Autowired
    private Investor_ProjectRepo investorProjectRepo;

    public List<Investor> getAllInvestor() {
        List<Investor> allproject = investorRepo.findAll();
        return allproject;
    }

    public Integer createProjectInvestor(ProjectInvestor data){
        int status = 0;
        try {
            Investor_Project investor_Project = new Investor_Project();
            investor_Project.setInvestedShare(data.getInvested_share());

            Investor investor = investorRepo.getById(data.getInvestor_id());
            investor_Project.setInvestor(investor);

            Project p = projectRepo.getOneProject(data.getProject_id());
            investor_Project.setProject(p);

            investorProjectRepo.save(investor_Project);

            status = 200;
        } catch (Exception e) {
            System.out.println("error while adding investor to project: "+e.getMessage());
            status = 500;
        }
        return status;
    }

    public Integer deleteOneProjectInvestor(Integer id){
        int status = 0;
        try {
            investor_ProjectRepo.deleteOneProjectInvestor(id);
            status = 200;
        } catch (Exception e) {
            status = 500;
        }
        return status;
    }

    public List<Investor_Project> getAllProjectInvestor(){
        List<Investor_Project> data = investorProjectRepo.findAll();
        return data;
    }

    public Integer createInvestor(Investor investor) {
        System.out.println(investor.getInvestedMoney());
        int status=0;
        try {
            investorRepo.save(investor);
            status=200;
        }catch (Exception e){
            System.out.println("error while creating investor: "+e.getMessage());
            status=500;
        }
        return status;
    }

    public Integer countInvestor() {
        return investorRepo.countInvestor();
    }

    public Integer deleteInvestor(Integer id) {
        Integer status = 0;
        try {
            investorRepo.deleteById(id);
            status=200;
        } catch (Exception e) {
            System.out.println("error while deleting investor: "+e.getMessage());
            status=500;
        }
        return status;
    }

    public List<Investor_Project> getAllInvesterProjects(Integer investorId) {
        List<Investor_Project> projects = investorProjectRepo.findByInvestorId(investorId);
        return projects;
    }

    public Optional<Investor_Project> getInvesterProject(Integer investorId, Integer projectId) {
        Optional<Investor_Project> project = investorProjectRepo.findProjectByIp(investorId,projectId);
        return project;
    }

    public List<Investor_Project> getProjectLocationOfInvestor(Integer investorId, String projectLocationName){
        List<Investor_Project> investorprojectbylocaiton=investorProjectRepo.findProjectLocationByInvestorId(investorId,projectLocationName);
        return  investorprojectbylocaiton;
    }


}

