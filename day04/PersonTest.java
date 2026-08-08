package day04;

public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("John Doe", 30, "john.doe@example.com", "password");
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Email: " + person.getEmail());
    }
}
