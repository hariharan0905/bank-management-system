package com.bms.bank_management_system.repository;

import com.bms.bank_management_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByAccountNumber(String accountNumber);

    Loan findByAccountNumberAndStatus(String accountNumber, String status);
}
