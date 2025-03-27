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

    public Long getId() {
        return id;
    }

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    @Column(name = "loan_type", nullable = false)
    private String loanType; // Home, Personal, Education, etc.

    public String getLoanType() {
        return loanType;
    }

    @Column(name = "loan_amount", nullable = false)
    private double loanAmount;

    public double getLoanAmount() {
        return loanAmount;
    }

    @Column(name = "tenure", nullable = false)
    private int tenure; // Duration in months

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    @Column(name = "remaining_balance", nullable = false)
    private double remainingBalance;

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    @Column(name = "disbursement_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date disbursementDate;

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    @Column(name = "status", nullable = false)
    private String status; // ACTIVE, CLOSED, etc.

    public String getStatus() {
        return status;
    }

}
