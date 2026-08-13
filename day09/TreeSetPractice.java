package day09;

import java.util.TreeSet;

public class TreeSetPractice {
    public static void main(String[] args){
        TreeSet<String> students = new TreeSet<>();
        students.add("Simay");
        students.add("Mehmet");
        students.add("Ali");
        students.add("Zeynep");
        students.add("Ayşe");
        students.add("Ali");
        System.out.println(students);

        System.out.println(students.contains("Mehmet"));
        System.out.println(students.remove("Ali"));
        System.out.println(students);
    }
}
