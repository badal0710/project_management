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
@Table(name = "money_management")
@Entity
public class MoneyManagement implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_management_id" )
    private Integer moneyManagementId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "project_id",name = "project_id")
    private Project project;

    @Column(name = "total_budget" )
    private Integer totalBudget;

    @Column(name = "used_budget" )
    private Integer usedBudget;

    @Column(name = "remaining_budget" )
    private Integer remainingBudget;




}
