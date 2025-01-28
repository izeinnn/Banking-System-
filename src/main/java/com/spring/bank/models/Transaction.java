package com.spring.bank.models;


import com.spring.bank.Utility.FileStorage;
import com.spring.bank.enums.TransactionType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Transaction implements Serializable {
    private int id;
    private final String accountNumber;
    private double amount;
    private TransactionType type;
    private Date timestamp;

    public Transaction(int id, String accountNumber, double amount, TransactionType type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = new Date();
    }

    // Getters
    public int getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public Date getTimestamp() { return timestamp; }
}