package com.service.project_management.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "task_details")
@Entity
public class TaskDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id" )
    private  Integer TaskId;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "project_id",name="project_id")
    private Project project;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "contractor_id",name="contractor_id")
    private Contractor contractor;


    @Column(name = "task_name" )
    private String taskName;

    @Column(name = "task_starting_date" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskStartingDate;

    @Column(name = "task_deadline" )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskDeadLine;

    @Column(name = "task_status" )
    private  Integer taskStatus;

    @Column(name = "allocated_budget" )
    private Integer allocatedBudget;




}
