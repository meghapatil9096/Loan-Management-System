package com.neosoft.mapper.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.entity.LoanType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
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

    public static List<GetAllLoanTypeDTO> toResponseList(List<LoanType> types){
        return types.stream()
                .map(GetAllLoanTypeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
