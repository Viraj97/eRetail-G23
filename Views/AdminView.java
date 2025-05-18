package Views;

import java.util.Scanner;

public class AdminView {
    private final Scanner scanner;

    public AdminView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showAdminMenu() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. View All Customers");
        System.out.println("2. View All Sellers");
        System.out.println("3. View All Products");
        System.out.println("4. Remove a Seller");
        System.out.println("5. Remove a Customer");
        System.out.println("6. Logout");
        System.out.print("Select an option: ");
    }

    public String[] getLoginInput() {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    public String getIdInput(String type) {
        System.out.print("Enter " + type + " ID to remove: ");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
