package day10;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Student implements Comparable<Student>{
    private String name;
    private int grade;
    private int age;

    public Student(String name, int grade, int age) {
        this.name = name;
        this.grade = grade;
        this.age = age;

        
    }
    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        Student simay = new Student("simay",95, 23);
        Student ali = new Student("Ali",80, 24);
        Student ayşe = new Student("Ayşe",90, 22);

        ArrayList<Student> students = new ArrayList<>();
        students.add(simay);
        students.add(ali);
        students.add(ayşe);

        Comparator<Student> byName = Comparator.comparing(Student::getName);

        Comparator<Student> byGradeDescending = Comparator.comparing(Student::getGrade).reversed();

        Comparator<Student> byGradeThenName = Comparator.comparing(Student::getGrade).thenComparing(Student::getName);

        Comparator<Student> byAge = Comparator.comparing(Student::getAge);

        Collections.sort(students);
        System.out.println(students);

        Collections.sort(students, byName);
        System.out.println(students);

        Collections.sort(students, byGradeDescending);
        System.out.println(students);

        Collections.sort(students, byGradeThenName);
        System.out.println(students);

        Collections.sort(students, byAge);
        System.out.println(students);
        

    }

    @Override
    public String toString() {
        return name + " - " + grade + " - " + age;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }
    
}

