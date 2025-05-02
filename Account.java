import java.util.*;

public class Account {
    private double balance;
    private final String accountId;
    private final List<String> transactionHistory;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void receiveTransfer(double amount) {
        balance += amount;
        transactionHistory.add("Received: $" + amount);
    }

    public boolean sendTransfer(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Sent: $" + amount);
            return true;
        } else {
            return false;
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History for Account ID: " + accountId);
        for (String log : transactionHistory) {
            System.out.println(" - " + log);
        }
    }
}
