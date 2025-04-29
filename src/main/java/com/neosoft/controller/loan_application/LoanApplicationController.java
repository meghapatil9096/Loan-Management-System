package com.neosoft.controller.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.service.loan_application.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan/application")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/customer/apply")
    ResponseEntity<String> applyLoan(@Valid @RequestBody ApplyLoanDTO request){
        return ResponseEntity.ok(loanApplicationService.applyLoan(request));
    }

    @GetMapping("/status/{status}")
    public  ResponseEntity<List<LoanApplication>> showStatus(@PathVariable String status){
        return ResponseEntity.ok(loanApplicationService.showStatus(status.toUpperCase()));
    }

    @GetMapping("/admin/get/all")
    public ResponseEntity<List<GetAllLoanAppDTO>> getAllLoans(){
        return ResponseEntity.ok(loanApplicationService.getAllLoanApp());
    }

}
