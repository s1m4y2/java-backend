# Day 11 — Stream API & Advanced Stream Operations

On Day 11, I studied the Java Stream API in detail.

In this session, I specifically used `Stream`, `Lambda`, `Method Reference`, `Comparator`, `Optional`, and `Collectors` together to filter, transform, sort, group, and analyze data on objects.

---

## 📚 Topics

- Stream API
- Stream Pipeline
- `filter()`
- `map()`
- `mapToInt()`
- `forEach()`
- `sorted()`
- `distinct()`
- `limit()`
- `skip()`
- `collect()`
- `Collectors.toList()`
- `count()`
- `findFirst()`
- `anyMatch()`
- `allMatch()`
- `noneMatch()`
- `max()`
- `min()`
- `sum()`
- `average()`
- `summaryStatistics()`
- `groupingBy()`
- `counting()`
- `partitioningBy()`
- `Optional`
- `Comparator`
- Method Reference (`::`)

---

## 1. What is Stream API?

Stream API allows us to process data inside collections in a more readable and functional way.

For example:

```java
List<Integer> numbers = List.of(10, 15, 20, 25, 30, 35, 40);

numbers.stream()
        .filter(number -> number % 2 == 0)
        .forEach(System.out::println);
```

Here, a Stream pipeline is built like this:

```text
List
 ↓
stream()
 ↓
filter()
 ↓
forEach()
```

Stream operations create a data processing flow instead of directly modifying the existing list.

---

## 2. Stream Pipeline

A Stream pipeline can generally be thought of as:

```text
Source
  ↓
Intermediate Operations
  ↓
Terminal Operation
```

For example:

```java
numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(number -> number * number)
        .forEach(System.out::println);
```

Here it proceeds as:

```text
numbers
   ↓
stream()
   ↓
filter()
   ↓
map()
   ↓
forEach()
```

---

## 3. filter()

`filter()` is used to select elements that satisfy a given condition.

```java
numbers.stream()
        .filter(number -> number % 2 == 0)
        .forEach(System.out::println);
```

Here, only even numbers remain.

```text
10
20
30
40
```

`filter()` works with a `Predicate` logic.

```text
Element → boolean
```

For example:

```java
number -> number % 2 == 0
```

---

## 4. map()

`map()` is used to transform elements into another value.

```java
numbers.stream()
        .map(number -> number * number)
        .forEach(System.out::println);
```

Here, a transformation of:

```text
Integer → Integer
```

is performed.

It can also be used on `Student` objects:

```java
students.stream()
        .map(student -> student.getName())
        .forEach(System.out::println);
```

Here, a transformation of:

```text
Student → String
```

is performed.

With Method Reference:

```java
students.stream()
        .map(Student::getName)
        .forEach(System.out::println);
```

can also be written.

---

## 5. filter() + map()

Multiple Stream operations can be chained.

```java
numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(number -> number * number)
        .forEach(System.out::println);
```

Process:

```text
Numbers
 ↓
Select even numbers
 ↓
Get their squares
 ↓
Print to console
```

---

## 6. collect()

`collect()` can be used to get the Stream result as a collection.

```java
List<Integer> result = numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(number -> number * number)
        .collect(Collectors.toList());
```

Result:

```text
[100, 400, 900, 1600]
```

---

## 7. sorted()

Used to sort Stream elements.

**Ascending**

```java
numbers.stream()
        .sorted()
        .forEach(System.out::println);
```

**Descending**

```java
numbers.stream()
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
```

---

## 8. distinct()

Removes duplicate elements.

```java
List<Integer> numbers = List.of(
        10, 20, 10, 30, 20, 40, 30, 50
);

numbers.stream()
        .distinct()
        .forEach(System.out::println);
```

Result:

```text
10
20
30
40
50
```

---

## 9. limit()

Used to take a specific number of elements from the beginning.

```java
numbers.stream()
        .limit(3)
        .forEach(System.out::println);
```

Takes the first 3 elements.

---

## 10. skip()

Used to skip a specific number of elements from the beginning.

```java
numbers.stream()
        .skip(3)
        .forEach(System.out::println);
```

Skips the first 3 elements and processes the rest.

---

## 11. count()

Finds the number of elements in the Stream.

