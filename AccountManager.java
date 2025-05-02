import java.util.*;

public class AccountManager {
    private Map<String, Customer> customers;
    private Map<String, Employee> employees;
    private Map<String, Integer> loginAttempts;

    public AccountManager() {
        customers = new HashMap<>();
        employees = new HashMap<>();
        loginAttempts = new HashMap<>();
    }

    public void createCustomerAccount(Customer customer) {
        customers.put(customer.getUsername(), customer);
        System.out.println("Customer account created for " + customer.getUsername());
    }

    public void createEmployeeAccount(Employee employee) {
        employees.put(employee.getUsername(), employee);
        System.out.println("Employee account created for " + employee.getUsername());
    }

    public void deleteCustomerAccount(String username) {
        customers.remove(username);
        System.out.println("Account deleted for " + username);
    }

    public Customer findCustomer(String username) {
        return customers.get(username);
    }

    public Employee findEmployee(String username) {
        return employees.get(username);
    }

    public boolean isLockedOut(String username) {
        return loginAttempts.getOrDefault(username, 0) >= 5;
    }

    public void recordFailedLogin(String username) {
        loginAttempts.put(username, loginAttempts.getOrDefault(username, 0) + 1);
    }

    public void resetLoginAttempts(String username) {
        loginAttempts.put(username, 0);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
