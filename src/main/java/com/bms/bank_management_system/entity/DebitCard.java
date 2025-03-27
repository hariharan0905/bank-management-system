package com.bms.bank_management_system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard {
    @Id
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }
    private String expiryDate;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}