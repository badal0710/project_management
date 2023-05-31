package com.service.project_management.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import com.service.project_management.CustomValidation.ValidateProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "project")
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id" )
    private Integer projectId;


//    @Pattern(regexp = "^(10|20|30|40|50|60|70|80|90|100)$",message = "not valid")

//



//    @Pattern(regexp = "^(10|20|30)$", message =

    @Column(name = "project_status" )
    private Integer projectStatus;

   @Pattern(regexp = "[a-zA-Z]+$", message = "not valid !!fff")
    @Column(name = "project_name")
    private  String projectName;




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "project_starting_date" )
    private LocalDate projectStartingDate;

    @Column(name = "project_deadline" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate projectDeadline;




    @Column(name = "project_type_name" )
    private String projectTypeName;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "project_location_id",name="project_location_id")
    private ProjectLocation projectLocation;




}
