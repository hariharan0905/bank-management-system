package com.bms.bank_management_system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String accountNumber;
    private double balance;

    @OneToOne
    private User user;

    public double getBalance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBalance(double d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}