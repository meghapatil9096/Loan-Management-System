package com.neosoft.repository;

import com.neosoft.entity.LoanApplication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByStatus(LoanApplication.Status status);

    @Modifying
    @Transactional
    @Query("DELETE from LoanApplication a where a.id = :id")
    void deleteLoanAppById(@Param("id") Long id);
}
