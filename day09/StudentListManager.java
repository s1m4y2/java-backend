package day09;
import java.util.*;

public class StudentListManager {
    public static void main(String[] args){
        ArrayList<String> students = new ArrayList<>();
        students.add("Ali");
        students.add("Ayşe");
        students.add("Mehmet");
        students.add("Zeynep");
        students.add("Simay");

        System.out.println(students.get(3));

        students.set(1, "Elif");
        students.remove("Mehmet");
        System.out.println(students.contains("Simay"));
        System.out.println(students.size());
        System.out.println(students.isEmpty());

    }
}
