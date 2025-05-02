import java.util.Scanner;

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
}
