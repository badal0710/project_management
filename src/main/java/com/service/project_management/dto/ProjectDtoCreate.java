package com.service.project_management.dto;

import java.time.LocalDate;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.project_management.CustomValidation.ValidateProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDtoCreate {
    
    @Id
    private Integer projectId;


    @ValidateProjectStatus
    private Integer projectStatus;


    private String projectName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String projectStartingDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String projectDeadline;


    private String projectTypeName;

    private Integer projectLocationId;

}
