package com.bms.bank_management_system.controller;

import com.bms.bank_management_system.entity.Loan;
import com.bms.bank_management_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Apply for a loan
    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(@RequestBody Loan loan) {
        String result = loanService.applyLoan(loan);
        return ResponseEntity.ok(result);
    }

    // Get loan details by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Loan>> getLoanDetails(@PathVariable String accountNumber) {
        List<Loan> loans = loanService.getLoanDetails(accountNumber);
        return loans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loans);
    }

    // Repay loan amount
    @PostMapping("/repay")
    public ResponseEntity<?> repayLoan(@RequestParam String accountNumber, @RequestParam double amount) {
        String result = loanService.repayLoan(accountNumber, amount);
        return ResponseEntity.ok(result);
    }
}
