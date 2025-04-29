package com.neosoft.dto.loan_type;

import lombok.Data;

@Data
public class GetAllLoanTypeDTO {

    private Long id;

    private String name;

    private Double interestRate;

    private Integer durationMonth;

    private String description;

}
