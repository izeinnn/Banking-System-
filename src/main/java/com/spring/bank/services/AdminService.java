package com.spring.bank.services;


import com.spring.bank.Utility.FileStorage;
import com.spring.bank.models.Account;
import com.spring.bank.models.Customer;
import com.spring.bank.models.Transaction;

import java.util.List;


import java.util.List;

public class AdminService {
    public List<Customer> getAllCustomers() {
        return FileStorage.readObjects("data/customers.dat", Customer.class);
    }

    public List<Account> getAllAccounts() {
        return FileStorage.readObjects("data/accounts.dat", Account.class);
    }

    public List<Transaction> getAllTransactions() {
        return FileStorage.readObjects("data/transactions.dat", Transaction.class);
    }
}