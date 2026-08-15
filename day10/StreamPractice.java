package day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        numbers.add(25);
        numbers.add(30);
        numbers.add(35);
        numbers.add(40);

        numbers.stream()
        .filter(number -> number % 2 == 0)
        .forEach(number -> System.out.println(number));

        numbers.stream()
       .map(number -> number * 2)
       .forEach(number -> System.out.println(number));

       numbers.stream()
       .filter(number -> number % 2 == 0)
       .map(number -> number * 2)
       .forEach(number -> System.out.println(number));

       numbers.stream()
       .sorted()
       .forEach(number -> System.out.println(number));

       numbers.stream()
       .sorted(Comparator.reverseOrder())
       .forEach(number -> System.out.println(number));

       List<Integer> result = numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(number -> number * 2)
        .collect(Collectors.toList());
        System.out.println(result);

        List<Integer> duplicateNumbers = List.of(
                10, 20, 10, 30, 20, 40, 30
        );
        duplicateNumbers.stream()
        .distinct()
        .forEach(number -> System.out.println(number));
    }
}
