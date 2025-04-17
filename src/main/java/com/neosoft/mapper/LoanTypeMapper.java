package com.neosoft.mapper;

import com.neosoft.dto.LoanTypeRequest;
import com.neosoft.entity.LoanType;
import org.springframework.stereotype.Component;

@Component
public class LoanTypeMapper {

    public LoanType createRequestDtoToLoanType(LoanTypeRequest loanTypeRequest){
        LoanType loanType = new LoanType();

        loanType.setName(loanTypeRequest.getName());
        loanType.setInterestRate(loanTypeRequest.getInterestRate());
        loanType.setDurationMonth(loanTypeRequest.getDurationMonth());
        loanType.setDescription(loanTypeRequest.getDescription());

        return loanType;
    }

}
