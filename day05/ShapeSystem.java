package day05;

public abstract class ShapeSystem {
    public abstract double calculateArea();
    public void displayArea() {
        System.out.println("The area is: " + calculateArea());
    }
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        circle.displayArea();

        Rectangle rectangle = new Rectangle(4, 6);
        rectangle.displayArea();
    }
}