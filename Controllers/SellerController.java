package Controllers;

import Models.*;
import Views.SellerView;
import Services.AuthenticationService;
import Services.NotificationService;

import java.util.*;

public class SellerController {
    private final SellerView view;
    private final AuthenticationService authService;
    private final NotificationService notificationService;
    private final List<Seller> sellerList;
    private final List<Product> productList;
    private Seller loggedInSeller = null;

    public SellerController(Scanner scanner, AuthenticationService authService, NotificationService notificationService,
                            List<Seller> sellerList, List<Product> productList) {
        this.view = new SellerView(scanner);
        this.authService = authService;
        this.notificationService = notificationService;
        this.sellerList = sellerList;
        this.productList = productList;
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            view.showAccessMenu();
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1:
                    register();
                    handleSellerMenu();
                    break;
                case 2:
                    if (login()) {
                        handleSellerMenu();
                    }
                    break;
                case 0:
                    view.showMessage("Returning to Main Menu...");
                    exit = true;
                    break;
                default:
                    view.showMessage("Invalid option.");
            }
        }
    }

    private void register() {
        String[] input = view.getRegistrationInput();
        Seller newSeller = new Seller(input[0], input[1], input[2], input[3]);
        sellerList.add(newSeller);
        loggedInSeller = newSeller;
        notificationService.createNotification("New seller registered: " + newSeller.getName(), "admin");
    }

    private boolean login() {
        String[] credentials = view.getLoginInput();
        loggedInSeller = authService.loginSeller(credentials[0], credentials[1]);
        if (loggedInSeller != null) {
            view.showMessage("Login successful. Welcome, " + loggedInSeller.getName() + "!");
            notificationService.createNotification("Seller logged in: " + loggedInSeller.getName(), loggedInSeller.getSellerId());
            return true;
        } else {
            view.showMessage("Login failed. Invalid credentials.");
            return false;
        }
    }

    private void handleSellerMenu() {
        boolean active = true;

        while (active) {
            view.showSellerMenu();
            int option = view.getMenuChoice();

            switch (option) {
                case 1:
                    String[] details = view.getProductInput();
                    Product newProduct = new Product(details[0], details[1], Double.parseDouble(details[2]), Integer.parseInt(details[3]), details[4]);
                    loggedInSeller.addProduct(newProduct);
                    productList.add(newProduct);
                    notificationService.createNotification("Seller " + loggedInSeller.getName() + " added product: " + newProduct.getName(), loggedInSeller.getSellerId());
                    view.showMessage("Product added successfully.");
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    view.displayProducts(loggedInSeller.getProductList());
                    break;
                case 5:
                    notificationService.createNotification("Seller logged out: " + loggedInSeller.getName(), loggedInSeller.getSellerId());
                    view.showMessage("Logging out...");
                    loggedInSeller = null;
                    active = false;
                    break;
                default:
                    view.showMessage("Invalid option.");
            }
        }
    }

    private void updateProduct() {
        String productId = view.getProductId();

        Product product = null;
        for (Product p : loggedInSeller.getProductList()) {
            if (p.getProductId().equals(productId)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            view.showMessage("Product not found in your product list.");
            return;
        }

        try {
            double newPrice = view.getNewPrice();
            int newQuantity = view.getNewQuantity();

            loggedInSeller.updateProduct(productId, newPrice, newQuantity);
            notificationService.createNotification("Seller " + loggedInSeller.getName() + " updated product: " + product.getName(), loggedInSeller.getSellerId());
            view.showMessage("Product updated successfully.");
        } catch (NumberFormatException e) {
            view.showMessage("Invalid input for price or quantity. Please enter valid numbers.");
        }
    }

    private void removeProduct() {
        String productId = view.getProductId();

        boolean exists = false;
        for (Product p : loggedInSeller.getProductList()) {
            if (p.getProductId().equals(productId)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            view.showMessage("Product not found or unable to remove.");
            return;
        }

        loggedInSeller.removeProduct(productId);
        productList.removeIf(p -> p.getProductId().equals(productId));
        notificationService.createNotification("Seller " + loggedInSeller.getName() + " removed product with ID: " + productId, loggedInSeller.getSellerId());
        view.showMessage("Product removed successfully.");
    }
}
