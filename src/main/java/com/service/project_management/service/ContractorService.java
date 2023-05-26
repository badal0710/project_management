package com.service.project_management.service;

import com.service.project_management.Entities.Contractor;
import com.service.project_management.Entities.ProjectLocation;
import com.service.project_management.Repositories.ContractorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContractorService {

    @Autowired
    ContractorRepo contractorRepo;


    public Integer createContractor(Contractor contractor) {
        int status = 0;
        try {
            contractorRepo.save(contractor);
            status = 200;
        } catch (Exception e) {
            status = 500;
        }
        return status;
    }

    public List<Contractor> getAllContractor() {
        List<Contractor> allContractor = contractorRepo.findAll();
        return allContractor;
    }

    public Integer countContractor() {
        return contractorRepo.countContractor();
    }


    public Contractor contractorUpdateById(Integer contractorId, Contractor contractor) {


        Contractor con = contractorRepo.findById(contractorId).get();


        if(Objects.nonNull(contractor.getAddress())
                && !"".equalsIgnoreCase(contractor.getAddress())){

            con.setAddress(contractor.getAddress());
        }

        if(Objects.nonNull(contractor.getEmail())
                && !"".equalsIgnoreCase(contractor.getEmail())){
            con.setEmail(contractor.getEmail());
        }


        if(Objects.nonNull(contractor.getContractorName())
                && !"".equalsIgnoreCase(contractor.getContractorName())){
            con.setContractorName(contractor.getContractorName());
        }

        if(Objects.nonNull(contractor.getJobRole())
                && !"".equalsIgnoreCase(contractor.getJobRole())){
            con.setJobRole(contractor.getJobRole());
        }

        if(Objects.nonNull(contractor.getExperience())
                && !"".equalsIgnoreCase(contractor.getExperience())){
            con.setExperience(contractor.getExperience());
        }

        if(Objects.nonNull(contractor.getPhoneNo())
                && !"".equalsIgnoreCase(contractor.getPhoneNo())){
            con.setPhoneNo(contractor.getPhoneNo());
        }

        return contractorRepo.save(con);

    }


    public Integer deleteContractor(Integer contractorId) {
        
        int status = 0;
        try {
            this.contractorRepo.deleteById(contractorId);
            status=200;
        } catch (Exception e) {

            status=500;
        }
        return status;

    }

}
