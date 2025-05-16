import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String cartId;
    private String customerId;
    private List<Product> items;

    public Cart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeItem(Product product) {
        if (items.remove(product)) {
            System.out.println(product.getName() + " removed from cart.");
        } else {
            System.out.println(product.getName() + " not found in cart.");
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Cart contents:");
        for (Product product : items) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : items) {
            total += product.getPrice();
        }
        return total;
    }

    public String getCartId() {
        return cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Product> getItems() {
        return items;
    }
}