```java
long evenCount = numbers.stream()
        .filter(number -> number % 2 == 0)
        .count();
```

For example:

```text
evenCount = 4
```

---

## 12. findFirst()

Finds the first matching element in the Stream.

```java
Optional<Integer> firstEven = numbers.stream()
        .filter(number -> number % 2 == 0)
        .findFirst();
```

Result:

```text
Optional[10]
```

`findFirst()` returns an `Optional`.

---

## 13. Optional

`Optional` is used to safely represent situations where a value might not be present.

For example:

```java
Optional<Integer> firstEven = numbers.stream()
        .filter(number -> number % 2 == 0)
        .findFirst();
```

If a value exists, it can be retrieved with:

```java
firstEven.get()
```

However, it's safer to check whether the value is present before using `get()`.

---

## 14. anyMatch()

Checks whether at least one element satisfies the condition.

```java
boolean hasNumber = numbers.stream()
        .anyMatch(number -> number > 35);
```

If any number is greater than 35, it returns:

```text
true
```

---

## 15. allMatch()

Checks whether all elements satisfy the condition.

```java
boolean allGreaterThanFive = numbers.stream()
        .allMatch(number -> number > 5);
```

If all numbers are greater than 5, it returns:

```text
true
```

---

## 16. noneMatch()

Checks that no element satisfies the condition.

```java
boolean hasNoNegative = numbers.stream()
        .noneMatch(number -> number < 0);
```

If there are no negative numbers, it returns:

```text
true
```

---

## 17. max() and min()

Used to find the maximum or minimum element in a Stream.

Student example:

```java
Optional<Student> topStudent = students.stream()
        .max(Comparator.comparingInt(Student::getGrade));
```

Finds the student with the highest grade.

Minimum:

```java
Optional<Student> lowestStudent = students.stream()
        .min(Comparator.comparingInt(Student::getGrade));
```

Finds the student with the lowest grade.

The results are of type `Optional<Student>`.

---

## 18. mapToInt()

`mapToInt()` can be used to extract a primitive `int` value from an object and use numeric Stream operations.

For example:

```java
students.stream()
        .mapToInt(Student::getGrade)
```

does the following:

```text
Student
   ↓
getGrade()
   ↓
int
   ↓
IntStream
```

After this, operations like:

```text
.sum()
.average()
.min()
.max()
```

can be performed.

---

## 19. sum()

Finds the sum of all integer values.

```java
int totalGrade = students.stream()
        .mapToInt(Student::getGrade)
        .sum();
```

For example:

```text
95 + 80 + 90 + 65 + 75 = 405
```

Result:

```text
405
```

---

## 20. average()

Used to calculate the average.

```java
double averageGrade = students.stream()
        .mapToInt(Student::getGrade)
        .average()
        .orElse(0);
```

Result:

```text
81.0
```

Since `average()` returns an `OptionalDouble`:

```java
.orElse(0)
```

can be used.

---

## 21. summaryStatistics()

Used to get basic statistics of an `IntStream` all at once.

```java
IntSummaryStatistics statistics = students.stream()
        .mapToInt(Student::getGrade)
        .summaryStatistics();
```

The result contains:

```text
count
sum
min
average
max
```

For example:

```text
count=5
sum=405
min=65
average=81.0
max=95
```

Individual values can also be retrieved:

```java
statistics.getCount();
statistics.getSum();
statistics.getMin();
statistics.getAverage();
statistics.getMax();
```

---

## 22. Comparator + sorted()

We can sort `Student` objects based on a specific criterion.

By grade, descending:

```java
students.stream()
        .sorted(
                Comparator.comparingInt(Student::getGrade)
                        .reversed()
        )
        .forEach(System.out::println);
```

---

## 23. thenComparing()

We can create multiple sorting criteria.

For example:

First by grade descending, and if grades are equal, by age ascending.

```java
Comparator<Student> byGradeThenAge =
        Comparator.comparingInt(Student::getGrade)
                .reversed()
                .thenComparingInt(Student::getAge);
```

Then:

```java
students.stream()
        .sorted(byGradeThenAge)
        .forEach(System.out::println);
```

---

## 24. groupingBy()

Used to group elements based on a specific criterion.

We can group students by age:

