package day05;

public class Cat extends AnimalSystem {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
    
}
