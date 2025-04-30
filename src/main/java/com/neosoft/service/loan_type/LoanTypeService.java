package com.neosoft.service.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;

import java.util.List;

public interface LoanTypeService {

//    Save
    String createLoanType(LoanTypeDTO loanTypeDTO);

//  get By name
    List<LoanType> getByLoanTypeName(String name);

//    get all loan-type
    List<GetAllLoanTypeDTO> getAllLoanType(int pageNo,int pageSize,String sortBy,String sortDir);

//  update loan-type
    LoanType updatetype(Long id, LoanTypeDTO typeDTO);
}
