package day03;

public class EqualsDemo {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        if(str1.equals(str2)) {
            System.out.println("The strings are equal.");
        } else {
            System.out.println("The strings are not equal.");
        }

        if(str1 == str2) {
            System.out.println("The strings are the same object.");
        } else {
            System.out.println("The strings are different objects.");
        }
    }
}
