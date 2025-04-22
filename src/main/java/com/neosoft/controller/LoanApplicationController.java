package com.neosoft.controller;

import com.neosoft.dto.ApplyLoanRequest;
import com.neosoft.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    String applyLoan(@RequestBody ApplyLoanRequest request){
        return loanApplicationService.applyLoan(request);
    }
}
