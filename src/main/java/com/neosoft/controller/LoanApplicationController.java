package com.neosoft.controller;

import com.neosoft.dto.ApplyLoanDTO;
import com.neosoft.dto.GetAllLoanAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    String applyLoan(@Valid @RequestBody ApplyLoanDTO request){
        return loanApplicationService.applyLoan(request);
    }

    @GetMapping("/status/{status}")
    public List<LoanApplication> showStatus(@PathVariable String status){
        return loanApplicationService.showStatus(status.toUpperCase());
    }

    @GetMapping("/admin/getallloan")
    public List<GetAllLoanAppDTO> getAllLoans(){
        return loanApplicationService.getAllLoanApplications();
    }

}
