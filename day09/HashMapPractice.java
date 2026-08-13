package day09;
import java.util.HashMap;
import java.util.Map;

public class HashMapPractice {
    public static void main(String[] args){
        HashMap<String, Integer> grades = new HashMap<>();
        grades.put("Simay",95);
        grades.put("Ali",80);
        grades.put("Ayşe",90);
        System.out.println(grades);

        System.out.println(grades.get("Simay"));
        System.out.println(grades.get("Ali"));

        System.out.println(grades.containsKey("Simay"));
        System.out.println(grades.containsKey("Zeynep"));

        System.out.println(grades.containsValue(95));
        System.out.println(grades.containsValue(100));

        grades.put("Ali", 100);
        System.out.println(grades);

        grades.remove("Ali");
        System.out.println(grades);

        for (String student : grades.keySet()) {
            System.out.println(student);
        }

        for (Integer grade : grades.values()) {
            System.out.println(grade);
        }

        for (Map.Entry<String, Integer> entry : grades.entrySet()) {

            System.out.println(entry.getKey() + " → " + entry.getValue());

        }
    }
}
