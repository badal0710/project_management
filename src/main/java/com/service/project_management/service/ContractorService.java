package com.service.project_management.service;

import com.service.project_management.Entities.Contractor;
import com.service.project_management.Entities.ProjectLocation;
import com.service.project_management.Repositories.ContractorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ContractorService {

    @Autowired
    ContractorRepo contractorRepo;


    public Contractor createContractor(Contractor contractor) {

        return contractorRepo.save(contractor);

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


    public ResponseEntity<Boolean> deleteContractor(Integer contractorId) {
        boolean isDeleted = false;

            this.contractorRepo.deleteById(contractorId);
            isDeleted = true;
            return ResponseEntity.ok(isDeleted);


    }

}
