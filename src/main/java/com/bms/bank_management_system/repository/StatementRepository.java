package com.bms.bank_management_system.repository;

import com.bms.bank_management_system.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    List<Statement> findByAccountNumber(String accountNumber);

    List<Statement> findByAccountNumberOrderByTransactionDateDesc(String accountNumber);
}
