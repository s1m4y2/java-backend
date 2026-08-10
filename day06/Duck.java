package day06;

public class Duck extends Animal implements Flyable, Swimmable {

    public Duck(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Quack!");
    }

    @Override
    public void fly() {
        System.out.println("Duck is flying.");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming.");
    }
}
