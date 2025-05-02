public class Employee extends Account {
    public Employee(String username, String password) {
        super(username, password);
    }

    public void viewCustomerDetails(Customer customer) {
        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Balance: $" + customer.getBalance());
        customer.showTransactionHistory();
    }
}
