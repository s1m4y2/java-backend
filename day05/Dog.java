package day05;

public class Dog extends AnimalSystem {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
    
}
