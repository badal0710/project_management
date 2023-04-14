package com.service.project_management.dto.TaskDtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Data
public class TaskDetailDto {




    @Id
    private  Integer TaskId;

    private String taskName;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskStartingDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskDeadLine;

    private Integer contractorId;

    private String contractorName;

    private String jobRole;

    private String email;

    private Integer projectId;

    private Integer taskStatus;

    private Integer allocatedBudget;








}
