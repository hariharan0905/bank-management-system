package com.bms.bank_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "loan_type", nullable = false)
    private String loanType; // Home, Personal, Education, etc.

    @Column(name = "loan_amount", nullable = false)
    private double loanAmount;

    @Column(name = "tenure", nullable = false)
    private int tenure; // Duration in months

    @Column(name = "remaining_balance", nullable = false)
    private double remainingBalance;

    @Column(name = "disbursement_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date disbursementDate;

    @Column(name = "status", nullable = false)
    private String status; // ACTIVE, CLOSED, etc.
}
