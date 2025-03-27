package com.bms.bank_management_system.service;

import com.bms.bank_management_system.entity.Transaction;
import com.bms.bank_management_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Save a new transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Get all transactions by account number
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }

    // Get transaction by ID
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public String deposit(String accountNumber, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    public String withdraw(String accountNumber, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
