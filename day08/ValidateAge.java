package day08;

import java.util.*;

public class ValidateAge {
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }
    }
    public static void main(String[] args){
        Scanner klavye = new Scanner(System.in);

        try {

            System.out.print("Yaşınızı giriniz: ");
            int age = klavye.nextInt();

            checkAge(age);

            System.out.println("Yaşınız uygundur.");

        }
        catch (InvalidAgeException e) {

            System.out.println(e.getMessage());

        }
        catch (InputMismatchException e) {

            System.out.println("Lütfen sayı giriniz.");

        }
    }
}
