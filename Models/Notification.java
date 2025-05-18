package Models;

import java.util.UUID;

public class Notification {
    private final String notificationID;
    private String message;
    private String userID;

    public Notification(String message, String userID) {
        this.notificationID = UUID.randomUUID().toString();
        this.message = message;
        this.userID = userID;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
