package Models;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    private String sellerId;
    private String name;
    private String email;
    private String password;
    private List<Product> productList;

    public Seller(String sellerId, String name, String email, String password) {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.productList = new ArrayList<>();
    }

    public boolean login(String enteredEmail, String enteredPassword) {
        return this.email.equals(enteredEmail) && this.password.equals(enteredPassword);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(String productId) {
        productList.removeIf(p -> p.getProductId().equals(productId));
    }

    public void updateProduct(String productId, double newPrice, int newQuantity) {
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                p.setPrice(newPrice);
                p.setQuantity(newQuantity);
            }
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
