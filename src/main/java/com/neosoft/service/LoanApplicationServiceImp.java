package com.neosoft.service;

import com.neosoft.dto.ApplyLoanDTO;
import com.neosoft.dto.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.entity.LoanType;
import com.neosoft.entity.User;
import com.neosoft.mapper.LoanApplicationMapper;
import com.neosoft.repository.LoanApplicationRepository;
import com.neosoft.repository.LoanTypeRepository;
import com.neosoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanApplicationServiceImp implements LoanApplicationService{

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Override
    public String applyLoan(ApplyLoanDTO applyLoanDTO) {

        LoanApplication loanApplication = new LoanApplication();
//(User)
        User user = userRepository.findById(applyLoanDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
//(LoanType)
        LoanType loanType = loanTypeRepository.findById(applyLoanDTO.getLoanTypeId())
                .orElseThrow(() -> new RuntimeException("Loan Type not found"));

        loanApplication.setUser(user);
        loanApplication.setLoanType(loanType);
        loanApplication.setAmount(applyLoanDTO.getAmount());
        loanApplication.setStatus(LoanApplication.Status.PENDING);
        loanApplication.setAppliedDate(LocalDateTime.now());

        loanApplicationRepository.save(loanApplication);

        return "Loan Application submitted successfully! ";
    }

    @Override
    public List<LoanApplication> showStatus(String status) {
        return loanApplicationRepository.findByStatus(LoanApplication.Status.valueOf(status.toUpperCase()));
    }

    @Override
    public List<GetAllLoanAppDTO> getAllLoanApplications() {
        List<LoanApplication> applications = loanApplicationRepository.findAll();

        return LoanApplicationMapper.toResponseList(applications);
    }

}
