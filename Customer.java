import java.util.Scanner;

public class Customer extends Account {
    public Customer(String username, String password) {
        super(username, password);
    }

    public void viewBalance() {
        System.out.println("Current Balance: $" + getBalance());
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String log : getTransactionHistory()) {
            System.out.println(" - " + log);
        }
    }

    public void transferTo(Customer recipient, double amount) {
        if (sendTransfer(amount)) {
            recipient.receiveTransfer(amount);
            getTransactionHistory().add("Transferred: $" + amount + " to " + recipient.getUsername());
            System.out.println("Transferred: $" + amount + " to " + recipient.getUsername());
        } else {
            System.out.println("Transfer failed: insufficient funds or invalid amount.");
        }
    }
}
