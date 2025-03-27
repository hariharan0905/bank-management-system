package com.bms.bank_management_system.service;

import com.bms.bank_management_system.entity.Loan;
import com.bms.bank_management_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Save loan details
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public String applyLoan(Loan loan) {
        return "Loan application successful for loan ID: " + loan.getId();
    }

    // Get all loans for an account
    public List<Loan> getLoansByAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }

    // Get active loan for an account
    public Loan getActiveLoanByAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumberAndStatus(accountNumber, "ACTIVE");
    }

    public List<Loan> getLoanDetails(String accountNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String repayLoan(String accountNumber, double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
