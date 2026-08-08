package day04;

public class Vehicle {
    protected String brand;
    protected int year;
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    public void start() {
        System.out.println("Vehicle is starting");
    }
}
