package com.neosoft.mapper.loan_application;

import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class GetAllLoanAppMapper {

    public  static GetAllLoanAppDTO toResponse(LoanApplication application){
        GetAllLoanAppDTO dto = new GetAllLoanAppDTO();
        dto.setId(application.getId());
        dto.setUserName(application.getUser().getName());
        dto.setLoanType(application.getLoanType().getName());
        dto.setAmount(application.getAmount());
        dto.setStatus(application.getStatus().name());
        dto.setAppliedDate(application.getAppliedDate());
        return dto;
    }


}
