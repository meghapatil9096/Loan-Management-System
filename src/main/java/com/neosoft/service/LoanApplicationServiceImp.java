package com.neosoft.service;

import com.neosoft.dto.ApplyLoanRequest;
import com.neosoft.entity.LoanApplication;
import com.neosoft.entity.LoanType;
import com.neosoft.entity.User;
import com.neosoft.repository.LoanApplicationRepository;
import com.neosoft.repository.LoanTypeRepository;
import com.neosoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanApplicationServiceImp implements LoanApplicationService{

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Override
    public String applyLoan(ApplyLoanRequest applyLoanRequest) {

        LoanApplication loanApplication = new LoanApplication();
//(User)
        User user = userRepository.findById(applyLoanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
//(LoanType)
        LoanType loanType = loanTypeRepository.findById(applyLoanRequest.getLoanTypeId())
                .orElseThrow(() -> new RuntimeException("Loan Type not found"));

        loanApplication.setUser(user);
        loanApplication.setLoanType(loanType);
        loanApplication.setAmount(applyLoanRequest.getAmount());
        loanApplication.setStatus(LoanApplication.Status.PENDING);
        loanApplication.setAppliedDate(LocalDateTime.now());

        loanApplicationRepository.save(loanApplication);

        return "Loan Application submitted successfully! ";
    }
}
