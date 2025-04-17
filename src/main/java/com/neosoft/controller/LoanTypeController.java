package com.neosoft.controller;

import com.neosoft.dto.LoanTypeRequest;
import com.neosoft.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loantype")
public class LoanTypeController {

    @Autowired
    private LoanTypeService loanTypeService;

    @PostMapping("/save")
    String createLoanType(@RequestBody LoanTypeRequest request){
        return loanTypeService.createLoanType(request);
    }

}
