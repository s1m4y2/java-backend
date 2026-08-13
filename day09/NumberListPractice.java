package day09;
import java.util.*;

public class NumberListPractice {
    public static void main(String[] args){
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(25);
        numbers.add(7);
        numbers.add(42);
        numbers.add(18);
        System.out.println(numbers);

        System.out.println(numbers.get(3));
        numbers.set(2, 50);
        numbers.remove(Integer.valueOf(25));
        System.out.println(numbers.contains(18));
        System.out.println(numbers.size());
    }
    


}
