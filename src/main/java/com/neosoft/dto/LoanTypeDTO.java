package com.neosoft.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class LoanTypeDTO {

    @NotBlank(message = "Loan type name is required")
    private  String name;

    @NotNull(message = "Interest Rate is required")
    @DecimalMin(value = "0.0", inclusive = false, message ="Interest rate must be greater than 0" )
    private Double interestRate;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least one month")
    private Integer durationMonth;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;
}
