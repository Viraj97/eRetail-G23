package Models;

import java.util.*;

public class Cart {
    private final String cartId;
    private final String customerId;
    private final Map<Product, Integer> items = new HashMap<>(); // Product and quantity

    public Cart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public void addItem(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeItem(Product product) {
        if (items.containsKey(product)) {
            int qty = items.get(product);
            if (qty > 1) {
                items.put(product, qty - 1);
            } else {
                items.remove(product);
            }
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println("Product not in cart.");
        }
    }

    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            items.remove(product);
        } else {
            items.put(product, quantity);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void viewCart() {
        System.out.println("Your Cart Contents:");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            System.out.printf("%s - Quantity: %d - Price: $%.2f\n",
                entry.getKey().getName(),
                entry.getValue(),
                entry.getKey().getPrice() * entry.getValue());
        }
        System.out.printf("Total: $%.2f\n", calculateTotal());
    }
}
