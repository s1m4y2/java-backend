# 📅 Day 5 — Abstract Classes & Abstract Methods

Today I focused on **abstract classes and abstract methods** in Java and practiced how they work together with inheritance, constructors, `super()`, and method overriding.

The goal was to understand how abstract classes can provide common structure and behavior while forcing child classes to implement specific behaviors.

---

## 📚 Topics Covered

- Abstract Classes
- Abstract Methods
- Inheritance
- `extends`
- `super()`
- Constructors in Abstract Classes
- Method Overriding
- `@Override`
- `protected` fields
- Encapsulation
- Reusing Parent Class Behavior
- Common vs Specialized Behavior

---

# 🐾 Exercise 1 — Animal System

Created an abstract `AnimalSystem` class and extended it with `Dog` and `Cat`.

### Structure

```text
             AnimalSystem
             (abstract)
              /       \
             /         \
          Dog           Cat
```

### AnimalSystem

Contains:

- `name`
- Constructor
- `eat()` method
- Abstract `makeSound()` method

Example:

```java
public abstract class AnimalSystem {

    protected String name;

    public AnimalSystem(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public abstract void makeSound();
}
```

### Dog

```java
public class Dog extends AnimalSystem {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

### Cat

```java
public class Cat extends AnimalSystem {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}
```

### Key Concept

`AnimalSystem` defines the common structure and requires every concrete animal class to implement `makeSound()`.

```text
AnimalSystem
     │
     ├── name
     ├── eat()
     │
     └── makeSound() ← abstract
                         ↓
                  implemented by
                    Dog / Cat
```

---

# 🔵 Exercise 2 — Shape System

Created an abstract `ShapeSystem` class with different implementations for `Circle` and `Rectangle`.

### Structure

```text
              ShapeSystem
              (abstract)
               /       \
              /         \
          Circle      Rectangle
```

### ShapeSystem

```java
public abstract class ShapeSystem {

    public abstract double calculateArea();

    public void displayArea() {
        System.out.println(
            "The area is: " + calculateArea()
        );
    }
}
```

The `calculateArea()` method is abstract because each shape calculates its area differently.

### Circle

```java
public class Circle extends ShapeSystem {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

### Rectangle

```java
public class Rectangle extends ShapeSystem {

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}
```

### Key Concept

The parent class provides the common behavior:

```text
displayArea()
```

while child classes define their own implementation of:

```text
calculateArea()
```

This demonstrates how an abstract class can combine:

- Shared behavior
- Abstract behavior
- Inheritance
- Method overriding

---

# 💳 Exercise 3 — Payment System

Created an abstract payment system to model different payment methods.

### Structure

```text
                 PaymentSystem
                   (abstract)
                /      |       \
               /       |        \
      CreditCard    PayPal    BankTransfer
```

### PaymentSystem

Contains:

- Payment amount
- Constructor
- `showAmount()`
- Abstract `processPayment()`

Example:

```java
public abstract class PaymentSystem {

    protected double amount;

    public PaymentSystem(double amount) {
        this.amount = amount;
    }

    public void showAmount() {
        System.out.println(
            "Payment Amount: " + amount
        );
    }

    public abstract void processPayment();
}
```

### Credit Card Payment

```text
Processing credit card payment...
```

### PayPal Payment

```text
Processing PayPal payment...
```

### Bank Transfer

```text
Processing bank transfer...
```

### Key Concept

Every payment method must implement:

```java
processPayment()
```

but each payment method can define its own implementation.

---

# 👨‍💻 Exercise 4 — Employee System

Created an abstract employee hierarchy.

### Structure

```text
                 Employee
                (abstract)
                /       \
               /         \
        Developer       Manager
```

### Employee

Contains common employee information:

- Name
- Salary
- `displayBasicInfo()`
- Abstract `work()` method

```java
public abstract class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void displayBasicInfo() {
        System.out.println(
            "Name: " + name +
            " Salary: " + salary
        );
    }

    public abstract void work();
}
```

### Developer

Adds:

```text
programmingLanguage
```

and implements:

```java
work()
```

Example behavior:

```text
Simay is developing software with Java.
```

### Manager

Adds:

```text
teamSize
```

and implements:

```java
work()
```

Example behavior:

```text
Ayşe is managing a team.
```

---

# 🧠 What I Learned

## Abstract Class

An abstract class is a class that cannot be instantiated directly.

```java
public abstract class Animal
```

This means:

```java
Animal animal = new Animal();
```

is not allowed.

However, concrete child classes can extend it:

```java
Dog dog = new Dog();
```

---

## Abstract Method

An abstract method has no implementation in the parent class.

```java
public abstract void makeSound();
```

A concrete child class must implement it:

```java
@Override
public void makeSound() {
    System.out.println("Woof!");
}
```

---

## `super()`

`super()` is used to call the parent class constructor.

```java
public Dog(String name) {
    super(name);
}
```

The constructor chain becomes:

```text
Dog Constructor
      ↓
super(name)
      ↓
Animal Constructor
      ↓
this.name = name
```

---

## `@Override`

`@Override` indicates that a child class is providing its own implementation of a method inherited from the parent class.

```java
@Override
public void makeSound() {
    System.out.println("Woof!");
}
```

---

# 🔄 Abstract Class vs Inheritance

Inheritance describes the relationship between classes:

```text
Dog extends Animal
```

Abstract defines how the parent class should be used:

```text
Animal
 ↓
abstract
 ↓
cannot be instantiated directly
```

Together:

```text
        Abstract Parent
              ↓
        ┌─────┴─────┐
        ↓           ↓
      Child       Child
        ↓           ↓
   implementation implementation
```

---

# 📌 Key Takeaways

```text
abstract class
      ↓
Common structure + common behavior

abstract method
      ↓
Behavior that child classes must implement

extends
      ↓
Inheritance

super()
      ↓
Call parent constructor

@Override
      ↓
Child-specific implementation
```

The main idea I learned today:

> **An abstract class provides a common foundation while leaving certain behaviors to its child classes.**

---

# 🚀 Next Steps

- Polymorphism
- Upcasting & Downcasting
- Dynamic Method Dispatch
- Interfaces
- Abstract Classes vs Interfaces
- Exception Handling
- Collections Framework