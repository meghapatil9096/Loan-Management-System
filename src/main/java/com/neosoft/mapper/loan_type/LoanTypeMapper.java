package com.neosoft.mapper.loan_type;

import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import org.springframework.stereotype.Component;

@Component
public class LoanTypeMapper {

    public LoanType createRequestDtoToLoanType(LoanTypeDTO dto){
        LoanType loanType = new LoanType();

        loanType.setName(dto.getName());
        loanType.setInterestRate(dto.getInterestRate());
        loanType.setDurationMonth(dto.getDurationMonth());
        loanType.setDescription(dto.getDescription());

        return loanType;
    }

}
