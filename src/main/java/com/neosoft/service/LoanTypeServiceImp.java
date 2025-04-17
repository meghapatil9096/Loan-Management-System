package com.neosoft.service;

import com.neosoft.dto.LoanTypeRequest;
import com.neosoft.entity.LoanType;
import com.neosoft.mapper.LoanTypeMapper;
import com.neosoft.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanTypeServiceImp implements LoanTypeService {

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Autowired
    private LoanTypeMapper loanTypeMapper;

    @Override
    public String createLoanType(LoanTypeRequest loanTypeRequest) {

        LoanType loanType = loanTypeMapper.createRequestDtoToLoanType(loanTypeRequest);
        loanTypeRepository.save(loanType);

        return "Loan type created successfully!";
    }
}
