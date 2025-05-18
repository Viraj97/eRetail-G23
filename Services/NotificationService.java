package Services;

import Models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<Notification> notifications = new ArrayList<>();

    public void createNotification(String message, String userId) {
        Notification notification = new Notification(message, userId);
        notifications.add(notification);
    }

    public List<Notification> getUserNotifications(String userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getUserID().equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
}
