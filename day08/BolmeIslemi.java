package day08;
import java.util.Scanner;

public class BolmeIslemi {
    public static void main(String[] args) {
        Scanner klavye = new Scanner(System.in);
        String yanit;
        
        do{
            System.out.print("Bölünecek sayı: ");
            int a = klavye.nextInt();

            System.out.print("Bölen sayı: ");
            int b = klavye.nextInt();
            try {
                if (b == 0) {
                    throw new SifiraBolmeHatasi("Bölen sıfır olamaz.");
                }
                int c = a / b;
                System.out.println(a + "/" + b + "=" + c);
            }
            catch (SifiraBolmeHatasi e) {

                System.out.println(e.getMessage());

            }
            finally {
                System.out.println("Calculation finished.");
            }
            System.out.print("Devam etmek istiyor musunuz? (e/h): ");
            yanit = klavye.next();
        }while (yanit.equalsIgnoreCase("e"));
        
    }
}
