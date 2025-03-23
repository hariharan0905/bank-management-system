package com.bms.bank_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.bank_management_system.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByAccountNumberAndPin(String accountNumber, String pin);
}
