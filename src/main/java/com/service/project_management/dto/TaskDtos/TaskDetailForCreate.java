package com.service.project_management.dto.TaskDtos;

import java.time.LocalDate;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.project_management.Entities.Contractor;

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
public class TaskDetailForCreate {
    
    // @Id
    // private  Integer TaskId;

    private Integer projectId;

    private Integer contractorId;

    private String taskName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskStartingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskDeadLine;

    // private  Integer taskStatus;

    private Integer allocatedBudget;

}
