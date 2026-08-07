package day03;

public class Employee {
    private String name;
    private double salary;
    private String department;
    private static String companyName = "Google"; 

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    public static void main(String[] args){
        Employee employee1 = new Employee("John Doe", 50000, "Engineering");
        Employee employee2 = new Employee("Jane Smith", 60000, "Marketing");
        employee1.displayInfo();
        employee2.displayInfo();
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
        System.out.println("Department: " + department);
        System.out.println("Company: " + companyName);
    }


}
