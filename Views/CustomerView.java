package Views;

import java.util.Scanner;

public class CustomerView {
    private final Scanner scanner;

    public CustomerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showAccessMenu() {
        System.out.println("\n===== Customer Access Menu =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    public void showCustomerMenu() {
        System.out.println("\n===== Customer Menu =====");
        System.out.println("1. Update Address");
        System.out.println("2. View Order History");
        System.out.println("3. Browse Products");
        System.out.println("4. Add Product to Cart");
        System.out.println("5. Remove Product from Cart");
        System.out.println("6. View Cart");
        System.out.println("7. Checkout");
        System.out.println("8. Write Product Review");
        System.out.println("9. View Recommendations");
        System.out.println("10. Logout");
        System.out.print("Choose an option: ");
    }

    public String[] getRegistrationInput() {
        String[] input = new String[5];
        System.out.println("\n--- Registration ---");
        System.out.print("Customer ID: ");
        input[0] = scanner.nextLine();
        System.out.print("Name: ");
        input[1] = scanner.nextLine();
        System.out.print("Email: ");
        input[2] = scanner.nextLine();
        System.out.print("Password: ");
        input[3] = scanner.nextLine();
        System.out.print("Address: ");
        input[4] = scanner.nextLine();
        return input;
    }

    public String[] getLoginInput() {
        String[] credentials = new String[2];
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        credentials[0] = scanner.nextLine();
        System.out.print("Password: ");
        credentials[1] = scanner.nextLine();
        return credentials;
    }

    public String getNewAddress() {
        System.out.print("Enter new address: ");
        return scanner.nextLine();
    }

    public int getProductChoice(String action) {
        System.out.print("Enter product number to " + action + " (1-3): ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    public String[] getReviewInput() {
        String[] input = new String[3];
        System.out.print("Enter product ID to review: ");
        input[0] = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        input[1] = scanner.nextLine();
        System.out.print("Enter comment: ");
        input[2] = scanner.nextLine();
        return input;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int getMenuChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter a number.");
        }
        return choice;
    }
}
