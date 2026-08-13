package day08;
import java.util.*;

public class ValidateNumber {
    public static void checkNumber(double number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException("sayı pozitif olmalı");
        }
    }
    public static void main(String[] args){
        Scanner klavye = new Scanner(System.in);

        try {

            System.out.print("sayı giriniz: ");
            double number = klavye.nextDouble();

            checkNumber(number);

            System.out.println("Yaşınız uygundur.");

        }
        catch (NegativeNumberException e) {

            System.out.println(e.getMessage());

        }
        catch (InputMismatchException e) {

            System.out.println("Lütfen sayı giriniz.");

        }
    }
}
