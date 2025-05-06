import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BankGUI {
    private AccountManager manager;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Login panels
    private JPanel loginPanel;
    private JPanel customerLoginPanel;
    private JPanel employeeLoginPanel;
    
    // Main panels
    private JPanel customerPanel;
    private JPanel employeePanel;
    private JPanel createAccountPanel;
    
    // Current user references
    private Customer currentCustomer;
    private Employee currentEmployee;
    
    public BankGUI(AccountManager manager) {
        this.manager = manager;
        initialize();
    }
    
    private void initialize() {
        frame = new JFrame("The Big 4 Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        createLoginPanel();
        createCustomerLoginPanel();
        createEmployeeLoginPanel();
        createCustomerPanel();
        createEmployeePanel();
        createAccountPanel();
        
        mainPanel.add(loginPanel, "login");
        mainPanel.add(customerLoginPanel, "customerLogin");
        mainPanel.add(employeeLoginPanel, "employeeLogin");
        mainPanel.add(customerPanel, "customer");
        mainPanel.add(employeePanel, "employee");
        mainPanel.add(createAccountPanel, "createAccount");
        
        frame.add(mainPanel);
        cardLayout.show(mainPanel, "login");
        frame.setVisible(true);
    }
    
    private void createLoginPanel() {
        loginPanel = new JPanel(new BorderLayout(0, 20));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        loginPanel.setBackground(new Color(240, 240, 240)); 
    
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(240, 240, 240)); 
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel titleLabel = new JLabel("Welcome to The Big 4 Bank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.DARK_GRAY); 
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel subtitleLabel = new JLabel("Your trusted financial partner");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.DARK_GRAY); 
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(subtitleLabel);
    

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 15));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        
        JButton createAccountBtn = createStyledButton("Create Customer Account", new Color(0, 102, 204));
        JButton employeeLoginBtn = createStyledButton("Login as Employee", new Color(76, 175, 80));
        JButton customerLoginBtn = createStyledButton("Login as Customer", new Color(255, 152, 0));
        JButton exitBtn = createStyledButton("Exit Application", new Color(244, 67, 54));

        createAccountBtn.addActionListener(e -> cardLayout.show(mainPanel, "createAccount"));
        customerLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "customerLogin"));
        employeeLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "employeeLogin"));
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(createAccountBtn);
        buttonPanel.add(customerLoginBtn);
        buttonPanel.add(employeeLoginBtn);
        buttonPanel.add(exitBtn);

        
        loginPanel.add(headerPanel, BorderLayout.NORTH);
        loginPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void createCustomerLoginPanel() {
        customerLoginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        customerLoginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginBtn = new JButton("Login");
        JButton resetPasswordBtn = new JButton("Reset Password");
        JButton backBtn = new JButton("Back");
        
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (manager.isLockedOut(username)) {
                JOptionPane.showMessageDialog(frame, "Account locked due to too many failed login attempts.");
                return;
            }
            
            Customer customer = manager.findCustomer(username);
            if (customer != null && customer.isAuthorized(username, password)) {
                manager.resetLoginAttempts(username);
                currentCustomer = customer;
                updateCustomerPanel();
                cardLayout.show(mainPanel, "customer");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login.");
                manager.recordFailedLogin(username);
            }
        });
        
        resetPasswordBtn.addActionListener(e -> {
            String username = usernameField.getText();
            Customer customer = manager.findCustomer(username);
            if (customer != null) {
                String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
                if (newPassword != null && !newPassword.isEmpty()) {
                    customer.resetPassword(new Scanner(newPassword));
                    JOptionPane.showMessageDialog(frame, "Password reset successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Customer not found.");
            }
        });
        
        backBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "login");
        });
        
        customerLoginPanel.add(usernameLabel);
        customerLoginPanel.add(usernameField);
        customerLoginPanel.add(passwordLabel);
        customerLoginPanel.add(passwordField);
        customerLoginPanel.add(loginBtn);
        customerLoginPanel.add(resetPasswordBtn);
        customerLoginPanel.add(backBtn);
    }
    
    private void createEmployeeLoginPanel() {
        employeeLoginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        employeeLoginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginBtn = new JButton("Login");
        JButton resetPasswordBtn = new JButton("Reset Password");
        JButton backBtn = new JButton("Back");
        
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            Employee employee = manager.findEmployee(username);
            if (employee != null && employee.isAuthorized(username, password)) {
                currentEmployee = employee;
                updateEmployeePanel();
                cardLayout.show(mainPanel, "employee");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login.");
            }
        });
        
        resetPasswordBtn.addActionListener(e -> {
            String username = usernameField.getText();
            Employee employee = manager.findEmployee(username);
            if (employee != null) {
                String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
                if (newPassword != null && !newPassword.isEmpty()) {
                    employee.resetPassword(new Scanner(newPassword));
                    JOptionPane.showMessageDialog(frame, "Password reset successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.");
            }
        });
        
        backBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "login");
        });
        
        employeeLoginPanel.add(usernameLabel);
        employeeLoginPanel.add(usernameField);
        employeeLoginPanel.add(passwordLabel);
        employeeLoginPanel.add(passwordField);
        employeeLoginPanel.add(loginBtn);
        employeeLoginPanel.add(resetPasswordBtn);
        employeeLoginPanel.add(backBtn);
    }
    
    private void createCustomerPanel() {
        customerPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton createAccountBtn = new JButton("Create Bank Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton viewBalanceBtn = new JButton("View Balance");
        JButton viewHistoryBtn = new JButton("View Transaction History");
        JButton viewAllAccountsBtn = new JButton("View All Accounts");
        JButton transferOwnBtn = new JButton("Transfer Between Accounts");
        JButton transferOtherBtn = new JButton("Transfer to Another Customer");
        JButton deleteAccountBtn = new JButton("Delete My Account");
        JButton logoutBtn = new JButton("Logout");
        
        createAccountBtn.addActionListener(e -> {
            String accountId = JOptionPane.showInputDialog(frame, "Enter new account ID:");
            if (accountId != null && !accountId.isEmpty()) {
                currentCustomer.createAccount(accountId);
                updateCustomerPanel();
            }
        });
        
        depositBtn.addActionListener(e -> {
            String accountId = JOptionPane.showInputDialog(frame, "Enter account ID to deposit into:");
            if (accountId != null && !accountId.isEmpty()) {
                Account acc = currentCustomer.getAccount(accountId);
                if (acc != null) {
                    String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                    try {
                        double amount = Double.parseDouble(amountStr);
                        acc.deposit(amount);
                        updateCustomerPanel();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });
        
        withdrawBtn.addActionListener(e -> {
            String accountId = JOptionPane.showInputDialog(frame, "Enter account ID to withdraw from:");
            if (accountId != null && !accountId.isEmpty()) {
                Account acc = currentCustomer.getAccount(accountId);
                if (acc != null) {
                    String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                    try {
                        double amount = Double.parseDouble(amountStr);
                        acc.withdraw(amount);
                        updateCustomerPanel();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });
        
        viewBalanceBtn.addActionListener(e -> {
            String accountId = JOptionPane.showInputDialog(frame, "Enter account ID to view balance:");
            if (accountId != null && !accountId.isEmpty()) {
                Account acc = currentCustomer.getAccount(accountId);
                if (acc != null) {
                    JOptionPane.showMessageDialog(frame, 
                        "Balance for account " + accountId + ": $" + acc.getBalance());
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });
        
        viewHistoryBtn.addActionListener(e -> {
            String accountId = JOptionPane.showInputDialog(frame, "Enter account ID to view history:");
            if (accountId != null && !accountId.isEmpty()) {
                Account acc = currentCustomer.getAccount(accountId);
                if (acc != null) {
                    StringBuilder history = new StringBuilder();
                    for (String log : acc.getTransactionHistory()) {
                        history.append(log).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, 
                        new JScrollPane(new JTextArea(history.toString())), 
                        "Transaction History for " + accountId, 
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });
        
        viewAllAccountsBtn.addActionListener(e -> {
            StringBuilder accountsInfo = new StringBuilder();
            for (Account acc : currentCustomer.getAccountIds().stream()
                    .map(id -> currentCustomer.getAccount(id))
                    .toArray(Account[]::new)) {
                accountsInfo.append("Account ID: ").append(acc.getAccountId())
                          .append(" | Balance: $").append(acc.getBalance()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, 
                new JScrollPane(new JTextArea(accountsInfo.toString())), 
                "All Accounts", 
                JOptionPane.PLAIN_MESSAGE);
        });
        
        transferOwnBtn.addActionListener(e -> {
            JPanel transferPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField fromField = new JTextField();
            JTextField toField = new JTextField();
            JTextField amountField = new JTextField();
            
            transferPanel.add(new JLabel("From Account ID:"));
            transferPanel.add(fromField);
            transferPanel.add(new JLabel("To Account ID:"));
            transferPanel.add(toField);
            transferPanel.add(new JLabel("Amount:"));
            transferPanel.add(amountField);
            
            int result = JOptionPane.showConfirmDialog(frame, transferPanel, 
                "Transfer Between Accounts", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    currentCustomer.transferBetweenAccounts(
                        fromField.getText(), toField.getText(), amount);
                    updateCustomerPanel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.");
                }
            }
        });
        
        transferOtherBtn.addActionListener(e -> {
            JPanel transferPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField recipientField = new JTextField();
            JTextField fromField = new JTextField();
            JTextField toField = new JTextField();
            JTextField amountField = new JTextField();
            
            transferPanel.add(new JLabel("Recipient Username:"));
            transferPanel.add(recipientField);
            transferPanel.add(new JLabel("From Account ID:"));
            transferPanel.add(fromField);
            transferPanel.add(new JLabel("To Account ID:"));
            transferPanel.add(toField);
            transferPanel.add(new JLabel("Amount:"));
            transferPanel.add(amountField);
            
            int result = JOptionPane.showConfirmDialog(frame, transferPanel, 
                "Transfer to Another Customer", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    Customer recipient = manager.findCustomer(recipientField.getText());
                    if (recipient != null) {
                        currentCustomer.transferToOtherCustomer(
                            recipient, fromField.getText(), toField.getText(), amount);
                        updateCustomerPanel();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Recipient not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.");
                }
            }
        });
        
        deleteAccountBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to delete your account?", 
                "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                manager.deleteCustomerAccount(currentCustomer.getUsername());
                currentCustomer = null;
                cardLayout.show(mainPanel, "login");
            }
        });
        
        logoutBtn.addActionListener(e -> {
            currentCustomer = null;
            cardLayout.show(mainPanel, "login");
        });
        
        buttonPanel.add(createAccountBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(viewBalanceBtn);
        buttonPanel.add(viewHistoryBtn);
        buttonPanel.add(viewAllAccountsBtn);
        buttonPanel.add(transferOwnBtn);
        buttonPanel.add(transferOtherBtn);
        buttonPanel.add(deleteAccountBtn);
        buttonPanel.add(logoutBtn);
        
        customerPanel.add(new JLabel("Customer Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        customerPanel.add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void createEmployeePanel() {
        employeePanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton viewCustomerBtn = new JButton("View Customer Info");
        JButton viewAnalyticsBtn = new JButton("View Bank Analytics");
        JButton createEmployeeBtn = new JButton("Create Employee Account");
        JButton logoutBtn = new JButton("Logout");
        
        viewCustomerBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(frame, "Enter customer username:");
            if (username != null && !username.isEmpty()) {
                Customer customer = manager.findCustomer(username);
                if (customer != null) {
                    StringBuilder customerInfo = new StringBuilder();
                    customerInfo.append("Customer: ").append(customer.getUsername()).append("\n\n");
                    customerInfo.append("Accounts:\n");
                    
                    for (String accId : customer.getAccountIds()) {
                        Account acc = customer.getAccount(accId);
                        customerInfo.append(" - ID: ").append(accId)
                                  .append(" | Balance: $").append(acc.getBalance()).append("\n");
                    }
                    
                    JOptionPane.showMessageDialog(frame, 
                        new JScrollPane(new JTextArea(customerInfo.toString())), 
                        "Customer Details", 
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                }
            }
        });
        
        viewAnalyticsBtn.addActionListener(e -> {
            Collection<Customer> customers = manager.getAllCustomers();
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

            StringBuilder analytics = new StringBuilder();
            analytics.append("=== Bank Analytics ===\n");
            analytics.append("Total Bank Holdings: $").append(totalBankFunds).append("\n");
            if (topCustomer != null) {
                analytics.append("Customer with Highest Total Balance: ")
                        .append(topCustomer.getUsername())
                        .append(" ($").append(maxBalance).append(")");
            } else {
                analytics.append("No customers found.");
            }
            
            JOptionPane.showMessageDialog(frame, analytics.toString(), "Bank Analytics", JOptionPane.INFORMATION_MESSAGE);
        });
        
        createEmployeeBtn.addActionListener(e -> {
            JPanel empPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            
            empPanel.add(new JLabel("Username:"));
            empPanel.add(usernameField);
            empPanel.add(new JLabel("Password:"));
            empPanel.add(passwordField);
            
            int result = JOptionPane.showConfirmDialog(frame, empPanel, 
                "Create Employee Account", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (!username.isEmpty() && !password.isEmpty()) {
                    manager.createEmployeeAccount(new Employee(username, password));
                    JOptionPane.showMessageDialog(frame, "Employee account created successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.");
                }
            }
        });
        
        logoutBtn.addActionListener(e -> {
            currentEmployee = null;
            cardLayout.show(mainPanel, "login");
        });
        
        buttonPanel.add(viewCustomerBtn);
        buttonPanel.add(viewAnalyticsBtn);
        buttonPanel.add(createEmployeeBtn);
        buttonPanel.add(logoutBtn);
        
        employeePanel.add(new JLabel("Employee Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        employeePanel.add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void createAccountPanel() {
        createAccountPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        createAccountPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        
        JButton createBtn = new JButton("Create Account");
        JButton backBtn = new JButton("Back");
        
        createBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (!username.isEmpty() && !password.isEmpty()) {
                manager.createCustomerAccount(new Customer(username, password));
                JOptionPane.showMessageDialog(frame, "Customer account created successfully.");
                usernameField.setText("");
                passwordField.setText("");
                cardLayout.show(mainPanel, "login");
            } else {
                JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.");
            }
        });
        
        backBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "login");
        });
        
        createAccountPanel.add(usernameLabel);
        createAccountPanel.add(usernameField);
        createAccountPanel.add(passwordLabel);
        createAccountPanel.add(passwordField);
        createAccountPanel.add(createBtn);
        createAccountPanel.add(backBtn);
    }
    
    private void updateCustomerPanel() {
        // Refresh any customer-specific data if needed
    }
    
    private void updateEmployeePanel() {
        // Refresh any employee-specific data if needed
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AccountManager manager = new AccountManager();
            new BankGUI(manager);
        });
    }
}