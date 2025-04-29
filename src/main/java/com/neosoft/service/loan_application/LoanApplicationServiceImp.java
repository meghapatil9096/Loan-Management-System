package com.neosoft.service.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.entity.LoanType;
import com.neosoft.entity.User;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.mapper.loan_application.GetAllLoanAppMapper;
import com.neosoft.mapper.loan_application.UpdateAppMapper;
import com.neosoft.repository.LoanApplicationRepository;
import com.neosoft.repository.LoanTypeRepository;
import com.neosoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImp implements LoanApplicationService {

   // @Autowired
    private final LoanApplicationRepository loanApplicationRepository;

    //@Autowired
    private final UserRepository userRepository;

    // @Autowired
    private final LoanTypeRepository loanTypeRepository;

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
    public List<GetAllLoanAppDTO> getAllLoanApp() {
        List<LoanApplication> applications = loanApplicationRepository.findAll();

        return GetAllLoanAppMapper.toResponseList(applications);
    }

    @Override
    public LoanApplication updateLoanApp(Long id, UpdateAppDTO dto) {
        LoanApplication application = loanApplicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Loan-App is not found with Id: "+id));

        if (!LoanApplication.Status.PENDING.equals(application.getStatus())){
            throw new IllegalStateException("Only PENDING application can be updated.");
        }

        UpdateAppMapper.updateDtoToEntity(dto,application);
        return loanApplicationRepository.save(application);
    }

}
