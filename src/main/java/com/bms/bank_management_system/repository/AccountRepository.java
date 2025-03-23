package com.bms.bank_management_system.repository;

import com.bms.bank_management_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Custom query to find an account by account number
    Account findByAccountNumber(String accountNumber);
}
