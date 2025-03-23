package com.bms.bank_management_system.service;

import com.bms.bank_management_system.entity.DebitCard;
import com.bms.bank_management_system.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    // Save a new debit card
    public DebitCard saveDebitCard(DebitCard debitCard) {
        return debitCardRepository.save(debitCard);
    }

    // Get debit card details by account number
    public DebitCard getDebitCardByAccountNumber(String accountNumber) {
        return debitCardRepository.findByAccountNumber(accountNumber);
    }

    // Delete a debit card by account number
    public void deleteDebitCard(String accountNumber) {
        debitCardRepository.deleteById(accountNumber);
    }
}
