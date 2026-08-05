import java.util.*;

public class SalaryCalculator{
    public static void main(String[] args) {
        double hourlyRate;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your hourly rate: ");
        hourlyRate = s.nextDouble();

        double hoursWorked;
        System.out.print("Enter the number of hours worked: ");
        hoursWorked = s.nextDouble();
        
        double weeklySalary = hourlyRate * hoursWorked;
        double monthlySalary = weeklySalary * 4;
        double yearlySalary = monthlySalary * 12;
        System.out.println("Weekly Salary: " + weeklySalary);
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Yearly Salary: " + yearlySalary);
        s.close();

    }
}