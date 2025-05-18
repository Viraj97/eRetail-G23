import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controllers.AdminController;
import Services.AuthenticationService;
import Services.NotificationService;
import Controllers.CustomerController;
import Controllers.SellerController;
import Models.Admin;
import Models.Customer;
import Models.Product;
import Models.Seller;

public class MainApp {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    List<Customer> customers = new ArrayList<>();
    List<Seller> sellers = new ArrayList<>();
    List<Product> products = new ArrayList<>();

    Admin admin = new Admin("001", "admin", "admin");

    AuthenticationService authService = new AuthenticationService(admin, customers, sellers);

    NotificationService notificationService = new NotificationService();

    while (!exit) {
        System.out.println("\n===== E-Retail Management System =====");
        System.out.println("1. Customer Menu");
        System.out.println("2. Seller Menu");
        System.out.println("3. Admin Menu");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    CustomerController customerController = new CustomerController(scanner, authService, customers, products);
                    customerController.start();
                }
                case 2 -> {
                    SellerController sellerController = new SellerController(scanner, authService, notificationService, sellers, products);
                    sellerController.start();
                }
                case 3 -> {
                    AdminController adminController = new AdminController(scanner, authService, notificationService, customers, sellers, products);
                    adminController.start();
                }
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting application. Thank you!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    scanner.close();
}

}
