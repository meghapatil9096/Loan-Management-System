package com.neosoft.mapper.loan_type;

import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import org.springframework.stereotype.Component;

@Component
public class UpdateTypeMapper {

    public static void updateTypeForDTO(LoanTypeDTO dto, LoanType type){
        type.setName(dto.getName());
        type.setInterestRate(dto.getInterestRate());
        type.setDescription(dto.getDescription());
        type.setDurationMonth(dto.getDurationMonth());
    }

}
