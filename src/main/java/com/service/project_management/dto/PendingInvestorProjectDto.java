package com.service.project_management.dto;


import lombok.*;

import javax.persistence.Id;
import java.net.Inet4Address;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PendingInvestorProjectDto {



    private Integer investorProjectId;

    private Integer investor_id;

    private Integer project_id;

    private String name;

    private  String projectName;

    private Integer invested_share;



}
