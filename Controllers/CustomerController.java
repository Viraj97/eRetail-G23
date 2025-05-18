package Controllers;

import Models.*;
import Views.CustomerView;
import Services.AuthenticationService;

import java.util.*;

public class CustomerController {
    private final CustomerView view;
    private final AuthenticationService authService;
    private final List<Customer> customerList;
    private final List<Product> productList;
    private Customer loggedInCustomer = null;
    private Cart cart = null;

    public CustomerController(Scanner scanner, AuthenticationService authService, List<Customer> customerList, List<Product> productList) {
        this.view = new CustomerView(scanner);
        this.authService = authService;
        this.customerList = customerList;
        this.productList = productList;
    }

    public void start() {
        boolean exitApp = false;

        while (!exitApp) {
            view.showAccessMenu();
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1:
                    register();
                    handleCustomerMenu();
                    break;
                case 2:
                    if (login()) {
                        handleCustomerMenu();
                    }
                    break;
                case 0:
                    view.showMessage("Returning to Main Menu...");
                    exitApp = true;
                    break;
                default:
                    view.showMessage("Invalid option.");
            }
        }
    }

    private void register() {
        String[] input = view.getRegistrationInput();
        Customer newCustomer = new Customer(input[0], input[1], input[2], input[3], input[4]);
        newCustomer.register();
        customerList.add(newCustomer);
        loggedInCustomer = newCustomer;
        cart = new Cart("CART_" + input[0], input[0]);
    }

    private boolean login() {
        String[] credentials = view.getLoginInput();
        loggedInCustomer = authService.loginCustomer(credentials[0], credentials[1]);
        if (loggedInCustomer != null) {
            cart = new Cart("CART_" + loggedInCustomer.getCustomerId(), loggedInCustomer.getCustomerId());
            view.showMessage("Login successful. Welcome, " + loggedInCustomer.getName() + "!");
            return true;
        } else {
            view.showMessage("Login failed. Invalid credentials.");
            return false;
        }
    }

    private void handleCustomerMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            view.showCustomerMenu();
            int option = view.getMenuChoice();

            switch (option) {
                case 1:
                    String newAddress = view.getNewAddress();
                    loggedInCustomer.updateProfile(newAddress);
                    break;
                case 2:
                    loggedInCustomer.viewOrderHistory();
                    break;
                case 3:
                    displayProducts();
                    break;
                case 4:
                    int addChoice = view.getProductChoice("add");
                    addToCart(addChoice);
                    break;
                case 5:
                    int removeChoice = view.getProductChoice("remove");
                    removeFromCart(removeChoice);
                    break;
                case 6:
                    cart.viewCart();
                    break;
                case 7:
                    double total = cart.calculateTotal();
                    view.showMessage("Your total is: $" + total);
                    break;
                case 8:
                    String[] reviewData = view.getReviewInput();
                    UUID reviewId = UUID.randomUUID();
                    String customerId = loggedInCustomer.getCustomerId();
                    String productId = reviewData[0];
                    int rating = Integer.parseInt(reviewData[1]);
                    String comment = reviewData[2];
                    Review review = new Review(reviewId, customerId, productId, rating, comment);
                    review.writeReview();
                    break;
                case 9:
                    RecommendationEngine engine = new RecommendationEngine(loggedInCustomer.getCustomerId());
                    engine.showRecommendations();
                    break;
                case 10:
                    loggedInCustomer = null;
                    cart = null;
                    loggedIn = false;
                    view.showMessage("Logged out successfully.");
                    break;
                default:
                    view.showMessage("Invalid option.");
            }
        }
    }

    private void displayProducts() {
        view.showMessage("Available Products:");
        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            view.showMessage((i + 1) + ". " + p.getName() + " - $" + p.getPrice());
        }
    }

    private void addToCart(int choice) {
        if (choice >= 1 && choice <= productList.size()) {
            cart.addItem(productList.get(choice - 1));
        } else {
            view.showMessage("Invalid product number.");
        }
    }

    private void removeFromCart(int choice) {
        if (choice >= 1 && choice <= productList.size()) {
            cart.removeItem(productList.get(choice - 1));
        } else {
            view.showMessage("Invalid product number.");
        }
    }
}
