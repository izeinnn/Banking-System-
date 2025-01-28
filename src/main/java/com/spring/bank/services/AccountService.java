package com.spring.bank.services;

import com.spring.bank.Utility.FileStorage;
import com.spring.bank.enums.TransactionType;
import com.spring.bank.models.Account;
import com.spring.bank.models.Transaction;
import java.util.stream.Collectors;
import java.util.List;

public class AccountService {
    private static final String ACCOUNTS_FILE = "data/accounts.dat";
    private static final String TRANSACTIONS_FILE = "data/transactions.dat";


    // Generate unique transaction ID
    private int generateTransactionId() {
        List<Transaction> transactions = FileStorage.readObjects(TRANSACTIONS_FILE, Transaction.class);
        return transactions.isEmpty() ? 1 : transactions.get(transactions.size() - 1).getId() + 1;
    }

    // Save transaction to file
    private void recordTransaction(Transaction transaction) {
        List<Transaction> transactions = FileStorage.readObjects(TRANSACTIONS_FILE, Transaction.class);
        transactions.add(transaction);
        FileStorage.writeObjects(TRANSACTIONS_FILE, transactions);
    }

    // Get transactions for a specific account
    public List<Transaction> getAccountTransactions(String accountNumber) {
        return FileStorage.readObjects(TRANSACTIONS_FILE, Transaction.class).stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
    }

    // Deposit method (to be called from customer menu)
    public boolean deposit(String accountNumber, double amount) {
        List<Account> accounts = FileStorage.readObjects(ACCOUNTS_FILE, Account.class);
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                acc.setBalance(acc.getBalance() + amount);
                FileStorage.writeObjects(ACCOUNTS_FILE, accounts);
                recordTransaction(new Transaction(
                        generateTransactionId(),
                        accountNumber,
                        amount,
                        TransactionType.DEPOSIT
                ));
                return true;
            }
        }
        return false;
    }

    // Withdraw method (to be called from customer menu)
    public boolean withdraw(String accountNumber, double amount) {
        List<Account> accounts = FileStorage.readObjects(ACCOUNTS_FILE, Account.class);
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                if (acc.getBalance() >= amount) {
                    acc.setBalance(acc.getBalance() - amount);
                    FileStorage.writeObjects(ACCOUNTS_FILE, accounts);
                    recordTransaction(new Transaction(
                            generateTransactionId(),
                            accountNumber,
                            amount,
                            TransactionType.WITHDRAWAL
                    ));
                    return true;
                }
            }
        }
        return false;
    }

    // Transfer method (to be called from customer menu)
    public boolean transfer(String fromAccount, String toAccount, double amount) {
        boolean withdrawSuccess = withdraw(fromAccount, amount);
        if (withdrawSuccess) {
            boolean depositSuccess = deposit(toAccount, amount);
            if (depositSuccess) {
                // Record transfer transaction for sender
                recordTransaction(new Transaction(
                        generateTransactionId(),
                        fromAccount,
                        amount,
                        TransactionType.TRANSFER
                ));
                return true;
            }
        }
        return false;
    }
}