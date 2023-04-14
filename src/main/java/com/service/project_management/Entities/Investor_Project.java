package com.service.project_management.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
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
@Table(name = "investor_project")
@Entity
public class Investor_Project implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "investor_project_id" )
   private Integer investorProjectId;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(referencedColumnName = "investor_id",name = "investor_id")
   private Investor investor;


   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(referencedColumnName = "project_id",name="project_id")
   private Project project;

   @Column(name = "invested_share" )
   private Integer investedShare;

   @Column(name="status")
   private String Status;

}
