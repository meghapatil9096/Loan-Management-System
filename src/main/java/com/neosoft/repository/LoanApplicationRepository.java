package com.neosoft.repository;

import com.neosoft.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByStatus(LoanApplication.Status status);
}
