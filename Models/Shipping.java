package Models;

public class Shipping {
    private String orderId;
    private String shippingAddress;
    private String shippingStatus;

    public Shipping(String orderId, String shippingAddress) {
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.shippingStatus = "Pending";
    }

    public void updateShippingStatus(String status) {
        this.shippingStatus = status;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void printShippingDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Shipping Status: " + shippingStatus);
    }
}
