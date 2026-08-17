# Day 13 — Modern Java: Date & Time, Records, Sealed Classes & Stream Practice

Day 13 focused on modern Java features that are commonly used in backend development.

Today I practiced:

- Java Date & Time API
- `LocalDate`
- `LocalTime`
- `LocalDateTime`
- `DateTimeFormatter`
- `Period`
- `Duration`
- Records
- Record methods
- Compact constructors
- Sealed interfaces
- `final` implementations
- Stream API with records
- Method references
- `Optional`
- `Comparator`
- `ChronoUnit`

The goal was to understand how these modern Java features can work together in realistic backend scenarios.

---

## 📚 Topics Covered

### 📅 Java Date & Time API

Java provides the `java.time` package for working with dates and times.

Instead of using older date APIs, modern Java applications commonly use classes such as:

```java
LocalDate
LocalTime
LocalDateTime
```

---

### LocalDate

`LocalDate` represents a date without a time.

Example:

```java
LocalDate today = LocalDate.now();

LocalDate deadline = LocalDate.of(2026, 9, 1);
```

Date comparisons:

```java
today.isBefore(deadline);
today.isAfter(deadline);
today.isEqual(deadline);
```

Date manipulation:

```java
deadline.plusDays(7);
deadline.plusMonths(2);
deadline.plusYears(1);
```

Example:

```java
LocalDate today = LocalDate.now();

LocalDate deadline = LocalDate.of(2026, 9, 1);

System.out.println(today);
System.out.println(today.isBefore(deadline));

System.out.println(deadline.plusDays(7));
System.out.println(deadline.plusMonths(2));
System.out.println(deadline.plusYears(1));
```

---

### 🕐 LocalTime

`LocalTime` represents a time without a date.

Example:

```java
LocalTime startTime = LocalTime.of(9, 0);
LocalTime endTime = LocalTime.of(17, 30);
```

---

### 🕐 LocalDateTime

`LocalDateTime` represents both date and time.

Example:

```java
LocalDateTime appointment =
        LocalDateTime.of(2026, 9, 5, 14, 30);
```

Time manipulation:

```java
appointment.plusMinutes(90);
```

This is useful for backend scenarios such as:

- Appointments
- Orders
- Events
- Reservations
- Scheduled operations

---

### 🎨 DateTimeFormatter

`DateTimeFormatter` is used to format dates.

Example:

```java
DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy");

LocalDate date = LocalDate.of(2026, 8, 17);

String formatted = date.format(formatter);

System.out.println(formatted);
```

Output:

```text
17.08.2026
```

---

### 📆 Period

`Period` is used to calculate date-based differences in:

- Years
- Months
- Days

Example:

```java
LocalDate start = LocalDate.of(2020, 1, 1);
LocalDate end = LocalDate.of(2026, 8, 17);

Period period = Period.between(start, end);

System.out.println(
        "Years: " + period.getYears() +
        " Months: " + period.getMonths() +
        " Days: " + period.getDays()
);
```

`Period` is useful when the difference should be expressed using calendar-based units.

---

### ⏱ Duration

`Duration` is used to calculate time-based differences.

Example:

```java
LocalTime startTime = LocalTime.of(9, 0);
LocalTime endTime = LocalTime.of(17, 30);

Duration duration =
        Duration.between(startTime, endTime);

System.out.println(duration.toHours());
System.out.println(duration.toMinutes());
```

This is useful for calculating:

- Working hours
- Session duration
- Processing time
- Time intervals

---

### 📦 Records

Records provide a concise way to create classes whose main purpose is to hold data.

Example:

```java
public record Product(
        Long id,
        String name,
        double price
) {
}
```

Java automatically provides methods such as:

```text
product.id()
product.name()
product.price()
```

Instead of traditional getters:

```text
getId()
getName()
getPrice()
```

---

### 🧠 Record Methods

Records can also contain custom methods.

Example:

```java
public record Product(
        Long id,
        String name,
        double price
) {
    public boolean isExpensive() {
        return price >= 10000;
    }
}
```

Usage:

```java
Product product =
        new Product(1L, "Laptop", 45000.0);

System.out.println(product.isExpensive());
```

Records are useful for simple data-oriented objects such as:

- DTOs
- API responses
- Request models
- Value objects
- Immutable data structures

---

### 🛡 Compact Constructor in Records

Records can contain a compact constructor for validation.

Example:

```java
public record User(
        String username,
        int age
) {
    public User {
        if (age < 18) {
            throw new IllegalArgumentException(
                    "Age must be at least 18."
            );
        }
    }
}
```

The compact constructor allows validation without explicitly declaring constructor parameters again.

Example:

```java
User user = new User("Simay", 23);
```

An invalid value:

```java
User user = new User("Test", 15);
```

throws:

```text
IllegalArgumentException
```

---

### 🔒 Sealed Interfaces

A sealed interface restricts which classes can implement it.

Example:

```java
public sealed interface Payment
        permits CreditCardPayment, BankTransferPayment {

    void pay();
}
```

Only the permitted classes can implement the interface:

```java
public final class CreditCardPayment
        implements Payment {

    @Override
    public void pay() {
        System.out.println(
                "Payment successful with credit card."
        );
    }
}
```

```java
public final class BankTransferPayment
        implements Payment {

    @Override
    public void pay() {
        System.out.println(
                "Payment successful with bank transfer."
        );
    }
}
```

Usage:

```java
Payment payment1 = new CreditCardPayment();
Payment payment2 = new BankTransferPayment();

payment1.pay();
payment2.pay();
```

Sealed classes and interfaces are useful when the possible implementations should be explicitly controlled.

---

### 💼 JobApplication Practice

The main practice of Day 13 combined several modern Java concepts in a realistic backend-style example.

