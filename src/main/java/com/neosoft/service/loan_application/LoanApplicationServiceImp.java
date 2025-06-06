package com.neosoft.service.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.entity.LoanType;
import com.neosoft.entity.User;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.loan_application.GetAllLoanAppMapper;
import com.neosoft.mapper.loan_application.UpdateAppMapper;
import com.neosoft.repository.LoanApplicationRepository;
import com.neosoft.repository.LoanTypeRepository;
import com.neosoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImp implements LoanApplicationService {

//    @Autowired
    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
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
    public List<LoanApplication> getById(Long user_id) {
        return loanApplicationRepository.findByUserId(user_id);
    }

    @Override
    public List<LoanApplication> showStatus(String status) {
        return loanApplicationRepository.findByStatus(LoanApplication.Status.valueOf(status.toUpperCase()));
    }

    @Override
    public List<GetAllLoanAppDTO> getAllLoanApp(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize,Sort.by(direction,sortBy));
        Page<LoanApplication> loanApplications = loanApplicationRepository.findAll(pageRequest);
        return loanApplications
                .getContent()
                .stream()
                .map(GetAllLoanAppMapper::toResponse)
                .toList();
    }


    @Override
    public LoanApplication updateLoanApp(Long id, UpdateAppDTO dto) {
        LoanApplication application = loanApplicationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Loan-App is not found with Id: "+id));

//        check if the status is PENDING
        if (!LoanApplication.Status.PENDING.equals(application.getStatus())){
            throw new IllegalStateException("Only PENDING application can be updated.");
        }
        //if a new LoanTypeId is provided, update the LoanType
        if (dto.getLoanTypeId()!=null){
           LoanType newLoanType = loanTypeRepository.findById(dto.getLoanTypeId())
                   .orElseThrow(()->new ResourceNotFoundException("Loan Type not found with id: "+ dto.getLoanTypeId()));
            application.setLoanType(newLoanType);
        }
        //update other field like amount
        if (dto.getAmount()!=null){
            application.setAmount(dto.getAmount());
        }
//        update status if provided and valid
        if(dto.getStatus()!=null){
           application.setStatus(dto.getStatus());
        }

//        UpdateAppMapper.updateDtoToEntity(dto,application);
        return loanApplicationRepository.save(application);
    }

    @Override
    public void deleteLoanApp(Long id) {
//        log.info)"Entered method {} wth args: {}"
        if (!loanApplicationRepository.existsById(id))
        {
            throw new UserNotFoundException(UserNotFoundException.USER_NOT_FOUND);
        }
        loanApplicationRepository.deleteLoanAppById(id);
    }

}
