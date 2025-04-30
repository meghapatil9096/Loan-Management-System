package com.neosoft.controller.loan_type;

import com.neosoft.dto.loan_type.GetAllLoanTypeDTO;
import com.neosoft.dto.loan_type.LoanTypeDTO;
import com.neosoft.entity.LoanType;
import com.neosoft.service.loan_type.LoanTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan/type")
public class LoanTypeController {

    @Autowired
    private LoanTypeService loanTypeService;
//  create loan type
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
    @PutMapping("/update/{id}")
    ResponseEntity<LoanType> updatetype(@PathVariable Long id, @RequestBody @Valid LoanTypeDTO typeDTO){
       return ResponseEntity.ok(loanTypeService.updatetype(id,typeDTO));
    }

}
