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
public class ProjectInvestorCreateDTO {

    private Integer projectid;
 
    private Integer amount;
}
