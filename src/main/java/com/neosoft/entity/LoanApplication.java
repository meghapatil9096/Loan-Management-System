package com.neosoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan-application")
@Data
@ToString
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    FK to User Table (who applied)
    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

//    FK to Loan-Type table (Type of loan applied)
    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime appliedDate;

    public enum Status{
        PENDING,
        APPROVED,
        REJECTED
    }



}