```java
Map<Integer, List<Student>> studentsByAge =
        students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
```

For example:

```text
22 → [Ayşe]
23 → [Simay, Zeynep]
24 → [Ali]
25 → [Mehmet]
```

Here:

```text
Key   → age
Value → students of that age
```

---

## 25. groupingBy() + counting()

We can find the number of elements in each group.

```java
Map<Integer, Long> studentCountByAge =
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        Collectors.counting()
                ));
```

Result:

```text
22 → 1
23 → 2
24 → 1
25 → 1
```

Here:

```text
Map<Integer, Long>
```

is used because `Collectors.counting()` returns a `Long`.

---

## 26. partitioningBy()

Used to split elements into two groups based on a condition.

For example:

```java
Map<Boolean, List<Student>> partitionedStudents =
        students.stream()
                .collect(Collectors.partitioningBy(
                        student -> student.getGrade() >= 80
                ));
```

Result:

```text
true  → successful students
false → unsuccessful students
```

For example:

```text
true  → [Simay, Ali, Ayşe]
false → [Mehmet, Zeynep]
```

---

## 27. groupingBy() vs partitioningBy()

**groupingBy()**

Can create multiple categories.

```text
22 → ...
23 → ...
24 → ...
25 → ...
```

**partitioningBy()**

Splits into two groups based on a boolean condition.

```text
true  → ...
false → ...
```

---

## 28. Combining Multiple Stream Operations

A real-world example:

Get the names of students with a grade of 80 or above, sorted by grade in descending order.

```java
List<String> result = students.stream()
        .filter(student -> student.getGrade() >= 80)
        .sorted(Comparator.comparingInt(Student::getGrade).reversed())
        .map(Student::getName)
        .collect(Collectors.toList());
```

Result:

```text
[Simay, Ayşe, Ali]
```

The pipeline here:

```text
List<Student>
     ↓
filter()
     ↓
grade >= 80
     ↓
sorted()
     ↓
grade DESC
     ↓
map()
     ↓
Student → String
     ↓
collect()
     ↓
List<String>
```

This type of chain is frequently encountered in real Java backend applications.

---

## 29. Method Reference

Method references can be used in cases where lambda expressions can be written more concisely.

For example:

```java
student -> student.getName()
```

can be replaced with:

```java
Student::getName
```

Similarly:

```java
number -> System.out.println(number)
```

can be replaced with:

```java
System.out::println
```

---

## 30. The Main Logic I Learned on Day 11

Instead of memorizing the Stream API, thinking through these questions:

```text
1. Which elements do I want?
        ↓
     filter()


2. What do I want to transform the elements into?
        ↓
      map()


3. Am I going to perform an operation on a numeric value?
        ↓
    mapToInt()


4. How am I going to sort them?
        ↓
     sorted()


5. Where am I going to store the result?
        ↓
     collect()


6. Do I want to group them?
        ↓
   groupingBy()


7. Do I want to split them into two?
        ↓
 partitioningBy()


8. Largest / smallest?
        ↓
    max() / min()


9. How many?
        ↓
      count()


10. Average / total?
        ↓
 average() / sum()
```

---

## 📁 Day 11 Files

```text
day11
│
├── StreamPractice.java
├── StudentStreamPractice.java
└── README.md
```

---

## 🎯 Day 11 Summary

On Day 11, I practiced functional data processing on collections and objects using the Java Stream API.

In particular, I practiced:

- Building Stream pipelines
- Using lambda expressions
- Using method references
- Filtering
- Transforming data
- Sorting
- Numeric analysis
- Using `Optional`
- Grouping
- Counting
- Partitioning
- Chaining multiple Stream operations

The most important pattern I learned:

```text
Source
  ↓
filter()
  ↓
map()
  ↓
sorted()
  ↓
collect()
```

and when needed:

```text
mapToInt()
  ↓
sum()
average()
min()
max()
summaryStatistics()
```

---

### A small note

I specifically included **everything we did** in this README, but I did not add the `filter() + max()` task that wasn't completed at the end. This way, the README genuinely reflects **the topics actually completed on Day 11**. ❤️

`StudentStreamPractice.java` has now become one of the most valuable files of Day 11, because the `filter → map → sorted → collect` chain is practiced there on real `Student` objects.