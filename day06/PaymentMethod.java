package day06;

public interface PaymentMethod {
    void pay(double amount);

    public static void main(String[] args){
        CreditCardPayment creditCard = new CreditCardPayment();
        PayPalPayment payPal = new PayPalPayment();
        BankTransferPayment bankTransfer = new BankTransferPayment();
        double amount = 100.0;
        creditCard.pay(amount);
        payPal.pay(amount);
        bankTransfer.pay(amount);

    }
}
