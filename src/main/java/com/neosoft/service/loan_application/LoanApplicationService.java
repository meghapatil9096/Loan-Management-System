package com.neosoft.service.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationService {

    String applyLoan(ApplyLoanDTO applyLoanDTO);

//    get by id
        List<LoanApplication> getById(Long user_id);

//    get all by status
        List<LoanApplication> showStatus(String status);

//    get all loan-app
        List<GetAllLoanAppDTO> getAllLoanApp(int pageNo, int pageSize, String sortBy, String sortDir);

//    update loan-application with Amount and Status by Admin
        LoanApplication updateLoanApp(Long id, UpdateAppDTO dto);

//    delete loan-application by id
        void deleteLoanApp(Long id);
}
