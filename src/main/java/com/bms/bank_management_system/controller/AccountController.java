package com.bms.bank_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.bank_management_system.entity.Account;
import com.bms.bank_management_system.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Get account details by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    // Deposit money
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<?> deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        Account account = accountService.depositMoney(accountNumber, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().body("Invalid account number");
    }

    // Withdraw money
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        Account account = accountService.withdrawMoney(accountNumber, amount);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.badRequest().body("Insufficient funds or invalid account");
    }
}
