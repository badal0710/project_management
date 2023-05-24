package com.service.project_management.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "task_comments")
public class Comments implements Serializable {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "comment_id" )
private Integer commentId;


@Column(name = "comments")
private String comment_info;

@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(referencedColumnName = "task_id",name="task_id")
private TaskDetails taskDetails;

@Column(name = "comment_date")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
private LocalDate comment_date;



}