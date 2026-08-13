package day09;
import java.util.HashSet;

public class HashSetPractice {
    public static void main(String[] args){
        HashSet<String> students = new HashSet<>();

        students.add("Ali");
        students.add("Ayşe");
        students.add("Simay");
        students.add("Ali");
        students.add("Simay");
        students.add("Mehmet");
        System.out.println(students);

        System.out.println(students.contains("Simay"));
        System.out.println(students.contains("Zeynep"));

        students.remove("Ayşe");
        System.out.println(students);
    }
}
