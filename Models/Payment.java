package Models;

public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentMethod; // e.g. Credit Card, PayPal, etc.
    private String paymentStatus; // e.g. Pending, Completed, Failed

    public Payment(String paymentId, String orderId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
    }

    public void updatePaymentStatus(String status) {
        this.paymentStatus = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void printPaymentDetails() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Amount: $" + amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: " + paymentStatus);
    }
}
