package day05;

public abstract class AnimalSystem {
    protected String name;
    public AnimalSystem(String name) {
        this.name = name;
    }
    public void eat(){
        System.out.println(name + " is eating");
    }
    public abstract void makeSound();

    public static void main(String[] args) {
        Dog dog = new Dog("Max");
        dog.eat();
        dog.makeSound();

        Cat cat = new Cat("Whiskers");
        cat.eat();
        cat.makeSound();
    }
}
