package com.service.project_management.Controllers;

import com.service.project_management.Entities.Contractor;
import com.service.project_management.Entities.Investor_Project;
import com.service.project_management.Repositories.ContractorRepo;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.ContractorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/contractor")
public class ContractorController {

    @Autowired
    ContractorService contractorService;
    @Autowired
    ContractorRepo contractorRepo;

    @GetMapping("/totalContractor")
    public Integer totaContractor(){
        return contractorService.countContractor();
    }

    @PostMapping("/create-contractor")
    public ResponseEntity<Contractor> createContractor(@Valid @RequestBody Contractor contractor){
        Contractor createCategory=this.contractorService.createContractor(contractor);
        return new ResponseEntity<Contractor>(HttpStatus.CREATED);

    }

    @GetMapping("/allContractor")
        public ResponseEntity<Object> getAllContractor() {
        return ResponseEntity.ok().body(this.contractorService.getAllContractor());
    }

    @PutMapping("/update-contractor/{id}")
    public Contractor contractorUpdateById(@PathVariable("id") Integer contractorId,@RequestBody Contractor contractor){
        Optional<Contractor> mycontractor = contractorRepo.findById(contractorId);
        if(!mycontractor.isPresent()){
            throw new resourceNotFoundException("contractor", "contractorID", contractorId);
        }else{
            return this.contractorService.contractorUpdateById(contractorId,contractor);
        }
    }

    @DeleteMapping("/delete-contractor/{id}")
    public ResponseEntity<Object> deleteContractor(@PathVariable("id") Integer contractorId){
        Optional<Contractor> mycontractor = contractorRepo.findById(contractorId);
        if(!mycontractor.isPresent()){
            throw new resourceNotFoundException("contractor", "contractorID", contractorId);
        }else{
            this.contractorService.deleteContractor(contractorId);
            return ResponseEntity.ok().body("Contractor is deleted");
        }
    }
}