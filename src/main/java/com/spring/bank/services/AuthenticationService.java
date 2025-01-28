package com.spring.bank.services;


import com.spring.bank.Utility.FileStorage;
import com.spring.bank.models.Customer;

import java.util.List;

public class AuthenticationService {
    private static final String CUSTOMERS_FILE = "customers.dat";

    public Customer authenticateCustomer(int customerId, String password) {
        List<Customer> customers = FileStorage.readObjects(CUSTOMERS_FILE, Customer.class);
        return customers.stream()
                .filter(c -> c.getId() == customerId && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean authenticateAdmin(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }

    public void registerCustomer(String name, String password) {
        List<Customer> customers = FileStorage.readObjects(CUSTOMERS_FILE, Customer.class);
        int newId = customers.isEmpty() ? 1 : customers.get(customers.size() - 1).getId() + 1;
        customers.add(new Customer(newId, name, password));
        FileStorage.writeObjects(CUSTOMERS_FILE, customers);
    }
}