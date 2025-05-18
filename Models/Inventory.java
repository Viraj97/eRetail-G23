package Models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Product, Integer> stock;

    public Inventory() {
        stock = new HashMap<>();
    }

    public void addStock(Product product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public boolean reduceStock(Product product, int quantity) {
        int currentStock = stock.getOrDefault(product, 0);
        if (currentStock >= quantity) {
            stock.put(product, currentStock - quantity);
            return true;
        }
        return false;
    }

    public void restock(Product product, int quantity) {
        addStock(product, quantity);
    }

    public int getStock(Product product) {
        return stock.getOrDefault(product, 0);
    }
}
