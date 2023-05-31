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
@Entity
@Table(name = "investor")
public class Investor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investor_id" )
    private Integer investorId;

    @Column(name = "name" )
    private String name;

    @Column(name = "email" )
    private String email;

    @Column(name = "address" )
    private String address;

    @Column(name = "phone_no" )
    private String phoneno;

    @Column(name = "experience" )
    private String experience;

    @Column(name = "invested_money")
    private String InvestedMoney;


}
