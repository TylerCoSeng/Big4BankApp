import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager manager = new AccountManager();

        System.out.println("Welcome to The Big 4 Bank CLI");

        while (true) {
            System.out.println("\n1. Create Customer Account\n2. Create Employee Account\n3. Login as Customer\n4. Login as Employee\n5. Exit\nChoose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                manager.createCustomerAccount(new Customer(username, password));

            } else if (option == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                manager.createEmployeeAccount(new Employee(username, password));

            } else if (option == 3) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                if (manager.isLockedOut(username)) {
                    System.out.println("Account locked due to too many failed login attempts.");
                    continue;
                }

                Customer customer = manager.findCustomer(username);
                if (customer != null && customer.isAuthorized(username, password)) {
                    manager.resetLoginAttempts(username);
                    System.out.println("\nLogin successful.");
                    handleCustomerMenu(scanner, customer, manager);
                } else {
                    System.out.println("Invalid login.");
                    manager.recordFailedLogin(username);
                }

            } else if (option == 4) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                Employee employee = manager.findEmployee(username);
                if (employee != null && employee.isAuthorized(username, password)) {
                    System.out.println("\nEmployee login successful.");
                    handleEmployeeMenu(scanner, employee, manager);
                } else {
                    System.out.println("Invalid login.");
                }

            } else if (option == 5) {
                System.out.println("Exiting...");
                break;
            }
        }

        scanner.close();
    }

    private static void handleCustomerMenu(Scanner scanner, Customer customer, AccountManager manager) {
        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. View Balance\n4. View History\n5. Delete Account\n6. Logout\nChoose an option:");
            int choice = scanner.nextInt();

            if (choice == 1) customer.deposit(scanner);
            else if (choice == 2) customer.withdraw(scanner);
            else if (choice == 3) customer.viewBalance();
            else if (choice == 4) customer.showTransactionHistory();
            else if (choice == 5) {
                manager.deleteCustomerAccount(customer.getUsername());
                break;
            }
            else if (choice == 6) {
                System.out.println("Logging out...");
                break;
            }
        }
    }

    private static void handleEmployeeMenu(Scanner scanner, Employee employee, AccountManager manager) {
        while (true) {
            System.out.print("\n1. View Customer Info\n2. Logout\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter customer username: ");
                String target = scanner.nextLine();
                Customer customer = manager.findCustomer(target);
                if (customer != null) {
                    employee.viewCustomerDetails(customer);
                } else {
                    System.out.println("Customer not found.");
                }
            } else if (choice == 2) {
                System.out.println("Logging out...");
                break;
            }
        }
    }
}
