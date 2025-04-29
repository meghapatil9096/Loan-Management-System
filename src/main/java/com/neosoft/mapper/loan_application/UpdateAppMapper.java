package com.neosoft.mapper.loan_application;

import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;
import org.springframework.stereotype.Component;

@Component
public class UpdateAppMapper {
    public static void updateDtoToEntity(UpdateAppDTO dto, LoanApplication application){
        application.setAmount(dto.getAmount());
        application.setStatus(dto.getStatus());
    }
}
