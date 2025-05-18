package Models;
import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {
    private String customerId;

    public RecommendationEngine(String customerId) {
        this.customerId = customerId;
    }

    public List<Product> getRecommendations() {
        List<Product> recommended = new ArrayList<>();

        recommended.add(new Product("P100", "Wireless Mouse", 29.99, 20, "Accessories"));
        recommended.add(new Product("P101", "Bluetooth Headphones", 59.99, 15, "Electronics"));
        recommended.add(new Product("P102", "Portable SSD", 99.99, 10, "Storage"));

        return recommended;
    }
    public void showRecommendations() {
        System.out.println("Recommended products for customer " + customerId + ":");
        for (Product product : getRecommendations()) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")");
        }
    }
}
