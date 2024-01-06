package com.siyu.resourcingbackend.job;

import com.siyu.resourcingbackend.temp.Temp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobCreateDTO {

    private String name;
    private String startDate;
    private String endDate;
    private Long tempId;
    
}
