package com.neosoft.service.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import com.neosoft.mapper.loan_type.GetAllLoanTypeMapper;
import com.neosoft.mapper.loan_type.LoanTypeMapper;
import com.neosoft.mapper.loan_type.UpdateTypeMapper;
import com.neosoft.repository.LoanTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanTypeServiceImp implements LoanTypeService {

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Autowired
    private LoanTypeMapper loanTypeMapper;

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
    public List<GetAllLoanTypeDTO> getAllLoanType() {
        List<LoanType> types = loanTypeRepository.findAll();
        return GetAllLoanTypeMapper.toResponseList(types);
    }


}
