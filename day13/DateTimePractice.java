package day13;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateTimePractice {
    public static void main(String[] args){
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate deadline = LocalDate.of(2026,9,1);
        System.out.println(today.isBefore(deadline));

        System.out.println(deadline.plusDays(7));
        System.out.println(deadline.plusMonths(2));
        System.out.println(deadline.plusYears(1));

        LocalDateTime randevu = LocalDateTime.of(2026, 9, 5, 14, 30);
        System.out.println("Appoinment: " + randevu);
        System.out.println("After 90 minutes:" + randevu.plusMinutes(90));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.of(2026,8,17);
        String formatted = date.format(formatter);
        System.out.println(formatted);

        LocalDate start = LocalDate.of(2020,1,1);
        LocalDate end = LocalDate.of(2026,8,17);
        Period period = Period.between(start,end);
        System.out.println("Period:" + "Years:" + period.getYears() + "Months" + period.getMonths() + "Days:" + period.getDays());

        LocalTime startTime = LocalTime.of(9,0);
        LocalTime endTime = LocalTime.of(17,30);
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("working hours:" + duration.toHours() + "Working minutes:" + duration.toMinutes());


    }
}
