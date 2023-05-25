package com.service.project_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.service.project_management.dto.PendingInvestorProjectDto;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.project_management.Entities.Investor;
import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Entities.Project;
import com.service.project_management.Repositories.InvestorRepo;
import com.service.project_management.Repositories.Investor_ProjectRepo;
import com.service.project_management.Repositories.ProjectRepo;
import com.service.project_management.dto.ProjectInvestor;
import org.springframework.web.bind.annotation.RequestParam;

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
    ModelMapper modelMapper;




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

            investor_Project.setStatus("pending");

            Project p = projectRepo.getOneProject(data.getProject_id());
            investor_Project.setProject(p);

            investor_ProjectRepo.save(investor_Project);

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
        List<Investor_Project> data = investor_ProjectRepo.findAll();
        return data;
    }

    public Integer createInvestor(Investor investor) {

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
        List<Investor_Project> projects = investor_ProjectRepo.findByInvestorId(investorId);
        return projects;
    }

    public Optional<Investor_Project> getInvesterProject(Integer investorId, Integer projectId) {
        Optional<Investor_Project> project = investor_ProjectRepo.findProjectByIp(investorId,projectId);
        return project;
    }

    public List<Investor_Project> getProjectLocationOfInvestor(Integer investorId, String projectLocationName){
        List<Investor_Project> investorprojectbylocaiton=investor_ProjectRepo.findProjectLocationByInvestorId(investorId,projectLocationName);
        return  investorprojectbylocaiton;
    }


    public List<Project> getNotInvestedProjects(Integer investorId){

           List<Integer> projects=investor_ProjectRepo.findNotInvestedProject(investorId);
           List<Project> projectlist=new ArrayList<>();

        for (Integer projectid: projects) {
            projectlist.add(projectRepo.getOneProject(projectid));
        }

             return projectlist;

    }


    public List<PendingInvestorProjectDto> getInvestmentRequest(){


        List<Investor_Project> investorProjectRepos=this.investor_ProjectRepo.getPendingInvestmentRequest();

        List<PendingInvestorProjectDto> investorProjects=investorProjectRepos.stream().map(td->this.InvestorProjectToDto(td)).collect(Collectors.toList());
        return  investorProjects;
    }

   public  Investor_Project updateInvestmentStatus(Integer investor_projectId){




           Investor_Project investorProject=this.investor_ProjectRepo.getById(investor_projectId);
           investorProject.setStatus("approved");

         return   investor_ProjectRepo.save(investorProject);
   }


//Entity to Dto
    public PendingInvestorProjectDto InvestorProjectToDto(Investor_Project investorProject){
        PendingInvestorProjectDto pendingInvestorProjectDto=this.modelMapper.map(investorProject,PendingInvestorProjectDto.class);

        pendingInvestorProjectDto.setInvestor_id(investorProject.getInvestor().getInvestorId());
        pendingInvestorProjectDto.setProjectName(investorProject.getProject().getProjectName());
        pendingInvestorProjectDto.setProject_id(investorProject.getProject().getProjectId());
        pendingInvestorProjectDto.setInvested_share(investorProject.getInvestedShare());
        pendingInvestorProjectDto.setName(investorProject.getProject().getProjectName());
        pendingInvestorProjectDto.setInvestorProjectId(investorProject.getInvestorProjectId());
        return pendingInvestorProjectDto;
    }






}

