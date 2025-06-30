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

    // Save loan (used by controller)
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    // Get all loans for a specific account number
    public List<Loan> getLoanDetails(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }

    // Get the currently active loan for a given account
    public Loan getActiveLoanByAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumberAndStatus(accountNumber, "ACTIVE");
    }

    // Repay logic
    public String repayLoan(String accountNumber, double amount) {
        Loan loan = getActiveLoanByAccountNumber(accountNumber);

        if (loan != null) {
            double remainingBalance = loan.getRemainingBalance();

            if (amount >= remainingBalance) {
                loan.setRemainingBalance(0.0);
                loan.setStatus("CLOSED");
            } else {
                loan.setRemainingBalance(remainingBalance - amount);
            }

            loanRepository.save(loan);
            return "Repayment successful. Remaining balance: " + loan.getRemainingBalance();
        } else {
            return "No active loan found for account number: " + accountNumber;
        }
    }
}
