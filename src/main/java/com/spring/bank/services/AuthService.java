package com.spring.bank.services;


import com.spring.bank.Utility.FileStorage;
import com.spring.bank.models.Customer;

import java.util.List;

public class AuthService {
    private static final String CUSTOMERS_FILE = "data/customers.dat";

    public Customer authenticateCustomer(int customerId, String password) {
        // Add Customer.class as second parameter
        List<Customer> customers = FileStorage.readObjects(CUSTOMERS_FILE, Customer.class);
        return customers.stream()
                .filter(c -> c.getId() == customerId && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean registerCustomer(String name, String password) {
        // Fix here too
        List<Customer> customers = FileStorage.readObjects(CUSTOMERS_FILE, Customer.class);
        int newId = customers.isEmpty() ? 1001 : customers.get(customers.size()-1).getId() + 1;
        customers.add(new Customer(newId, name, password));
        FileStorage.writeObjects(CUSTOMERS_FILE, customers);
        return true;
    }

    public boolean authenticateAdmin(String username, String password) {
        // Hardcoded admin credentials (replace with secure method later)
        return "admin".equals(username) && "admin123".equals(password);
    }
}