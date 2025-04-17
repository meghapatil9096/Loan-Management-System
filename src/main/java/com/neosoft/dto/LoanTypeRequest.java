package com.neosoft.dto;

import lombok.Data;
@Data
public class LoanTypeRequest {

    private  String name;

    private Double interestRate;

    private Integer durationMonth;

    private String description;
}
