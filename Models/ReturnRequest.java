package Models;

import java.util.Date;

public class ReturnRequest {
    private String returnId;
    private String orderId;
    private String reason;
    private String status; // e.g. Requested, Approved, Rejected, Completed
    private Date requestDate;

    public ReturnRequest(String returnId, String orderId, String reason) {
        this.returnId = returnId;
        this.orderId = orderId;
        this.reason = reason;
        this.status = "Requested";
        this.requestDate = new Date();
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void printReturnDetails() {
        System.out.println("Return ID: " + returnId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Reason: " + reason);
        System.out.println("Status: " + status);
        System.out.println("Request Date: " + requestDate);
    }
}
