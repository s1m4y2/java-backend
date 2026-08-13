# 📅 Day 08 — Exception Handling

Today I focused on **Exception Handling in Java** and practiced how to handle errors, create custom exceptions, throw exceptions manually, and declare exceptions that a method may throw.

I also practiced handling multiple exception types and validating user input.

---

## 📚 Topics Covered

- Exception Handling
- `try`
- `catch`
- `finally`
- `throw`
- `throws`
- Custom Exceptions
- `extends Exception`
- Exception Constructors
- `getMessage()`
- Multiple `catch` Blocks
- `ArithmeticException`
- `InputMismatchException`
- Input Validation
- Method-based Exception Handling

---

## 🧠 What is Exception Handling?

Exception handling allows a Java program to handle unexpected situations without terminating unexpectedly.

The basic structure is:

```java
try {
    // Code that may cause an exception
}
catch (Exception e) {
    // Handle the exception
}
finally {
    // Code that runs regardless of the result
}
```

The general execution flow is:

```text
try
 ↓
Exception occurs
 ↓
catch
 ↓
finally
```

The `finally` block runs whether an exception occurs or not.

---

## 🔹 try / catch / finally

- A `try` block contains code that may cause an exception.
- A `catch` block handles the exception.
- A `finally` block contains code that should run regardless of whether an exception occurs.

Example:

```java
try {
    int result = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero.");
}
finally {
    System.out.println("Calculation finished.");
}
```

Output:

```text
Cannot divide by zero.
Calculation finished.
```

---

## 🔥 throw

The `throw` keyword is used to manually throw an exception.

For example:

```java
if (b == 0) {
    throw new SifiraBolmeHatasi(
        "Bölen sıfır olamaz."
    );
}
```

Here, a custom exception is created and manually thrown when the divisor is zero.

---

## 🔥 throws

The `throws` keyword is used in a method declaration to indicate that the method may throw a specific exception.

Example:

```java
public static void checkAge(int age)
        throws InvalidAgeException {

    if (age < 18) {
        throw new InvalidAgeException(
            "Age must be 18 or older."
        );
    }
}
```

### throw vs throws

```text
throw
 ↓
Actually throws an exception

throws
 ↓
Declares that a method may throw an exception
```

---

## 🛠 Exercise 1 — SifiraBolmeHatasi

Created a custom exception for division by zero.

**Structure**

```text
SifiraBolmeHatasi
        ↓
     Exception

BolmeIslemi
        ↓
 uses the custom exception
```

**SifiraBolmeHatasi**

```java
public class SifiraBolmeHatasi extends Exception {

    public SifiraBolmeHatasi() {
        super("bölen sıfır");
    }

    public SifiraBolmeHatasi(String ileti) {
        super(ileti);
    }
}
```

The exception is thrown when the divisor is zero:

```java
if (b == 0) {
    throw new SifiraBolmeHatasi(
        "Bölen sıfır olamaz."
    );
}
```

The exception is handled with:

```java
catch (SifiraBolmeHatasi e) {
    System.out.println(e.getMessage());
}
```

---

## 🧮 Exercise 2 — BolmeIslemi

Created a division program that handles division-by-zero errors using a custom exception.

The program asks the user for two numbers:

```text
Bölünecek sayı: 10
Bölen sayı: 2
10/2=5
```

If the user enters zero as the divisor:

```text
Bölünecek sayı: 10
Bölen sayı: 0
Bölen sıfır olamaz.
```

The program also uses `finally`:

```java
finally {
    System.out.println("Calculation finished.");
}
```

The program can continue asking the user for another calculation using `do-while`.

---

## 👤 Exercise 3 — InvalidAgeException

Created a custom exception for invalid age values.

The purpose is to prevent users under the age of 18 from being accepted.

**InvalidAgeException**

```java
public class InvalidAgeException extends Exception {

    public InvalidAgeException() {
        super("18 yaşından küçük");
    }

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

### 🔎 Age Validation

Created a `checkAge()` method that uses `throws`.

```java
public static void checkAge(int age)
        throws InvalidAgeException {

    if (age < 18) {
        throw new InvalidAgeException(
            "Age must be 18 or older."
        );
    }
}
```

The method is called inside a `try` block:

```java
try {

    checkAge(age);

    System.out.println("Yaşınız uygundur.");

}
catch (InvalidAgeException e) {

    System.out.println(e.getMessage());

}
```

**Execution Flow**

```text
main()
 ↓
checkAge(age)
 ↓
age < 18 ?
 ↓
throw
 ↓
