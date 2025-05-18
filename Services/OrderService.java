package Services;

import Models.*;

import java.util.Map;
import java.util.UUID;

public class OrderService {
    private Inventory inventory;

    public OrderService(Inventory inventory) {
        this.inventory = inventory;
    }
    public boolean placeOrder(Order order, String shippingAddress, String paymentMethod) {
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (!inventory.reduceStock(product, quantity)) {
                System.out.println("Not enough stock for product: " + product.getName());
                return false;
            }
        }

        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, order.getOrderId(), order.getTotalAmount(), paymentMethod);
        payment.updatePaymentStatus("Completed");

        Shipping shipping = new Shipping(order.getOrderId(), shippingAddress);
        shipping.updateShippingStatus("Shipped");

        order.updateStatus("Completed");

        order.printInvoice();
        payment.printPaymentDetails();
        shipping.printShippingDetails();

        return true;
    }

    public ReturnRequest createReturnRequest(Order order, String reason) {
        String returnId = UUID.randomUUID().toString();
        ReturnRequest returnRequest = new ReturnRequest(returnId, order.getOrderId(), reason);
        returnRequest.updateStatus("Approved");
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            inventory.restock(entry.getKey(), entry.getValue());
        }

        order.updateStatus("Returned");

        returnRequest.printReturnDetails();

        return returnRequest;
    }
}
