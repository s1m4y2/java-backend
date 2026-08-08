package day04;

public class EmployeeHierarchy {
    private String name;
    private double salary;
    public EmployeeHierarchy(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    void displayEmployeeInfo() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}
class Developer extends EmployeeHierarchy {
    private String programmingLanguage;
    public Developer(String name, double salary, String programmingLanguage) {
        super(name, salary);
        this.programmingLanguage = programmingLanguage;
    }
    @Override
    void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Programming Language: " + programmingLanguage);
    }
}
class Manager extends EmployeeHierarchy {
    private int teamSize;
    public Manager(String name, double salary, int teamSize) {
        super(name, salary);
        this.teamSize = teamSize;
    }
    @Override
    void displayEmployeeInfo() {
        super.displayEmployeeInfo();
        System.out.println("Team Size: " + teamSize);
    }
}
class EmployeeTest {
    public static void main(String[] args) {
        Developer developer = new Developer("Alice", 80000, "Java");
        Manager manager = new Manager("Bob", 100000, 5);
        System.out.println("Developer Info:");
        developer.displayEmployeeInfo();
        System.out.println("\nManager Info:");
        manager.displayEmployeeInfo();
    }
}