InvalidAgeException
 ↓
catch
 ↓
getMessage()
```

---

## 🔢 Exercise 4 — InputMismatchException

Practiced handling Java's built-in `InputMismatchException`.

This exception occurs when the program expects a specific data type but the user enters an incompatible value.

For example:

```text
Yaşınızı giriniz: abc
```

When `nextInt()` is used, Java throws:

```text
InputMismatchException
```

The exception is handled with:

```java
catch (InputMismatchException e) {
    System.out.println("Lütfen sayı giriniz.");
}
```

This prevents the program from terminating unexpectedly because of invalid user input.

---

## ➖ Exercise 5 — NegativeNumberException

Created another custom exception to prevent negative numbers.

**NegativeNumberException**

```java
public class NegativeNumberException extends Exception {

    public NegativeNumberException() {
        super("negatif sayı girmeyiniz");
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}
```

### 🔍 Number Validation

Created a `checkNumber()` method that validates whether a number is negative.

```java
public static void checkNumber(double number)
        throws NegativeNumberException {

    if (number < 0) {
        throw new NegativeNumberException(
            "sayı negatif olamaz"
        );
    }
}
```

The exception is handled with:

```java
try {

    checkNumber(number);

    System.out.println("Sayı pozitif.");

}
catch (NegativeNumberException e) {

    System.out.println(e.getMessage());

}
```

**Execution Flow**

```text
Number entered
      ↓
number < 0 ?
   /       \
 YES       NO
  ↓         ↓
throw      continue
  ↓
catch
  ↓
error message
```

---

## 🔑 Custom Exceptions

One of the main concepts practiced today was creating custom exceptions.

The general structure is:

```java
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}
```

Examples created today:

```text
SifiraBolmeHatasi
        ↓
InvalidAgeException
        ↓
NegativeNumberException
```

All of these extend Java's `Exception` class.

---

## 🧩 Multiple Catch Blocks

A single `try` block can have multiple `catch` blocks.

Example:

```java
try {

    int age = klavye.nextInt();

    checkAge(age);

}
catch (InvalidAgeException e) {

    System.out.println(e.getMessage());

}
catch (InputMismatchException e) {

    System.out.println("Lütfen sayı giriniz.");

}
```

Different exception types can therefore be handled differently.

```text
try
 │
 ├── InvalidAgeException
 │       ↓
 │    catch #1
 │
 └── InputMismatchException
         ↓
      catch #2
```

---

## 🧠 Exception Flow

The overall exception handling process practiced today:

```text
                 Exception Handling
                         │
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
         try           throw         throws
          │              │              │
          ↓              ↓              ↓
       catch       Create Exception   Declare
          │
          ↓
       finally
```

---

## 🔥 Custom Exception Flow

```text
Custom Exception Class
        ↓
extends Exception
        ↓
Create Exception Object
        ↓
throw new CustomException(...)
        ↓
catch (CustomException e)
        ↓
getMessage()
```

---

## 📂 Files

```text
day08
│
├── README.md
│
├── SifiraBolmeHatasi.java
├── BolmeIslemi.java
│
├── InvalidAgeException.java
├── ValidateAge.java
│
├── NegativeNumberException.java
└── ValidateNumber.java
```

---

## 🧠 Key Concepts Learned

Today I learned that:

- `try` contains code that may cause an exception.
- `catch` handles an exception.
- `finally` runs whether an exception occurs or not.
- `throw` manually throws an exception.
- `throws` declares that a method may throw an exception.
- Custom exceptions can be created by extending `Exception`.
- A custom exception can have multiple constructors.
- `getMessage()` retrieves the exception message.
- Multiple `catch` blocks can handle different exception types.
- Java provides built-in exceptions such as `ArithmeticException` and `InputMismatchException`.
- Exception handling can be separated into dedicated validation methods.

---

## 💻 Backend Connection

Exception handling is an important part of backend development.

In backend applications, exceptions can occur because of:

```text
Invalid Input
     ↓
Validation Error
     ↓
Database Error
     ↓
Authentication Error
     ↓
Business Logic Error
```

Learning how to handle these situations properly is important for building reliable backend applications.

---

## 📅 Day 08 Summary

The main concepts practiced today:

```text
try
 ↓
catch
 ↓
finally

Custom Exception
 ↓
extends Exception
 ↓
throw
 ↓
throws
 ↓
catch
```

---

## 🚀 Day 08 Status

**Completed ✅**

Exception handling fundamentals are now part of my Java foundation.

**Next Step:** Day 09 — Collections Framework