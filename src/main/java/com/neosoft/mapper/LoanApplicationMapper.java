package com.neosoft.mapper;

import com.neosoft.dto.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;

import java.util.List;
import java.util.stream.Collectors;

public class LoanApplicationMapper {

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

    public static List<GetAllLoanAppDTO> toResponseList(List<LoanApplication> applications){
        return applications.stream()
                .map(LoanApplicationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
