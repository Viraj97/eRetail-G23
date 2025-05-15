import java.util.HashSet;
import java.util.Set;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String password;
    private String address;

    public Customer(String customerId, String name, String email, String password, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
    
    private static Set<String> registeredEmails = new HashSet<>(); 
    public void register() {
        if (name == null || email == null || password == null || address == null) {
            System.out.println("Registration failed: All fields are required.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Registration failed: Invalid email format.");
            return;
        }

        if (registeredEmails.contains(email)) {
            System.out.println("Registration failed: Email already registered.");
            return;
        }

        registeredEmails.add(email);
        System.out.println("Customer " + name + " registered successfully with ID " + customerId);
    }

    public boolean login(String enteredEmail, String enteredPassword) {
        if (this.email.equals(enteredEmail) && this.password.equals(enteredPassword)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }

    public void updateProfile(String newAddress) {
        this.address = newAddress;
        System.out.println("Address updated to: " + newAddress);
    }

    public void viewOrderHistory() {
        System.out.println("Order history for " + name + ":");
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
