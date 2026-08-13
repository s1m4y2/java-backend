package day08;
import java.util.*;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(){
        super("negatif sayı girmeyiniz");
    }
    public NegativeNumberException(String message){
        super(message);
    }
}

class ValidateNumber{
    public static void checkNumber(double number) throws NegativeNumberException {
        
        if (number < 0) {
            throw new NegativeNumberException("sayı negatif olamaz");
        }
    }
    public static void main(String[] args){
        double number;
        Scanner klavye = new Scanner(System.in);
        do {
            
            System.out.print("sayı giriniz: ");
            number = klavye.nextDouble();

            try {
                checkNumber(number);
                System.out.println("sayı pozitif");
                break;
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (number < 0);
    }
}