package day08;

import java.util.Scanner;

public class SifiraBolmeHatasi extends Exception{
    public SifiraBolmeHatasi(){
        super("bölen sıfır");
    }
    public SifiraBolmeHatasi(String ileti){
        super(ileti);
    }
}

class BolmeIslemi{
    public static void main (String[] args){
        String yanit;
        Scanner klavye = new Scanner(System.in);
        do{
            try{
                System.out.print("bölünecek sayı: \t");
                int a = klavye.nextInt();
                System.out.print("bölen sayı: \t");
                int b = klavye.nextInt();
                if(b==0)
                    throw new SifiraBolmeHatasi("bölen sıfır");
                int c = a/b;
                System.out.println(a+"/"+b+"="+c);
            }
            catch(SifiraBolmeHatasi ozelDurum){
                System.out.println(ozelDurum.getMessage());
                System.out.println("bölen sıfır olamaz");
            }
            finally {
                System.out.println("Calculation finished.");
            }
            System.out.print("Devam etmek istiyor musunuz? (e/h): ");
            yanit = klavye.next();
        }while (yanit.equalsIgnoreCase("e"));
        
    }
}