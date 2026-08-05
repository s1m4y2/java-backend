import java.util.*;

public class TemperatureConverter {
    public static void main(String[] args) {
        double celsius;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter temperature in Celsius: ");
        celsius = s.nextDouble();
        double fahrenheit = (celsius * 9/5) + 32;
        System.out.println("Temperature in Fahrenheit: " + fahrenheit);
        double kelvin = celsius + 273.15;
        System.out.println("Temperature in Kelvin: " + kelvin);
        s.close();
    }
}