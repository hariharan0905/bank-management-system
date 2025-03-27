package com.bms.bank_management_system.service;

import com.bms.bank_management_system.entity.Statement;
import com.bms.bank_management_system.repository.StatementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bms.bank_management_system.entity.Transaction;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    // Generate and save a new statement
    public Statement saveStatement(Statement statement) {
        return statementRepository.save(statement);
    }

    // Get all statements for an account
    public List<Statement> getStatementsByAccountNumber(String accountNumber) {
        return statementRepository.findByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }

    // Get a statement by ID
    public Statement getStatementById(Long id) {
        return statementRepository.findById(id).orElse(null);
    }

    public List<Transaction> getAccountStatement(String accountNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Transaction> getStatementByDateRange(String accountNumber, String startDate, String endDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
