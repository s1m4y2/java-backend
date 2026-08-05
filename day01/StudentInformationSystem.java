import java.util.*;

public class StudentInformation{
    public static void main(String[] args) {
        int age;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your age: ");
        age = s.nextInt();
        String name;
        System.out.print("Enter your name: ");
        name = s.next();
        String surname;
        System.out.print("Enter your surname: ");
        surname = s.next();
        double height;
        System.out.print("Enter your height: ");
        height = s.nextDouble();
        double weight;
        System.out.print("Enter your weight: ");
        weight = s.nextDouble();
        double gpa;
        System.out.print("Enter your GPA: ");
        gpa = s.nextDouble();
        System.out.println("Student Card");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("GPA: " + gpa);
        s.close();
    }
}