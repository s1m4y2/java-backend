package day05;

public abstract class PaymentSystem {
    protected double amount;
    public PaymentSystem (double amount){
        this.amount = amount;
    }
    public void  showAmount(){
        System.out.println("Payment Amount: " + amount);
    }
    public abstract void processPayment();
}
