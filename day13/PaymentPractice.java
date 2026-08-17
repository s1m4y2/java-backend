package day13;

public class PaymentPractice {
    public sealed interface Payment permits CreditCardPayment, BankTransferPayment {
        void pay();
    }
    public static final class CreditCardPayment implements Payment {
        public void pay(){
            System.out.println("Payment successful with credit card.");
        }
    }
    public static final class BankTransferPayment implements Payment {
        public void pay(){
            System.out.println("Payment successful with bank transfer.");
        }
    }
    public static void main(String[] args){
        Payment payment1 = new CreditCardPayment();
        Payment payment2 = new BankTransferPayment();
        payment1.pay();
        payment2.pay();
    }
}
