# 📅 Day 6 — Interfaces

Today I focused on **Interfaces in Java** and practiced how interfaces define contracts for classes.

I also practiced using interfaces together with inheritance and abstract classes.

---

# 📚 Topics Covered

- Interfaces
- `implements`
- Interface Methods
- Interface Contracts
- `@Override`
- Multiple Interface Implementation
- `extends` vs `implements`
- Abstract Class + Interface
- Interface-based Design
- Method Parameters
- Interface References

---

# 🧠 What is an Interface?

An interface defines a **contract** that implementing classes must follow.

For example:

```java
public interface Flyable {

    void fly();
}
```

Any class implementing `Flyable` must provide an implementation for `fly()`.

```java
public class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Bird is flying.");
    }
}
```

The interface defines **what a class can do**, while the implementing class defines **how it does it**.

---

# ✈️ Exercise 1 — Flyable

Created a `Flyable` interface and implemented it with different classes.

### Structure

```text
             Flyable
              /    \
             /      \
          Bird    Airplane
```

### Flyable

```java
public interface Flyable {

    void fly();
}
```

### Bird

```java
public class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Bird is flying.");
    }
}
```

### Airplane

```java
public class Airplane implements Flyable {

    @Override
    public void fly() {
        System.out.println("Airplane is flying.");
    }
}
```

### Key Concept

`Bird` and `Airplane` do not need to belong to the same inheritance hierarchy.

They simply share the ability to fly.

```text
Bird       → can fly
Airplane   → can fly
```

The `Flyable` interface represents this shared behavior.

---

# 💳 Exercise 2 — Payment Method

Created a payment system using an interface.

### Structure

```text
                PaymentMethod
                /      |      \
               /       |       \
      CreditCard     PayPal    BankTransfer
```

### PaymentMethod

```java
public interface PaymentMethod {

    void pay(double amount);
}
```

Different payment classes implement the same contract.

### CreditCardPayment

```java
public class CreditCardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println(
            "Paid " + amount + " TL with credit card."
        );
    }
}
```

### PayPalPayment

```java
public class PayPalPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println(
            "Paid " + amount + " TL with PayPal."
        );
    }
}
```

### BankTransferPayment

```java
public class BankTransferPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println(
            "Paid " + amount + " TL via bank transfer."
        );
    }
}
```

### Key Concept

All payment classes must implement:

```java
pay(double amount)
```

but each class can provide its own implementation.

---

# 🦆 Exercise 3 — Abstract Class + Multiple Interfaces

This exercise combined the concepts learned on Day 5 and Day 6.

### Structure

```text
                    Animal
                  (abstract)
                      ↑
                      |
                    Duck
                  /      \
                 ↓        ↓
             Flyable   Swimmable
```

`Duck` is an `Animal`, but it can also fly and swim.

```java
public class Duck extends Animal
        implements Flyable, Swimmable {
}
```

This demonstrates that a class can:

- Extend one class
- Implement multiple interfaces

---

## Animal

`Animal` is an abstract class containing common state and behavior.

```java
public abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public abstract void makeSound();
}
```

---

## Flyable

```java
public interface Flyable {

    void fly();
}
```

---

## Swimmable

```java
public interface Swimmable {

    void swim();
}
```

---

## Duck

```java
public class Duck extends Animal
        implements Flyable, Swimmable {

    public Duck(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Quack!");
    }

    @Override
    public void fly() {
        System.out.println("Duck is flying.");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming.");
    }
}
```

### Key Concept

This class demonstrates three different relationships:

```text
Duck extends Animal
```

Duck **is an** Animal.

```text
Duck implements Flyable
```

Duck **can** fly.

```text
Duck implements Swimmable
```

Duck **can** swim.

---

# 🔔 Exercise 4 — Notification System

Created a notification system using interfaces.

### Structure

```text
              NotificationService
                 /      |      \
                /       |       \
             Email      SMS      Push
```

### NotificationService

```java
public interface NotificationService {

    void send(String message);
}
```

---

## Email Notification

```java
public class EmailNotification
        implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println(
            "Sending email: " + message
        );
    }
}
```

---

## SMS Notification

```java
public class SmsNotification
        implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println(
            "Sending SMS: " + message
        );
    }
}
```

---

## Push Notification

```java
public class PushNotification
        implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println(
            "Sending push notification: " + message
        );
    }
}
```

### Key Concept

All notification types follow the same contract:

```java
send(String message)
```

but each implementation handles the message differently.

---

# 🔑 `extends` vs `implements`

One of the most important concepts learned today:

### `extends`

Used when a class inherits from another class.

```java
public class Dog extends Animal
```

Meaning:

> Dog is an Animal.

---

### `implements`

Used when a class implements an interface.

```java
public class Bird implements Flyable
```

Meaning:

> Bird can fly.

---

# 🧠 Abstract Class vs Interface

### Abstract Class

Represents:

> **What something is**

Examples:

```text
Dog → Animal
Car → Vehicle
Developer → Employee
```

An abstract class can contain:

- Fields
- Constructors
- Normal methods
- Abstract methods

---

### Interface

Represents:

> **What something can do**

Examples:

```text
Bird → Flyable
Duck → Swimmable
Developer → Programmable
Payment → Payable
```

Interfaces are useful for defining behaviors that can be shared by unrelated classes.

---

# 🔥 Multiple Interfaces

A Java class can extend only one class:

```java
public class Duck extends Animal
```

but it can implement multiple interfaces:

```java
public class Duck
        extends Animal
        implements Flyable, Swimmable {
}
```

This allows a class to combine multiple behaviors.

---

# 🧩 Key Concepts Learned

```text
Interface
    ↓
Contract
    ↓
implements
    ↓
@Override
    ↓
Concrete Implementation
```

And:

```text
Abstract Class
        ↓
     extends
        ↓
   Common Structure
```

while:

```text
Interface
        ↓
   implements
        ↓
    Behavior
```

---

# 💻 Backend Connection

Interfaces are especially important in backend development.

A common backend architecture can look like:

```text
Controller
    ↓
Service Interface
    ↓
Service Implementation
    ↓
Repository Interface
    ↓
Database
```

For example:

```java
public interface UserService {

    User findUserById(int id);
}
```

An implementation can then provide the actual behavior:

```java
public class UserServiceImpl
        implements UserService {

    @Override
    public User findUserById(int id) {
        // Business logic
    }
}
```

This approach helps create loosely coupled and maintainable applications.

---

# 📂 Files

```text
day06
│
├── README.md
│
├── Flyable.java
├── Bird.java
├── Airplane.java
│
├── PaymentMethod.java
├── CreditCardPayment.java
├── PayPalPayment.java
├── BankTransferPayment.java
│
├── Animal.java
├── Duck.java
├── Swimmable.java
│
├── NotificationService.java
├── EmailNotification.java
├── SmsNotification.java
└── PushNotification.java
```

---

# 📅 Day 6 Summary

Today I learned that:

- An interface defines a contract.
- Classes implement interfaces using `implements`.
- Implementing classes must provide the required methods.
- `@Override` is used when implementing interface methods.
- A class can implement multiple interfaces.
- A class can extend one class and implement multiple interfaces.
- Abstract classes are useful for shared state and behavior.
- Interfaces are useful for shared capabilities and contracts.
- `extends` represents inheritance.
- `implements` represents implementing a behavior contract.

---

# 🚀 Next Topics

- Polymorphism
- Upcasting
- Downcasting
- Dynamic Method Dispatch
- Interface References
- Dependency Injection
- Exception Handling
- Collections Framework
- Spring Boot