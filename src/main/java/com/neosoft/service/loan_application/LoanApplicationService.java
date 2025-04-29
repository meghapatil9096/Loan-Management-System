package com.neosoft.service.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;

import java.util.List;

public interface LoanApplicationService {

    String applyLoan(ApplyLoanDTO applyLoanDTO);

    List<LoanApplication> showStatus(String status);

//    get all loan-app
    List<GetAllLoanAppDTO> getAllLoanApp();

//    update loan-application with Amount and Status by Admin
    LoanApplication updateLoanApp(Long id, UpdateAppDTO dto);
}
