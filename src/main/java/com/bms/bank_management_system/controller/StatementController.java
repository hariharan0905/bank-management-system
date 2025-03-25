package com.bms.bank_management_system.controller;

import com.bms.bank_management_system.entity.Transaction;
import com.bms.bank_management_system.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statements")
public class StatementController {

    @Autowired
    private StatementService statementService;

    // Get account statement by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> getStatement(@PathVariable String accountNumber) {
        List<Transaction> transactions = statementService.getAccountStatement(accountNumber);
        return transactions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(transactions);
    }

    // Get statement within a date range
    @GetMapping("/range")
    public ResponseEntity<List<Transaction>> getStatementByDateRange(
            @RequestParam String accountNumber,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        List<Transaction> transactions = statementService.getStatementByDateRange(accountNumber, startDate, endDate);
        return transactions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(transactions);
    }
}
