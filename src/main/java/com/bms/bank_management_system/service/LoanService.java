package com.bms.bank_management_system.service;

import com.bms.bank_management_system.entity.Loan;
import com.bms.bank_management_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Apply for a loan
    public String applyLoan(Loan loan) {
        Loan savedLoan = loanRepository.save(loan);
        return "Loan application successful for loan ID: " + savedLoan.getId();
    }

    // Get all loans for an account
    public List<Loan> getLoanDetails(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }

    // Get active loan for an account
    public Loan getActiveLoanByAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumberAndStatus(accountNumber, "ACTIVE");
    }

    // Repay loan amount
    public String repayLoan(String accountNumber, double amount) {
        Optional<Loan> optionalLoan = Optional.ofNullable(getActiveLoanByAccountNumber(accountNumber));
        
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            double remainingBalance = loan.getRemainingBalance();

            if (amount >= remainingBalance) {
                loan.setRemainingBalance(0.0);
                loan.setStatus("CLOSED"); // Loan closed after repayment
            } else {
                loan.setRemainingBalance(remainingBalance - amount);
            }

            loanRepository.save(loan);
            return "Repayment successful. Remaining balance: " + loan.getRemainingBalance();
        } else {
            return "No active loan found for account number: " + accountNumber;
        }
    }

    public Loan saveLoan(Loan loan) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveLoan'");
    }
}
