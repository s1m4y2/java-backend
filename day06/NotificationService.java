package day06;

public interface NotificationService {
    void send(String message);

    public static void main(String[] args){
        EmailNotification emailNotification = new EmailNotification();
        SmsNotification smsNotification = new SmsNotification();
        PushNotification pushNotification = new PushNotification();

        emailNotification.send("Email: Your order has been shipped.");
        smsNotification.send("SMS: Your verification code is 1234.");
        pushNotification.send("Push: You have a new friend request.");
    }
}