A job application was represented using a record:

```java
public record JobApplication(
        Long id,
        String company,
        LocalDate applicationDate,
        String status
) {
}
```

Example:

```java
JobApplication application1 =
        new JobApplication(
                1L,
                "Google",
                LocalDate.of(2026, 8, 10),
                "Applied"
        );
```

---

### 🔎 Checking Past Applications

A custom record method was created:

```java
public boolean gecmisBasvurular() {
    return applicationDate.isBefore(LocalDate.now());
}
```

This allows filtering applications with a method reference:

```java
applications.stream()
        .filter(JobApplication::gecmisBasvurular)
        .collect(Collectors.toList());
```

The same operation could also be written using a lambda:

```java
applications.stream()
        .filter(application ->
                application.gecmisBasvurular())
        .collect(Collectors.toList());
```

---

### 🔄 Stream + Filter + Map

Past application companies were extracted using:

```java
List<String> gecmisBasvurulanyerler =
        applications.stream()
                .filter(JobApplication::gecmisBasvurular)
                .map(JobApplication::company)
                .collect(Collectors.toList());
```

This demonstrates an important Stream pipeline:

```text
List<JobApplication>
        ↓
stream()
        ↓
filter()
        ↓
map()
        ↓
collect()
        ↓
List<String>
```

**filter()**

Decides which elements remain in the Stream.

```java
.filter(JobApplication::gecmisBasvurular)
```

**map()**

Transforms each element into another value.

```java
.map(JobApplication::company)
```

**collect()**

Collects the Stream result into a collection.

```java
.collect(Collectors.toList())
```

---

### 🎯 Finding the Nearest Application Date

I also practiced finding the application closest to today's date.

The difference between dates was calculated using:

```text
ChronoUnit.DAYS.between()
```

Because the application could be either before or after today, `Math.abs()` was used to calculate the absolute distance.

```java
Optional<JobApplication> nearestApplication =
        applications.stream()
                .min(
                        Comparator.comparingLong(application ->
                                Math.abs(
                                        ChronoUnit.DAYS.between(
                                                application.applicationDate(),
                                                LocalDate.now()
                                        )
                                )
                        )
                );
```

The Stream pipeline works like this:

```text
JobApplication
      ↓
applicationDate()
      ↓
DAYS.between()
      ↓
Math.abs()
      ↓
distance from today
      ↓
Comparator
      ↓
min()
      ↓
Optional<JobApplication>
```

Because `min()` may not find an element when the Stream is empty, the result is represented as:

```text
Optional<JobApplication>
```

---

### 🔗 Method References

Day 13 also reinforced method references.

Instead of:

```java
.map(application -> application.company())
```

I used:

```java
.map(JobApplication::company)
```

Instead of:

```java
.filter(application ->
        application.gecmisBasvurular())
```

I used:

```java
.filter(JobApplication::gecmisBasvurular)
```

Method references make Stream operations shorter and more readable when an existing method already matches the required functional interface.

---

## 🧩 Concepts Combined

Day 13 combined several concepts learned previously:

```text
Records
   ↓
JobApplication
   ↓
LocalDate
   ↓
Stream API
   ↓
filter()
   ↓
map()
   ↓
Comparator
   ↓
ChronoUnit
   ↓
Optional
   ↓
collect()
```

This was especially useful because these features frequently appear together in backend applications.

---

## 🛠 Files

```text
day13/
│
├── README.md
├── DateTimePractice.java
├── RecordPractice.java
├── PaymentPractice.java
└── JobApplicationPractice.java
```

---

## 📝 Practical Exercises

**Date & Time**

- Create and manipulate `LocalDate`
- Compare dates
- Add days, months and years
- Work with `LocalDateTime`
- Format dates with `DateTimeFormatter`
- Calculate date differences with `Period`
- Calculate time differences with `Duration`

**Records**

- Create a `Product` record
- Add a custom method
- Create a `User` record
- Validate data using a compact constructor

**Sealed Interfaces**

- Create a sealed `Payment` interface
- Restrict implementations
- Implement credit card payment
- Implement bank transfer payment
- Use interface references and polymorphism

**Stream Practice**

- Filter past job applications
- Extract company names with `map()`
- Use method references
- Find the application closest to today's date
- Combine Stream API with `Comparator`, `Optional`, and Date/Time API

---

## 🎯 Key Takeaways

The most important concepts from today:

```text
LocalDate
    ↓
Date without time


LocalTime
    ↓
Time without date


LocalDateTime
    ↓
Date + time


Period
    ↓
Date-based difference


Duration
    ↓
Time-based difference


Record
    ↓
Concise data carrier


Sealed Interface
    ↓
Controlled inheritance


filter()
    ↓
Select elements


map()
    ↓
Transform elements


Comparator
    ↓
Compare / sort objects


Optional
    ↓
Represent a possibly absent value


ChronoUnit
    ↓
Calculate date/time differences
```

---

## 🚀 Next Step

The next step is to continue strengthening Modern Java concepts and prepare for the transition into Spring Boot.

```text
Java Fundamentals
        ↓
OOP
        ↓
Collections
        ↓
Stream API
        ↓
Maven
        ↓
Date & Time
        ↓
Records
        ↓
Modern Java
        ↓
Spring Boot
        ↓
REST API
        ↓
Database
        ↓
JPA / Hibernate
        ↓
Spring Security
        ↓
Docker
        ↓
Production Backend
```

---

## 🚀 Day 13 Status

**Day 13 completed. ✅**

Today I practiced modern Java features including Date & Time API, Records, Sealed Interfaces, Stream API, Method References, Optional, Comparator, and ChronoUnit.

The `JobApplicationPractice` exercise helped combine these concepts into a small backend-oriented example.