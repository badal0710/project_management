package com.service.project_management.dto.TaskComments;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentsDtoGet {

    @Id
    private Integer commentId;

    private String comment_info;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate comment_date;

    private Integer TaskId;

    private Integer contractorId;

    private String contractorName;



}