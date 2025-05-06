import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        System.out.println("Welcome to The Big 4 Bank CLI");

        while (true) {
            System.out.println("\n1. Create Customer Account\n2. Login as Customer\n3. Login as Employee\n4. Reset Password\n5. Exit\nChoose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String custUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String custPassword = scanner.nextLine();
                    manager.createCustomerAccount(new Customer(custUsername, custPassword));
                    break;

                case 2:
                    System.out.print("Username: ");
                    String loginCustUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginCustPassword = scanner.nextLine();

                    if (manager.isLockedOut(loginCustUsername)) {
                        System.out.println("Account locked due to too many failed login attempts.");
                        break;
                    }

                    Customer customer = manager.findCustomer(loginCustUsername);
                    if (customer != null && customer.isAuthorized(loginCustUsername, loginCustPassword)) {
                        manager.resetLoginAttempts(loginCustUsername);
                        System.out.println("\nLogin successful.");
                        handleCustomerMenu(scanner, customer, manager);
                    } else {
                        System.out.println("Invalid login.");
                        manager.recordFailedLogin(loginCustUsername);
                    }
                    break;

                case 3:
                    System.out.print("Username: ");
                    String loginEmpUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginEmpPassword = scanner.nextLine();

                    Employee employee = manager.findEmployee(loginEmpUsername);
                    if (employee != null && employee.isAuthorized(loginEmpUsername, loginEmpPassword)) {
                        System.out.println("\nEmployee login successful.");
                        handleEmployeeMenu(scanner, employee, manager);
                    } else {
                        System.out.println("Invalid login.");
                    }
                    break;

                case 4:
                    System.out.print("Enter username to reset password: ");
                    String resetUsername = scanner.nextLine();
                    Customer resetCustomer = manager.findCustomer(resetUsername);
                    if (resetCustomer != null) {
                        resetCustomer.resetPassword(scanner);
                    } else {
                        Employee resetEmployee = manager.findEmployee(resetUsername);
                        if (resetEmployee != null) {
                            resetEmployee.resetPassword(scanner);
                        } else {
                            System.out.println("Account not found.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

    }

    private static void handleCustomerMenu(Scanner scanner, Customer customer, AccountManager manager) {
        while (true) {
            System.out.println("\n1. Create Bank Account\n2. Deposit\n3. Withdraw\n4. View Balance\n5. View History\n6. View All Accounts\n7. Transfer Between Own Accounts\n8. Transfer to Another Customer\n9. Delete Account\n10. Logout\nChoose an option:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new account ID: ");
                    String accountId = scanner.nextLine();
                    customer.createAccount(accountId);
                break;

                case 2:
                    System.out.print("Enter account ID to deposit into: ");
                    String id = scanner.nextLine();
                    Account acc = customer.getAccount(id);
                    if (acc != null) {
                        System.out.print("Enter amount to deposit: $");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        acc.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account ID to withdraw from: ");
                     id = scanner.nextLine();
                     acc = customer.getAccount(id);
                    if (acc != null) {
                        System.out.print("Enter amount to withdraw: $");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        acc.withdraw(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account ID to view balance: ");
                     id = scanner.nextLine();
                     acc = customer.getAccount(id);
                    if (acc != null) {
                        System.out.println("Balance for account " + id + ": $" + acc.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter account ID to view transaction history: ");
                     id = scanner.nextLine();
                    customer.viewAccountHistory(id);
                    break;

                case 6:
                    customer.viewAllAccounts();
                    break;
                case 7:
                    System.out.print("Enter FROM account ID: ");
                    String fromId = scanner.nextLine();
                    System.out.print("Enter TO account ID: ");
                    String toId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    customer.transferBetweenAccounts(fromId, toId, amount);
                    break;
                 case 8:
                    System.out.print("Enter recipient's username: ");
                    String recipientUsername = scanner.nextLine();
                    Customer recipient = manager.findCustomer(recipientUsername);
                    if (recipient != null) {
                        System.out.print("Enter your FROM account ID: ");
                         fromId = scanner.nextLine();
                        System.out.print("Enter recipient's TO account ID: ");
                         toId = scanner.nextLine();
                        System.out.print("Enter amount to transfer: $");
                         amount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        customer.transferToOtherCustomer(recipient, fromId, toId, amount);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                        break;

                    case 9:
                    manager.deleteCustomerAccount(customer.getUsername());
                    break;

            case 10:
                    System.out.println("Logging out...");
                    return;

            default: System.out.println("Invalid choice. Please try again.");
            break;
            }
        }
    }

    private static void handleEmployeeMenu(Scanner scanner, Employee employee, AccountManager manager) {
        while (true) {
            System.out.print("\n1. View Customer Info\n2. View Bank Analytics\n3. Logout\n4.Create Employee Account\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer username: ");
                    String target = scanner.nextLine();
                    Customer customer = manager.findCustomer(target);
                    if (customer != null) {
                        employee.viewCustomerDetails(customer);
                    } else {
                        System.out.println("Customer not found.");
                    }
                break;

                case 2: employee.viewAnalytics(manager.getAllCustomers());
                case 3:
                    System.out.println("Logging out...");
                    return;

                case 4:
                    System.out.print("Enter username: ");
                    String empUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String empPassword = scanner.nextLine();
                    manager.createEmployeeAccount(new Employee(empUsername, empPassword));
                    break;

                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}