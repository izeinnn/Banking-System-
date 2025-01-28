package com.spring.bank;

import com.spring.bank.Utility.InputHelper;
import com.spring.bank.models.Account;
import com.spring.bank.models.Customer;
import com.spring.bank.models.Transaction;
import com.spring.bank.services.AccountService;
import com.spring.bank.services.AdminService;
import com.spring.bank.services.AuthService;

import java.util.List;
import java.util.Scanner;


public class BankApp {
    private static final AuthService authService = new AuthService();
    private static final AccountService accountService = new AccountService();
    private static final AdminService adminService = new AdminService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Customer Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Register");
            System.out.println("4. Exit");

            int choice = InputHelper.getIntInput("Choose an option: ");
            switch (choice) {
                case 1 -> customerLogin();
                case 2 -> adminLogin();
                case 3 -> registerCustomer();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void customerLogin() {
        int customerId = InputHelper.getIntInput("Customer ID: ");
        String password = InputHelper.getStringInput("Password: ");

        Customer customer = authService.authenticateCustomer(customerId, password);
        if (customer == null) {
            System.out.println("Invalid credentials!");
            return;
        }
        System.out.println("Login successful!");
        customerMenu(customer);
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
//            System.out.println("1. View Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transactions");
            System.out.println("6. Logout");

            int choice = InputHelper.getIntInput("Choose an option: ");
            switch (choice) {
//                case 1 -> viewAccounts(customer);
                case 2 -> deposit(customer);
                case 3 -> withdraw(customer);
                case 4 -> transfer(customer);
                case 5 -> viewTransactions(customer);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

//    private static void viewAccounts(Customer customer) {
//        List<Account> accounts = accountService.getCustomerAccounts(customer.getId());
//        accounts.forEach(System.out::println);
//    }

    private static void deposit(Customer customer) {
        String accountNumber = InputHelper.getStringInput("Account Number: ");
        double amount = InputHelper.getDoubleInput("Amount: ");
        if (accountService.deposit(accountNumber, amount)) {
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Deposit failed!");
        }
    }
    private static void withdraw(Customer customer) {
        String accountNumber = InputHelper.getStringInput("Account Number: ");
        double amount = InputHelper.getDoubleInput("Amount: ");
        if (accountService.withdraw(accountNumber, amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Withdrawal failed!");
        }
    }
    private static void transfer(Customer customer) {
        String fromAccount = InputHelper.getStringInput("From Account Number: ");
        String toAccount = InputHelper.getStringInput("To Account Number: ");
        double amount = InputHelper.getDoubleInput("Amount: ");
        if (accountService.transfer(fromAccount, toAccount, amount)) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed!");
        }
    }
    private static void viewTransactions(Customer customer) {
        String accountNumber = InputHelper.getStringInput("Account Number: ");
        List<Transaction> transactions = accountService.getAccountTransactions(accountNumber);
        transactions.forEach(System.out::println);
    }

private static void adminLogin() {
    String username = InputHelper.getStringInput("Username: ");
    String password = InputHelper.getStringInput("Password: ");

    if (!authService.authenticateAdmin(username, password)) {
        System.out.println("Invalid admin credentials!");
        return;
    }
    System.out.println("Login successful!");
    adminMenu();
}

    private static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View All Customers");
            System.out.println("2. View All Accounts");
            System.out.println("3. View All Transactions");
            System.out.println("4. Logout");

            int choice = InputHelper.getIntInput("Choose an option: ");
            switch (choice) {
                case 1 -> viewAllCustomers();
                case 2 -> viewAllAccounts();
                case 3 -> viewAllTransactions();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private static void viewAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        customers.forEach(System.out::println);
    }
    private static void viewAllAccounts() {
        List<Account> accounts = adminService.getAllAccounts();
        accounts.forEach(System.out::println);
    }
    private static void viewAllTransactions() {
        List<Transaction> transactions = adminService.getAllTransactions();
        transactions.forEach(System.out::println);
    }
    private static void registerCustomer() {
        String name = InputHelper.getStringInput("Name: ");
        String password = InputHelper.getStringInput("Password: ");
        authService.registerCustomer(name, password);
        System.out.println("Registration successful!");
    }


}