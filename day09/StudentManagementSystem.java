package day09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StudentManagementSystem {
    public static void main(String[] args){
        ArrayList<String> students = new ArrayList<>();
        HashSet<String> uniqueStudents = new HashSet<>();
        HashMap<String, Integer> grades = new HashMap<>();

        students.add("Ali");
        students.add("Ayşe");
        students.add("Simay");
        students.add("Mehmet");
        System.out.println(students);

        uniqueStudents.add("Ali");
        uniqueStudents.add("Ayşe");
        uniqueStudents.add("Simay");
        uniqueStudents.add("Mehmet");
        System.out.println(uniqueStudents);

        grades.put("Ali", 80);
        grades.put("Ayşe", 90);
        grades.put("Simay", 95);
        grades.put("Mehmet", 75);
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {

            System.out.println(entry.getKey() + " → " + entry.getValue());

        }
        System.out.println(grades.get("Simay"));
        grades.put("Simay", 100);
        System.out.println(grades);
    }
}
