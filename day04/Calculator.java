package day04;

public class Calculator{
    double a,b,c;
    public int calculate(int a, int b) {
        return (int)a+(int)b;
    }
    public int calculate(double a, double b){
        return a+b;
    }
    public int calculate(int a, int b, int c) {
        return (int)(a + b + c);
    }
    public int calculate(double a, double b, double c) {
        return a + b + c;
    }
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.calculate(1, 2));
        System.out.println(cal.calculate(1.5, 2.5));
        System.out.println(cal.calculate(1, 2, 3));
        System.out.println(cal.calculate(1.5, 2.5, 3.5));
    }

}