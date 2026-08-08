package day04;

public class Car extends Vehicle {
    private String model;
    private double price;

    Car(String brand, int year, String model, double price) {
        super(brand, year);
        this.model = model;
        this.price = price;
        
    }
    public void displayCarInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Model: " + model);
        System.out.println("Price: " + price);
    }
    public static void main(String[] args) {
        Car car = new Car("BMW", 2022, "M3", 2500000);
        car.start(); // Calling method from superclass
        car.displayCarInfo(); // Calling instance method to display car info
    }
    
}
