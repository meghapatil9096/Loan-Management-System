package com.neosoft.service;

import com.neosoft.dto.ApplyLoanDTO;
import com.neosoft.dto.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;

import java.util.List;

public interface LoanApplicationService {

    String applyLoan(ApplyLoanDTO applyLoanDTO);

    List<LoanApplication> showStatus(String status);

    List<GetAllLoanAppDTO> getAllLoanApplications();
}
