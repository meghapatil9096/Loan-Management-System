package com.neosoft.controller.loan_application;

import com.neosoft.dto.loan_application.ApplyLoanDTO;
import com.neosoft.dto.loan_application.GetAllLoanAppDTO;
import com.neosoft.dto.loan_application.UpdateAppDTO;
import com.neosoft.entity.LoanApplication;
import com.neosoft.service.loan_application.LoanApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loan/application")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/customer/apply")
    ResponseEntity<String> applyLoan(@Valid @RequestBody ApplyLoanDTO request){
        return ResponseEntity.ok(loanApplicationService.applyLoan(request));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer/{user_id}")
    ResponseEntity<List<LoanApplication>> getById(@PathVariable Long user_id, HttpServletRequest request){

        Long tokenUserId = (Long) request.getAttribute("userId");
        if (!tokenUserId.equals(user_id)){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(loanApplicationService.getById(user_id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public  ResponseEntity<List<LoanApplication>> showStatus(@PathVariable String status){
        return ResponseEntity.ok(loanApplicationService.showStatus(status.toUpperCase()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/get/all")
    public ResponseEntity<List<GetAllLoanAppDTO>> getAllLoans(@RequestParam(defaultValue = "0") int pageNo,
                                                              @RequestParam(defaultValue = "3") int pageSize,
                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                              @RequestParam(defaultValue = "desc") String sortDir){
        List<GetAllLoanAppDTO> loanAppDTOS = loanApplicationService.getAllLoanApp(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(loanAppDTOS);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{id}")
    ResponseEntity<?> updateLoanApp(@PathVariable Long id,@RequestBody @Valid UpdateAppDTO dto){
        LoanApplication updated = loanApplicationService.updateLoanApp(id,dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    ResponseEntity<String> deleteLoanApp(@PathVariable Long id){
        loanApplicationService.deleteLoanApp(id);
        return ResponseEntity.ok("Loan-Application With Id "+id+" deleted Successfully.");
    }
}
