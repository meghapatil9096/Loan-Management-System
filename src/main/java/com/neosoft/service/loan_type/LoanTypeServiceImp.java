package com.neosoft.service.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.mapper.loan_type.GetAllLoanTypeMapper;
import com.neosoft.mapper.loan_type.LoanTypeMapper;
import com.neosoft.mapper.loan_type.UpdateTypeMapper;
import com.neosoft.repository.LoanTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImp implements LoanTypeService {

    //@Autowired
    private final LoanTypeRepository loanTypeRepository;

    //@Autowired
    private final LoanTypeMapper loanTypeMapper;

    @Override
    public String createLoanType(LoanTypeDTO loanTypeDTO) {

        LoanType loanType = loanTypeMapper.createRequestDtoToLoanType(loanTypeDTO);
        loanTypeRepository.save(loanType);

        return "Loan type created successfully!";
    }

    @Override
    public List<LoanType> getByLoanTypeName(String name) {
        return loanTypeRepository.findByName(name);
    }

    @Override
    public List<GetAllLoanTypeDTO> getAllLoanType(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc")?Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize,Sort.by(direction,sortBy));
        Page<LoanType> typePage = loanTypeRepository.findAll(pageRequest);
        return typePage.getContent()
                .stream()
                .map(GetAllLoanTypeMapper::toResponse)
                .toList();
    }


    @Override
    public LoanType updatetype(Long id, LoanTypeDTO typeDTO) {

        LoanType existingLoanType = loanTypeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Loan-Type is not found with id: "+id));
        UpdateTypeMapper.updateTypeForDTO(typeDTO,existingLoanType);

        return loanTypeRepository.save(existingLoanType);
    }

    @Override
    public void deleteLoanType(Long id) {
        if (!loanTypeRepository.existsById(id))
        {
            throw new UserNotFoundException(UserNotFoundException.USER_NOT_FOUND);
        }
        loanTypeRepository.deleteLonTypeById(id);
    }


}
