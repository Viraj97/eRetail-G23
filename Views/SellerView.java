package Views;

import Models.Product;

import java.util.List;
import java.util.Scanner;

public class SellerView {
    private final Scanner scanner;

    public SellerView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showAccessMenu() {
        System.out.println("\n=== Seller Access Menu ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
    }

    public void showSellerMenu() {
        System.out.println("\n=== Seller Menu ===");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Remove Product");
        System.out.println("4. View My Products");
        System.out.println("5. Logout");
        System.out.print("Enter choice: ");
    }

    public int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String[] getRegistrationInput() {
        System.out.println("\n--- Seller Registration ---");
        System.out.print("Enter Seller ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        return new String[]{id, name, email, password};
    }

    public String[] getLoginInput() {
        System.out.println("\n--- Seller Login ---");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        return new String[]{email, password};
    }

    public String[] getProductInput() {
        System.out.println("\n--- Add Product ---");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        String price = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        String quantity = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        return new String[]{id, name, price, quantity, category};
    }

    public String getProductId() {
        System.out.print("Enter Product ID: ");
        return scanner.nextLine();
    }

    public double getNewPrice() {
        System.out.print("Enter New Price: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public int getNewQuantity() {
        System.out.print("Enter New Quantity: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayProducts(List<Product> products) {
        System.out.println("\n--- My Product Listings ---");
        if (products.isEmpty()) {
            System.out.println("No products added.");
        } else {
            for (Product p : products) {
                System.out.println("ID: " + p.getProductId() + " | Name: " + p.getName() + " | Price: $" + p.getPrice() + " | Qty: " + p.getQuantity() + " | Category: " + p.getCategory());
            }
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
