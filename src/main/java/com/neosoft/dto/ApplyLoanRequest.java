package com.neosoft.dto;

import lombok.Data;

@Data
public class ApplyLoanRequest {

    private Long userId;
    private Long loanTypeId;
    private double amount;


}
