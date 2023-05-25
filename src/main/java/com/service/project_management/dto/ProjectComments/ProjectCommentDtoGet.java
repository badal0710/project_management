package com.service.project_management.dto.ProjectComments;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectCommentDtoGet {

    @Id
    private Integer commentId;

    private String comment_info;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate comment_date;

    private Integer projectId;

    private Integer investorId;

    private Integer name;




}