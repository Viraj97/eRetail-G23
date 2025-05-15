import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private static List<Customer> customerList = new ArrayList<>();
    private static Customer loggedInCustomer = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nE-Retail Customer Menu");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Address");
            System.out.println("4. View Order History");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nRegistration");
                    System.out.print("Customer ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    Customer newCustomer = new Customer(id, name, email, password, address);
                    newCustomer.register();
                    customerList.add(newCustomer);
                    break;

                case 2:
                    System.out.println("\nLogin");
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();

                    loggedInCustomer = authenticate(loginEmail, loginPassword);

                    if (loggedInCustomer != null) {
                        System.out.println("Login successful. Welcome, " + loggedInCustomer.getName() + "!");
                    } else {
                        System.out.println("Login failed. Invalid email or password.");
                    }
                    break;

                case 3:
                    if (loggedInCustomer == null) {
                        System.out.println("Please log in first.");
                        break;
                    }
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    loggedInCustomer.updateProfile(newAddress);
                    break;

                case 4:
                    if (loggedInCustomer == null) {
                        System.out.println("Please log in first.");
                        break;
                    }
                    loggedInCustomer.viewOrderHistory();
                    break;

                case 0:
                    System.out.println("Thank you. Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static Customer authenticate(String email, String password) {
        for (Customer c : customerList) {
            if (c.login(email, password)) {
                return c;
            }
        }
        return null;
    }
}

