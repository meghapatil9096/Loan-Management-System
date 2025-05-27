package com.neosoft.entity;

import com.neosoft.auditing.entity.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "loan-type")
@Data
@ToString
public class LoanType extends Auditable {

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
