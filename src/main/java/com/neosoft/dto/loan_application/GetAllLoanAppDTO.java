package com.neosoft.dto.loan_application;

import lombok.Data;

@Data
public class GetAllLoanAppDTO {
    private Long id;

    private String userName;

    private String loanType;

    private Double amount;

    private String status;

    private String appliedDate;
}
