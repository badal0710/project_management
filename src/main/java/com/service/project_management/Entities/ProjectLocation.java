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
@Table(name = "project_location")
@Entity
public class ProjectLocation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_location_id" )
    private  Integer projectLocationId;

    @Column(name = "state" )
    private String state;

    @Column(name = "city" )
    private String city;

    @Column(name = "area" )
    private  String area;

}
