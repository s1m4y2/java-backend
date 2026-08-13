package day09;
import java.util.*;

public class LinkedHashSetPractice {
    public static void main(String[] args){
        LinkedHashSet<String> students = new LinkedHashSet<>();

        students.add("Mehmet");
        students.add("Ali");
        students.add("Simay");
        students.add("Ayşe");
        students.add("Ali");
        students.add("Mehmet");
        System.out.println(students);

        
    }
}
