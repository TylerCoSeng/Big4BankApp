import java.util.*;

public class Customer {
    private final String username;
    private String password;
    private final Map<String, Account> accounts;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthorized(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public void resetPassword(Scanner scanner) {
        System.out.print("To reset your password, enter your username to verify your identity: ");
        String input = scanner.nextLine();
        if (input.equals(this.username)) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();
            this.password = newPassword;
            System.out.println("Password has been reset successfully.");
        } else {
            System.out.println("Verification failed. Username does not match.");
        }
    }

    public void createAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            System.out.println("Account with this ID already exists.");
        } else {
            accounts.put(accountId, new Account(accountId));
            System.out.println("New account created with ID: " + accountId);
        }
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account acc : accounts.values()) {
                System.out.println("Account ID: " + acc.getAccountId() + " | Balance: $" + acc.getBalance());
            }
        }
    }

    public void viewAccountHistory(String accountId) {
        Account acc = accounts.get(accountId);
        if (acc != null) {
            acc.showTransactionHistory();
        } else {
            System.out.println("Account ID not found.");
        }
    }

    public Set<String> getAccountIds() {
        return accounts.keySet();
    }
}
