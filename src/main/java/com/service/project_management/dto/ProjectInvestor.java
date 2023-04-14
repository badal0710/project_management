package com.service.project_management.dto;

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
public class ProjectInvestor {
 
    private Integer investor_id;
 
    private Integer project_id;
 
    private Integer invested_share;

    private String status;

}
