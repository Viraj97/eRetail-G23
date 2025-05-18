package Controllers;

import Models.Customer;
import Models.Product;
import Models.Seller;
import Models.Notification;
import Services.AuthenticationService;
import Services.NotificationService;
import Views.AdminView;

import java.util.*;

public class AdminController {
    private final AdminView view;
    private final AuthenticationService authService;
    private final NotificationService notificationService;
    private final List<Customer> customers;
    private final List<Seller> sellers;
    private final List<Product> products;

    public AdminController(Scanner scanner, AuthenticationService authService,
                           NotificationService notificationService,
                           List<Customer> customers, List<Seller> sellers, List<Product> products) {
        this.view = new AdminView(scanner);
        this.authService = authService;
        this.notificationService = notificationService;
        this.customers = customers;
        this.sellers = sellers;
        this.products = products;
    }

    public void start() {
        boolean loggedIn = false;

        while (!loggedIn) {
            String[] input = view.getLoginInput();
            if (authService.loginAdmin(input[0], input[1])) {
                view.showMessage("Login successful.");
                notificationService.createNotification("Admin logged in", "admin");
                loggedIn = true;
                handleAdminMenu();
            } else {
                view.showMessage("Invalid credentials. Try again.");
            }
        }
    }

    private void handleAdminMenu() {
        boolean exit = false;
        while (!exit) {
            view.showAdminMenu();
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> viewAllCustomers();
                case 2 -> viewAllSellers();
                case 3 -> viewAllProducts();
                case 4 -> removeSeller();
                case 5 -> removeCustomer();
                case 6 -> {
                    view.showMessage("Logged out.");
                    notificationService.createNotification("Admin logged out", "admin");
                    exit = true;
                }
                default -> view.showMessage("Invalid option.");
            }
        }
    }

    private void viewAllCustomers() {
        view.showMessage("\nRegistered Customers:");
        for (Customer c : customers) {
            view.showMessage("ID: " + c.getCustomerId() + ", Name: " + c.getName() + ", Email: " + c.getEmail());
        }
    }

    private void viewAllSellers() {
        view.showMessage("\nRegistered Sellers:");
        for (Seller s : sellers) {
            view.showMessage("ID: " + s.getSellerId() + ", Name: " + s.getName() + ", Email: " + s.getEmail());
        }
    }

    private void viewAllProducts() {
        view.showMessage("\nAll Products:");
        for (Product p : products) {
            view.showMessage("ID: " + p.getProductId() + ", Name: " + p.getName() + ", Price: $" + p.getPrice());
        }
    }

    private void removeSeller() {
        String id = view.getIdInput("Seller");
        sellers.removeIf(s -> s.getSellerId().equals(id));
        notificationService.createNotification("Seller with ID " + id + " removed by Admin", "admin");
        view.showMessage("Seller removed if ID matched.");
    }

    private void removeCustomer() {
        String id = view.getIdInput("Customer");
        customers.removeIf(c -> c.getCustomerId().equals(id));
        notificationService.createNotification("Customer with ID " + id + " removed by Admin", "admin");
        view.showMessage("Customer removed if ID matched.");
    }
}
