package com.bms.bank_management_system.payload;

public class LoginRequest {
    private String accountNumber;
    private String pin;

    // Constructor
    public LoginRequest() {}

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
