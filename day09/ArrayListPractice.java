package day09;
import java.util.*;

public class ArrayListPractice {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        students.add("Ali"); //yeni eleman ekler
        students.add("Ayşe");
        students.add("Simay");
        students.add("Mehmet");
        System.out.println(students);

        System.out.println(students.get(2)); //elemanı getirir

        students.set(1,"Zeynep"); //mevcut elemanı değiştirir

        students.remove(2); //elemanı siler
        System.out.println(students);

        System.out.println(students.contains("Ali")); //eleman listede mevcut mu kontrol eder
        System.out.println(students.contains("Simay"));

        System.out.println(students.size()); //eleman sayısını verir

        System.out.println(students.isEmpty()); //liste boş mu kontrol eder

        students.clear(); //listeyi tamamen temizler
        System.out.println(students);
        System.out.println(students.isEmpty());
        System.out.println(students.size());

    }
}
