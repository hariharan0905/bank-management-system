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
@Table(name = "transactions")
public class Transaction {

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

    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // CREDIT, DEBIT

    public String getTransactionType() {
        return transactionType;
    }

    @Column(name = "amount", nullable = false)
    private double amount;

    public double getAmount() {
        return amount;
    }

    @Column(name = "transaction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
}
