package com.neosoft.controller.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import com.neosoft.service.loan_type.LoanTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan/type")
@RequiredArgsConstructor
public class LoanTypeController {

//    @Autowired
    private final LoanTypeService loanTypeService;

//  create loan type
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/save")
    ResponseEntity<String> createLoanType(@Valid @RequestBody LoanTypeDTO request){
        return ResponseEntity.ok(loanTypeService.createLoanType(request));
    }
// get loan-type by name
    @GetMapping("/get/{name}")
    ResponseEntity<List<LoanType>> FindByLoanTypeName(@PathVariable String name){
        return ResponseEntity.ok(loanTypeService.getByLoanTypeName(name));
    }

//    get all loan-type with paging and sorting 
    @GetMapping("/get/all")
    ResponseEntity<List<GetAllLoanTypeDTO>> getAllLoanType(@RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "3") int pageSize,
                                                           @RequestParam(defaultValue = "id") String sortBy,
                                                           @RequestParam(defaultValue = "asc") String sortDir){
        List<GetAllLoanTypeDTO> loanType = loanTypeService.getAllLoanType(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(loanType);
    }

//    update loan-type
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{id}")
    ResponseEntity<LoanType> updatetype(@PathVariable Long id, @RequestBody @Valid LoanTypeDTO typeDTO){
       return ResponseEntity.ok(loanTypeService.updatetype(id,typeDTO));
    }

//    delete loan-type By id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("admin/delete/{id}")
    ResponseEntity<String> deleteLoanType(@PathVariable Long id){
        loanTypeService.deleteLoanType(id);
        return ResponseEntity.ok("Loan-Type With Id "+id+" deleted Successfully.");
    }

}
