package day02;
import java.util.*;

public class StudentGradeManager {
    int numStudents;
    
    public static void main(String[] args) {
        int sum = 0;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of students: "); 
        int numStudents = s.nextInt();
        int [] grades = new int[numStudents];
        System.out.println("Enter the grades of the students: ");
        for(int i = 0; i < numStudents; i++) {
            grades[i] = s.nextInt();
            sum += grades[i];
        }
        double average = (double) sum / numStudents;
        System.out.println("Average grade: " + average);
        
        int highest = grades[0];
        for(int i = 1; i < numStudents; i++) {
            if(grades[i] > highest) {
                highest = grades[i];
            }
        }
        System.out.println("Highest grade: " + highest);

        int lowest = grades[0];
        for(int i = 1; i < numStudents; i++) {
            if(grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        System.out.println("Lowest grade: " + lowest);

        if(average < 60) {
            System.out.println("Class performance: Poor");
        } else if(average < 70) {
            System.out.println("Class performance: Average");
        } else if(average < 80) {
            System.out.println("Class performance: Good");
        } else {
            System.out.println("Class performance: Excellent");
        }
    }

}