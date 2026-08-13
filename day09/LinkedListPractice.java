package day09;
import java.util.LinkedList;

public class LinkedListPractice {
    public static void main(String[] args){
        LinkedList<String> students = new LinkedList<>();
        students.add("Ali");
        students.add("Ayşe");
        students.add("Simay");
        students.add("Mehmet");
        System.out.println(students);

        students.addFirst("Zeynep"); //listenin başına eleman ekleme
        students.addLast("Elif"); //listenin sonuna eleman ekleme
        System.out.println(students.getFirst()); //ilk elemanı yazdır
        System.out.println(students.getLast()); //son elemanı yazdır

        students.removeFirst(); //ilk elemanı sil
        System.out.println(students);
        students.removeLast(); //son elemanı sil
        System.out.println(students);

    }
}
