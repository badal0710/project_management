package com.service.project_management.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity

@Table(name = "contractor",uniqueConstraints = @UniqueConstraint(name = "uk_mobile_email",columnNames = {"email","phone_no"}))
public class Contractor implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "contractor_id" )
     private Integer contractorId;


     @NotEmpty
     @Pattern(regexp = "[a-zA-Z]+$",message = "not valid !!")
     @Column(name = "name" )
     private String contractorName;


     @NotEmpty(message = "Please add your job role")

     @Column(name = "job_role" )
     private String jobRole;

     @Email(message = "Your email is not valid")
     @Column(name = "email" )
     private String email;

     @Pattern(regexp = "^[0-9]{10}",message = "your phone number is not valid !!")
     @Column(name = "phone_no" )
     private String phoneNo;

     @Column(name = "address" )
     private String address;


     @Pattern(regexp = "^[0-9]{1,2}",message = "Please enter years in number")
     @Column(name = "experience_year" )
     private String experience;


}
