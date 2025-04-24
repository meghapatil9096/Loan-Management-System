package com.neosoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Setter(AccessLevel.NONE)
    private String appliedDate;


    public enum Status{
        PENDING,
        APPROVED,
        REJECTED
    }

//    use this method to set the date and time in correct format
    public void setAppliedDate(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.appliedDate = dateTime.format(formatter);
    }


}
