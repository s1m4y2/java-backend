package day08;

import java.util.Scanner;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(){
        super("18 yaşından küçük");
    }
    public InvalidAgeException(String message){
        super(message);
    }
}


class ValidateAge{
    public static void checkAge(int age) throws InvalidAgeException {
        
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }
    }
    public static void main(String[] args){
        int age;
        do {
            Scanner klavye = new Scanner(System.in);
            System.out.print("Yaşınızı giriniz: ");
            age = klavye.nextInt();

            try {
                checkAge(age);
                System.out.println("Yaşınız uygundur.");
                break;
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
            }
        } while (age < 18);
    }
}
