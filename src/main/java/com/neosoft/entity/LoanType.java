package com.neosoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "loan-type")
@Data
@ToString
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private Integer durationMonth;

    private String description;
}
