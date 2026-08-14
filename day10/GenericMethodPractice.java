package day10;

public class GenericMethodPractice {
    public static <T> void printValue(T value){
       System.out.println(value);
    }
    public static void main(String[] args){
        printValue("Simay");
        printValue(23);
        printValue(3.14);
    }
}
