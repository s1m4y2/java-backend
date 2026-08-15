package day11;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args){
        List<Integer> numbers = List.of(10, 15, 20, 25, 30, 35, 40);
        numbers.stream()
                .filter(number -> number % 2 == 0) //hangileri kalsın
                .forEach(System.out::println); //number -> System.out.println(number)

        numbers.stream()
                .map(number -> number*number) //bunları neye dönüştüreyim
                .forEach(System.out::println); 

        numbers.stream()
                .filter(number -> number % 2 == 0) 
                .map(number -> number*number) 
                .forEach(System.out::println); 

        List<Integer> result = numbers.stream()
                .filter(number -> number % 2 == 0) //Predicate
                .map(number -> number*number) //Function
                .collect(Collectors.toList()); //sonucu list olarak al 
        System.out.println(result);

        List<Integer> numbers1 = List.of(30, 10, 40, 20, 15, 35, 25);
        numbers1.stream()
                .sorted()
                .forEach(System.out::println);

        numbers1.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        List<Integer> duplicateNumbers = List.of(10, 20, 10, 30, 20, 40, 30, 50);
        duplicateNumbers.stream()
            .distinct() //Duplicate sayıları kaldır
            .forEach(System.out::println);

        List<Integer> numbers2 = List.of(10, 20, 30, 40, 50, 60, 70);
        numbers2.stream()
            .limit(3) //Listedeki ilk 3 sayıyı ekrana yazdır.
            .forEach(System.out::println);
        
        numbers2.stream()
            .skip(3) //İlk 3 elemanı atla
            .forEach(System.out::println);

        long evenCount = numbers.stream()
                .filter(number -> number % 2 == 0)
                .count();
        System.out.println(evenCount);

        Optional<Integer> firstEven = numbers.stream()
            .filter(number -> number % 2 == 0)
            .findFirst();
        System.out.println(firstEven);
        System.out.println(firstEven.get());

        boolean hasNumber = numbers.stream()
                .anyMatch(number -> number > 35);
        System.out.println(hasNumber);

        boolean allGreaterThanFive = numbers.stream()
                .allMatch(number -> number > 5);
        System.out.println(allGreaterThanFive);

        boolean hasNoNegative = numbers.stream()
                .noneMatch(number -> number < 0);
        System.out.println(hasNoNegative);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a+b);
        System.out.println(sum);

        List<Integer> numbers3 = List.of(1, 2, 3, 4, 5);
        int product = numbers3.stream()
                .reduce(1, (a, b) -> a*b);
        System.out.println(product);
    }
}
