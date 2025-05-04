package Bank;
import java.util.*;

public class Employee {
    private final String username;
    private String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
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

    public void viewCustomerDetails(Customer customer) {
        System.out.println("Customer: " + customer.getUsername());
        customer.viewAllAccounts();
    }

    public void viewAnalytics(Collection<Customer> customers) {
        Customer topCustomer = null;
        double maxBalance = -1;
        double totalBankFunds = 0;

        for (Customer c : customers) {
            double total = 0;
            for (String accId : c.getAccountIds()) {
                Account acc = c.getAccount(accId);
                total += acc.getBalance();
            }
            totalBankFunds += total;
            if (total > maxBalance) {
                maxBalance = total;
                topCustomer = c;
            }
        }

        System.out.println("\n=== Bank Analytics ===");
        System.out.println("Total Bank Holdings: $" + totalBankFunds);
        if (topCustomer != null) {
            System.out.println("Customer with Highest Total Balance: " + topCustomer.getUsername() + " ($" + maxBalance + ")");
        } else {
            System.out.println("No customers found.");
        }
    }
}
