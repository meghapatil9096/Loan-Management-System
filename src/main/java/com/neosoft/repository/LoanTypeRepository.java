package com.neosoft.repository;

import com.neosoft.entity.LoanType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType,Long> {

    List<LoanType> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE from LoanType l WHERE l.id = :id")
    void deleteLonTypeById(@Param("id") Long id);
}
