package com.neosoft.mapper.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.entity.LoanType;
import org.springframework.stereotype.Component;

@Component
public class GetAllLoanTypeMapper {

    public static GetAllLoanTypeDTO toResponse(LoanType type){

        GetAllLoanTypeDTO dto = new GetAllLoanTypeDTO();
        dto.setId(type.getId());
        dto.setName(type.getName());
        dto.setInterestRate(type.getInterestRate());
        dto.setDurationMonth(type.getDurationMonth());
        dto.setDescription(type.getDescription());
        return dto;
    }

}
