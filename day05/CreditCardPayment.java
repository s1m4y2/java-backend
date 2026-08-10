package day05;

public class CreditCardPayment extends PaymentSystem {
    public CreditCardPayment(double amount) {
        super(amount);
    }
    @Override
    public void processPayment(){
        System.out.println("Processing credit card payment...");
    }
}
