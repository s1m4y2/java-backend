package day05;

public abstract class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }
    public void displayBasicInfo(){
        System.out.println("Name:"+ name + "salary:" + salary);
    }
    public abstract void work();
}
