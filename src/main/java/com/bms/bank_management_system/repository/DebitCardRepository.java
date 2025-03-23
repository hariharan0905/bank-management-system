package com.bms.bank_management_system.repository;

import com.bms.bank_management_system.entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, String> {
    // Custom query to find a debit card by account number
    DebitCard findByAccountNumber(String accountNumber);
}
