package com.bms.bank_management_system.controller;

import com.bms.bank_management_system.entity.Transaction;
import com.bms.bank_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get transaction history by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);
        return transactions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(transactions);
    }

    // Deposit money to account
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        String result = transactionService.deposit(accountNumber, amount);
        return ResponseEntity.ok(result);
    }

    // Withdraw money from account
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        String result = transactionService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(result);
    }
}

