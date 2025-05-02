import java.util.*;

public class Account {
    private double balance;
    private String username;
    private String password;
    private List<String> transactionHistory;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthorized(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    protected void receiveTransfer(double amount) {
        balance += amount;
        transactionHistory.add("Received: $" + amount);
    }

    protected boolean sendTransfer(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Sent: $" + amount);
            return true;
        } else {
            return false;
        }
    }
}
