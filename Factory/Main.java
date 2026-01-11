package Factory;

interface Notification{
    public void sendNotification(String message);
}

class EmailNotification implements Notification{
    @Override
    public void sendNotification(String message){
        System.out.println("Email Notification: " + message);
    }
}

class PhoneNotification implements Notification{
    @Override
    public void sendNotification(String message){
        System.out.println("Phone Notification: " + message);
    }
}

class NotificationFactory{
   public static Notification getNotificationObject(String text){
        return switch (text) {
            case "email" -> new EmailNotification();
            case "phone" -> new PhoneNotification();
            default -> throw new AssertionError();
        };
   }
}

public class Main {
    public static void main(String[] args) {
        Notification email = NotificationFactory.getNotificationObject("email");
        email.sendNotification("Hello");
    }
}
