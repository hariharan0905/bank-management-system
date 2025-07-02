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

    @PostMapping("/apply")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> applyLoan(@RequestBody Loan loan) {
        try {
            Loan savedLoan = loanService.saveLoan(loan);
            return ResponseEntity.ok("Loan application successful for loan ID: " + savedLoan.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Loan application failed: " + e.getMessage());
        }
    }

    /**
     * Repay a loan amount by account number.
     */
    @PostMapping("/repay")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> repayLoan(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            String result = loanService.repayLoan(accountNumber, amount);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Loan repayment failed: " + e.getMessage());
        }
    }

    /**
     * Get loan details by account number.
     */
    @GetMapping("/{accountNumber}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getLoanDetails(@PathVariable String accountNumber) {
        try {
            List<Loan> loans = loanService.getLoanDetails(accountNumber);
            if (loans.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(loans);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching loan details: " + e.getMessage());
        }
    }
}
