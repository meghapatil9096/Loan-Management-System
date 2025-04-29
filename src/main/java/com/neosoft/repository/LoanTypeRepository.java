package com.neosoft.repository;

import com.neosoft.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType,Long> {

    List<LoanType> findByName(String name);
}
