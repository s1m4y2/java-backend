package day06;

public class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " TL with PayPal.");
    }
}
