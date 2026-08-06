package day02;

public class Car { // Yeni veri tiplerini oluşturuyoruz

    private String brand;
    private String model;
    private int year;
    private String color;
    private double price;

    // Constructor
    public Car(String brand, String model, int year, String color, double price) { //contructorın adı classın adıyla aynı olmalı
        this.brand = brand; //bu arabanın brand değişkeni, constructorın brand değişkenine eşitleniyor
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public void displayInfo(){ //arabanın bilgilerini ekrana yazdıran method
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Price: $" + price);
    }

    // main
    public static void main(String[] args) { //programın başlangıcı 

        Car car = new Car("BMW", "M3", 2022, "Black", 2500000); //bellekte yeni araba oluşturuyoruz ve constructor ile değerlerini veriyoruz
        car.displayInfo();

    }

}