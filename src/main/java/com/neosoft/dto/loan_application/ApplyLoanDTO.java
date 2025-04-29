package com.neosoft.dto.loan_application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplyLoanDTO {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Loan Type Id is required")
    private Long loanTypeId;

    @Min(value = 1, message = "Amount should be greater than zero")
    private double amount;


}
