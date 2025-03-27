package com.bms.bank_management_system.controller;

import com.bms.bank_management_system.entity.Loan;
import com.bms.bank_management_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Apply for a loan - Restricted to users with 'USER' role
    @PostMapping("/apply")
    @PreAuthorize("hasAuthority('USER')")  // Ensure you enable @EnableGlobalMethodSecurity(prePostEnabled = true) in SecurityConfig
    public ResponseEntity<?> applyLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.saveLoan(loan);
        return ResponseEntity.ok("Loan application successful for loan ID: " + savedLoan.getId());
    }

    // Get loan details by account number
    @GetMapping("/{accountNumber}")
    @PreAuthorize("hasAuthority('USER')")  // Restrict access to 'USER' only
    public ResponseEntity<List<Loan>> getLoanDetails(@PathVariable String accountNumber) {
        List<Loan> loans = loanService.getLoanDetails(accountNumber);
        return loans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loans);
    }

    // Repay loan amount
    @PostMapping("/repay")
    @PreAuthorize("hasAuthority('USER')")  // Restricted to 'USER' role
    public ResponseEntity<?> repayLoan(@RequestParam String accountNumber, @RequestParam double amount) {
        String result = loanService.repayLoan(accountNumber, amount);
        return ResponseEntity.ok(result);
    }
}
