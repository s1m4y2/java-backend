package day06;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " TL with credit card.");
    }
}
