package Models; 
import java.util.*;

public class Order {
    private final String orderId;
    private final String customerId;
    private final Map<Product, Integer> items;
    private final double totalAmount;
    private String status;

    public Order(String orderId, String customerId, Map<Product, Integer> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new HashMap<>(items);
        this.totalAmount = totalAmount;
        this.status = "Processing";
    }

    public void trackStatus() {
        System.out.println("Order " + orderId + " status: " + status);
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void printInvoice() {
        System.out.println("Invoice for Order: " + orderId);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.printf("%s x%d - $%.2f\n",
                entry.getKey().getName(),
                entry.getValue(),
                entry.getKey().getPrice() * entry.getValue());
        }
        System.out.printf("Total Amount: $%.2f\n", totalAmount);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }
}
