package com.neosoft.dto.loan_application;

import com.neosoft.entity.LoanApplication;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAppDTO {
    @NotNull(message = "loantype id is required")
    private Long loanTypeId;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Status is required")
    private LoanApplication.Status status;  //PENDING, APPROVED, REJECT
}
