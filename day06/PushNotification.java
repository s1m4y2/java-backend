package day06;

public class PushNotification implements NotificationService{
    @Override 
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
}
