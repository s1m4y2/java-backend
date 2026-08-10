package day06;

public class BankTransferPayment implements PaymentMethod {
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " TL via bank transfer.");
    }
}
