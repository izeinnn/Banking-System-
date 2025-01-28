package com.spring.bank.models;


import com.spring.bank.enums.AccountType;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountNumber;
    private int customerId;
    private double balance;
    private AccountType type;
    private double interestRate;

    public Account(String accountNumber, int customerId, AccountType type, double balance) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.type = type;
        this.balance = balance;
        this.interestRate = (type == AccountType.SAVINGS) ? 2.5 : 0.0;
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public int getCustomerId() { return customerId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public AccountType getType() { return type; }
    public double getInterestRate() { return interestRate; }
}