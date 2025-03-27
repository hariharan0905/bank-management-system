package com.bms.bank_management_system.controller;

import com.bms.bank_management_system.entity.DebitCard;
import com.bms.bank_management_system.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private DebitCardService debitCardService;

    // Get debit card details by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getCardDetails(@PathVariable String accountNumber) {
        DebitCard debitCard = debitCardService.getDebitCardByAccountNumber(accountNumber);
        return debitCard != null ? ResponseEntity.ok(debitCard) : ResponseEntity.notFound().build();
    }

    /*  // Add a new debit card
    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestBody DebitCard debitCard) {
        DebitCard savedCard = debitCardService.addDebitCard(debitCard);
        return ResponseEntity.ok(savedCard);
    } 
    */

    // Delete debit card
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteCard(@PathVariable String accountNumber) {
        debitCardService.deleteDebitCard(accountNumber);
        return ResponseEntity.ok("Debit card deleted successfully");
    }
}
