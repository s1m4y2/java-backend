package day13;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

public class JobApplicationPractice {
    public record JobApplication(
        Long id,
        String company,
        LocalDate applicationDate,
        String status
    ){
        public boolean gecmisBasvurular(){
            return applicationDate.isBefore(LocalDate.now());
        }
    }
    
    public static void main(String[] args) {
        JobApplication application1 = new JobApplication(
                1L,
                "Google",
                LocalDate.of(2026, 8, 10),
                "Applied"
        );

        JobApplication application2 = new JobApplication(
                2L,
                "Microsoft",
                LocalDate.of(2026, 8, 20),
                "Applied"
        );

        JobApplication application3 = new JobApplication(
                3L,
                "Amazon",
                LocalDate.of(2026, 8, 5),
                "Applied"
        );

        List<JobApplication> applications = List.of(
                application1,
                application2,
                application3
        );
        List<JobApplication> gecmisBasvurular = applications.stream()
            .filter(JobApplication::gecmisBasvurular)
            .collect(Collectors.toList());
        System.out.println(gecmisBasvurular);

        List<String> gecmisBasvurulanyerler = applications.stream()
            .filter(JobApplication::gecmisBasvurular)
            .map(JobApplication::company)
            .collect(Collectors.toList());
        System.out.println(gecmisBasvurulanyerler);

        Optional<JobApplication> nearestApplication = applications.stream()
            .min(Comparator.comparingLong(application ->
                    Math.abs(
                            ChronoUnit.DAYS.between(
                                    application.applicationDate(),
                                    LocalDate.now()
                            )
                    )
            ));
        System.out.println(nearestApplication);

    }
}
