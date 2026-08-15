package day11;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentStreamPractice {
    public static class Student {

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

        @Override
        public String toString() {
            return name + " - " + grade + " - " + age;
        }
    }

    public static void main(String[] args){
        Student simay = new Student("Simay", 95, 23);
        Student ali = new Student("Ali", 80, 24);
        Student ayse = new Student("Ayşe", 90, 22);
        Student mehmet = new Student("Mehmet", 65, 25);
        Student zeynep = new Student("Zeynep", 75, 23);

        List<Student> students = List.of(simay, ali, ayse, mehmet, zeynep);
        System.out.println(students);

        students.stream()
                .filter(student -> student.getGrade() > 80) //“Her Student objesini al, grade'ine bak, 80'den büyükse Stream'de tut.”
                .forEach(System.out::println);
        students.stream()
                .map(student -> student.getName())
                .forEach(System.out::println);
        List<String> successfulStudents = students.stream()
                .filter(student -> student.getGrade() >= 80)
                .map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(successfulStudents);
        students.stream()
                .sorted(Comparator.comparingInt(Student::getGrade).reversed())
                .forEach(System.out::println);

        Student can = new Student("Can", 80, 21);
        List<Student> students1 = List.of(simay,ali,ayse,mehmet,zeynep,can);
        Comparator<Student> byGradeThenAge = Comparator.comparingInt(Student::getGrade)
                                                        .reversed()
                                                        .thenComparingInt(Student::getAge);
        students1.stream()
            .sorted(byGradeThenAge)
            .forEach(System.out::println);

        Optional<Student> topStudent = students.stream()
                .max(Comparator.comparingInt(Student::getGrade));
        System.out.println(topStudent);
        Optional<Student> lowestStudent = students.stream()
                .min(Comparator.comparingInt(Student::getGrade));
        System.out.println(lowestStudent);

        double averageGrade = students.stream()
                .mapToInt(Student::getGrade)
                .average()
                .orElse(0);
        System.out.println(averageGrade);

        int totalGrade = students.stream()
                .mapToInt(Student::getGrade)
                .sum();
        System.out.println(totalGrade);

        IntSummaryStatistics statistics = students.stream()
                .mapToInt(Student::getGrade)
                .summaryStatistics();
        System.out.println(statistics);

        Map<Integer, List<Student>> studentsByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        System.out.println(studentsByAge);

        Map<Integer, Long> studentCountByAge = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        Collectors.counting()
                ));
        System.out.println(studentCountByAge);

        Map<Boolean, List<Student>> partitionedStudents = students.stream()
                .collect(Collectors.partitioningBy(
                        student -> student.getGrade() >= 80
                ));
        System.out.println(partitionedStudents);

        List<String> result = students.stream()
                .filter(student -> student.getGrade() >= 80)
                .sorted(Comparator.comparingInt(Student::getGrade).reversed())
                .map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(result);
    }
        
}
