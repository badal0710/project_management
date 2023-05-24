package com.service.project_management.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
//import com.service.project_management.CustomValidation.ValidateProjectStatus;
import com.service.project_management.CustomValidation.ValidateProjectStatus;
import com.service.project_management.Entities.ProjectLocation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data



public class ProjectDto {

    @Id
    private Integer projectId;


    @ValidateProjectStatus
    private Integer projectStatus;


    private  String projectName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate projectStartingDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate projectDeadline;

    @NotNull(message = "pleae")
    private String projectTypeName;



    private Integer projectLocationId;

    private String state;

    private String city;

    private String area;




}
