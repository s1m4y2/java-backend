# ☕ Day 10 — Generics, Comparable, Comparator & Lambda

Day 10 focuses on some of the most important features of Modern Java.

Today I worked with:

- Generics
- Generic Classes
- Generic Methods
- Comparable
- Comparator
- Lambda Expressions
- Functional Interfaces
- Predicate
- Function
- Consumer
- Supplier
- Method References
- Introduction to Stream API

The main goal was to understand how these concepts connect to each other and how they are used in real Java backend development.

---

## 🧠 Topics Covered

### 1. Generics

Generics allow classes and methods to work with different types while maintaining type safety.

#### Generic Class

```java
public class GenericBox<T> {

    private T value;

    public GenericBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

Usage:

```java
GenericBox<String> hello =
        new GenericBox<>("Hello Java");


GenericBox<Integer> number =
        new GenericBox<>(100);
```

The same class can work with different types:

```text
GenericBox<String>
GenericBox<Integer>
GenericBox<Double>
```

#### Generic Methods

A method can also define its own generic type.

```java
public static <T> void printValue(T value) {
    System.out.println(value);
}
```

Usage:

```java
printValue("Simay");
printValue(23);
printValue(3.14);
```

The type is inferred automatically.

---

### 2. Comparable

`Comparable` is used when a class has a natural/default ordering.

Example:

```java
public class Student implements Comparable<Student> {
```

The class implements:

```java
@Override
public int compareTo(Student other) {
    return Integer.compare(this.grade, other.grade);
}
```

This means:

**Students are naturally ordered by grade.**

Then:

```java
Collections.sort(students);
```

uses the `compareTo()` method.

#### Comparison Result

`compareTo()` follows this general rule:

```text
negative → this comes before other
zero     → equal ordering
positive → this comes after other
```

---

### 3. Comparator

`Comparator` allows us to define alternative sorting strategies without changing the natural ordering of the class.

Example:

```java
Comparator<Student> byName =
        (s1, s2) -> s1.getName().compareTo(s2.getName());
```

Then:

```java
Collections.sort(students, byName);
```

sorts students by name.

#### Comparator.comparing()

Modern Java provides a cleaner way to create Comparators.

```java
Comparator<Student> byName =
        Comparator.comparing(Student::getName);
```

This means:

**Sort Students according to their name.**

#### reversed()

A Comparator can be reversed.

```java
Comparator<Student> byGradeDescending =
        Comparator.comparing(Student::getGrade)
                  .reversed();
```

This sorts grades from highest to lowest.

```text
95
90
80
```

#### thenComparing()

Multiple sorting criteria can be combined.

```java
Comparator<Student> byGradeThenName =
        Comparator.comparing(Student::getGrade)
                  .thenComparing(Student::getName);
```

The logic is:

```text
1. Compare grade
       ↓
2. If grades are equal
       ↓
3. Compare name
```

This is useful when multiple records have the same value for the primary sorting criterion.

---

### 4. Method Reference

Method references provide a shorter syntax for certain lambda expressions.

For example:

```java
student -> student.getName()
```

can be written as:

```java
Student::getName
```

Similarly:

```java
Comparator.comparing(Student::getGrade)
```

can be used instead of:

```java
Comparator.comparing(student -> student.getGrade())
```

---

### 5. Lambda Expressions

Lambda expressions provide a concise way to implement functional interfaces.

Basic structure:

```java
(parameters) -> expression
```

or:

```java
(parameters) -> {
    // statements
}
```

#### Lambda Without Parameters

```java
Greeting greeting = () -> {
    System.out.println("Hello!");
};
```

#### Lambda With One Parameter

```java
Greeting greeting = (name) -> {
    System.out.println("Hello " + name);
};
```

Usage:

```java
greeting.sayHello("Simay");
```

#### Lambda With Multiple Parameters

```java
Calculator addition =
        (a, b) -> a + b;
```

Other examples:

```java
Calculator subtraction =
        (a, b) -> a - b;


Calculator multiplication =
        (a, b) -> a * b;
```

---

### 6. Functional Interface

A functional interface has exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

}
```

Because `Calculator` has one abstract method, it can be implemented using a lambda:

```java
Calculator addition =
        (a, b) -> a + b;
```

The `@FunctionalInterface` annotation tells Java that the interface is intended to have one abstract method.

---

### 7. Java Functional Interfaces

Four important functional interfaces were introduced.

| Interface       | Input | Output  | Method   |
|------------------|-------|---------|----------|
| Predicate\<T\>    | T     | boolean | test()   |
| Function\<T,R\>   | T     | R       | apply()  |
| Consumer\<T\>     | T     | void    | accept() |
| Supplier\<T\>     | none  | T       | get()    |

#### Predicate

`Predicate` answers a yes/no question.

```java
Predicate<Integer> isEven =
        number -> number % 2 == 0;
```

Usage:

```java
isEven.test(10);
```

Result:

```text
true
```

Concept:

```text
T → boolean
```

#### Function

`Function` transforms one value into another value.

```java
Function<String, Integer> stringLength =
        text -> text.length();
```

Usage:

```java
stringLength.apply("Simay");
```

Result:

```text
5
```

Concept:

```text
T → R
```

#### Consumer

`Consumer` receives a value and performs an operation without returning a value.

```java
Consumer<String> printer =
        text -> System.out.println(text);
```

Usage:

```java
printer.accept("Java Backend");
```

Concept:

```text
T → void
```

#### Supplier

`Supplier` does not receive a value but produces one.

```java
Supplier<String> message =
        () -> "Java Backend";
```

Usage:

```java
message.get();
```

Concept:

```text
nothing → T
```

---

### 8. Introduction to Stream API

Stream API was only introduced briefly on Day 10.

A simple Stream pipeline was created using:

```java
numbers.stream()
       .filter(...)
       .map(...)
       .forEach(...);
```

The concepts introduced were:

```text
stream()
filter()
map()
forEach()
collect()
sorted()
```

Example:

```java
numbers.stream()
       .filter(number -> number % 2 == 0)
       .map(number -> number * 2)
       .forEach(number -> System.out.println(number));
```

The basic flow:

```text
List
 ↓
stream()
 ↓
filter()
 ↓
map()
 ↓
forEach()
```

#### Important Functional Interface Connection

```text
filter()   → Predicate
map()      → Function
forEach()  → Consumer
```

`collect()` was also briefly introduced:

```java
List<Integer> result =
        numbers.stream()
               .filter(number -> number % 2 == 0)
               .map(number -> number * 2)
               .collect(Collectors.toList());
```

Result:

```text
[20, 40, 60, 80]
```

Stream API will be studied in detail on Day 11.

---

## 🔥 Important Connections

One of the main goals of Day 10 was understanding how the concepts connect:

```text
Generics
    ↓
Collections
    ↓
Comparable / Comparator
    ↓
Lambda Expressions
    ↓
Functional Interfaces
    ↓
Stream API
```

Another important connection:

```text
Predicate
    ↓
filter()


Function
    ↓
map()


Consumer
    ↓
forEach()
```

And:

```text
Comparable
    ↓
compareTo()
    ↓
natural ordering
```

while:

```text
Comparator
    ↓
custom sorting
    ↓
comparing()
    ↓
reversed()
    ↓
thenComparing()
```

---

## 📂 Day 10 Files

```text
day10
│
├── README.md
├── GenericBox.java
├── GenericMethodPractice.java
├── Student.java
├── LambdaPractice.java
└── StreamPractice.java
```

---

## 🛠 Concepts Practiced

```text
Generics
   ↓
Generic Class
   ↓
Generic Method
   ↓
Comparable
   ↓
compareTo()
   ↓
Comparator
   ↓
Comparator.comparing()
   ↓
reversed()
   ↓
thenComparing()
   ↓
Method Reference
   ↓
Lambda Expressions
   ↓
Functional Interface
   ↓
Predicate
   ↓
Function
   ↓
Consumer
   ↓
Supplier
   ↓
Stream API Introduction
```

---

## 📅 Day 10 Status

| Topic                   | Status |
|---------------------------|--------|
| Generic Class              | ✅     |
| Generic Method             | ✅     |
| Comparable                 | ✅     |
| compareTo()                | ✅     |
| Comparator                 | ✅     |
| Comparator.comparing()     | ✅     |
| reversed()                 | ✅     |
| thenComparing()            | ✅     |
| Method Reference           | ✅     |
| Lambda Expressions         | ✅     |
| Functional Interface       | ✅     |
| Predicate                  | ✅     |
| Function                   | ✅     |
| Consumer                   | ✅     |
| Supplier                   | ✅     |
| Stream API Introduction    | ✅     |
| Stream API Deep Dive       | 🔜 Day 11 |

---

## 🎯 Day 10 Summary

Day 10 focused on understanding modern Java features that are heavily used in backend development.

The main takeaway:

Comparable defines a class's natural ordering, Comparator provides custom sorting strategies, Lambda Expressions provide concise behavior implementations, Functional Interfaces define the structure for those lambdas, and these concepts come together heavily in the Stream API.

---

## 🚀 Next — Day 11

Day 11 will focus entirely on Stream API.

Topics planned:

- `stream()`
- Intermediate vs Terminal Operations
- `filter()`
- `map()`
- `forEach()`
- `collect()`
- `sorted()`
- `distinct()`
- `limit()`
- `count()`
- `findFirst()`
- `anyMatch()`
- `allMatch()`
- `noneMatch()`
- `reduce()`
- `groupingBy()`
- `partitioningBy()`
- Stream + Objects
- Stream + Student
- Real backend-style examples

The goal is not just to memorize Stream methods, but to understand how to build and read Stream pipelines.

---

## 🚀 Journey Status

Day 10 completed.

Generics, Comparable, Comparator, Lambda Expressions and Functional Interfaces are now part of my Java foundation.

Stream API was introduced briefly and will be studied in depth on Day 11.