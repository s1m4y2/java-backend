package day10;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LambdaPractice {
    interface Greeting {
        void sayHello(String name);
    }

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    public static void main(String[] args) {
        Greeting greeting = (name) -> {
            System.out.println("Hello " + name + "!");
        };
        greeting.sayHello("Simay");

        Calculator addition = (a, b) -> a + b;
        System.out.println(addition.calculate(12, 23));
        Calculator subtraction = (a, b) -> a - b;
        System.out.println(subtraction.calculate(12, 23));
        Calculator multiplication = (a, b) -> a * b;
        System.out.println(multiplication.calculate(12, 23));

        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println(isEven.test(10));

        Function<String, Integer> stringLength = text -> text.length();
        System.out.println(stringLength.apply("Simay"));

        Consumer<String> printer = text -> System.out.println(text);
        printer.accept("Java Backend");

        Supplier<String> message = () -> "Java Backend";
        System.out.println(message.get());
    }

}
