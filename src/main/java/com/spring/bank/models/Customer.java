package com.spring.bank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String password;
    private List<String> accountNumbers = new ArrayList<>();

    public Customer(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public List<String> getAccountNumbers() { return accountNumbers; }

    public void addAccount(String accountNumber) {
        accountNumbers.add(accountNumber);
    }
}