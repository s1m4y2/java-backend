# 📅 Day 4 — Method Overloading, Access Modifiers & Inheritance

Today I focused on core Object-Oriented Programming concepts in Java.

The main goal was to understand how classes communicate with each other, how access to data is controlled, and how inheritance allows classes to reuse and extend existing behavior.

---

## 📚 Topics Covered

- Method Overloading
- Access Modifiers
  - `private`
  - `protected`
  - `public`
  - default / package-private
- Inheritance
- `extends`
- `super`
- Constructor Chaining
- Method Overriding
- `@Override`
- Encapsulation Review
- `this` vs `super`

---

## 💻 Practice Applications

### 🧮 Calculator — Method Overloading

Created a calculator to practice method overloading.

The `calculate()` method is implemented with different parameter lists:

```java
calculate(int a, int b)
calculate(double a, double b)
calculate(int a, int b, int c)
calculate(double a, double b, double c)
```

### Key Concept

Method overloading allows multiple methods to have the same name as long as their parameter lists are different.

```text
Same method name
       +
Different parameters
       =
Method Overloading
```

Return type alone is not enough for overloading.

---

## 👤 Person — Access Modifiers

Created a `Person` class to practice access control.

Fields were defined using different access modifiers:

```java
private String name;
private int age;
protected String email;
private String password;
```

Getter methods were implemented for selected fields.

### Access Levels

| Modifier | Same Class | Same Package | Subclass | Everywhere |
|----------|:----------:|:------------:|:--------:|:----------:|
| `private` | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ⚠️ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

This exercise helped reinforce the importance of encapsulation and controlled access to data.

---

## 🚗 Vehicle & Car — Inheritance

Created a parent-child class relationship:

```text
Vehicle
   ↑
   |
  Car
```

`Car` extends `Vehicle` using:

```java
public class Car extends Vehicle
```

The `Vehicle` class contains common properties such as:

```java
protected String brand;
protected int year;
```

The `Car` class adds its own properties:

```java
private String model;
private double price;
```

---

## 🔗 Using `super`

The `Car` constructor calls the parent constructor:

```java
super(brand, year);
```

This allows the `Vehicle` constructor to initialize the fields inherited by `Car`.

Conceptually:

```text
Car Constructor
      ↓
super(brand, year)
      ↓
Vehicle Constructor
      ↓
Parent fields initialized
```

---

## 👨‍💻 Employee Hierarchy

Created an inheritance hierarchy:

```text
             Employee
             /       \
            /         \
     Developer       Manager
```

### Employee

Contains common employee information:

- Name
- Salary

### Developer

Adds:

- Programming language

### Manager

Adds:

- Team size

Both subclasses use:

```java
super(name, salary);
```

to initialize the parent class.

---

## 🔄 Method Overriding

`Developer` and `Manager` override the `displayEmployeeInfo()` method from `Employee`.

Example:

```java
@Override
void displayEmployeeInfo() {
    super.displayEmployeeInfo();
    // subclass-specific information
}
```

This allows the child class to reuse the parent's behavior and extend it with additional behavior.

---

## 🧠 Key Concepts Learned

### `this`

Refers to the current object.

```java
this.name = name;
```

The left side refers to the object's field, while the right side refers to the constructor parameter.

---

### `super`

Refers to the parent class.

It can be used to:

#### Call the parent constructor

```java
super(name, salary);
```

#### Call a parent method

```java
super.displayEmployeeInfo();
```

---

### Overloading vs Overriding

| | Overloading | Overriding |
|---|---|---|
| Relationship | Same class | Parent → Child |
| Method name | Same | Same |
| Parameters | Different | Same |
| Purpose | Multiple versions of a method | Change/extend inherited behavior |
| Annotation | Not required | `@Override` |

---

## 🎯 Key Takeaways

Today I learned that inheritance is not just about reusing code. It also allows classes to model real-world relationships between objects.

I also learned the difference between:

```text
this  → current object
super → parent class
```

and:

```text
overloading  → same method name, different parameters
overriding   → child class redefines parent behavior
```

Understanding access modifiers also showed me how encapsulation can be used to protect an object's internal state.

---

## 🚀 Next Steps

- Abstract Classes
- Abstract Methods
- Interfaces
- Polymorphism
- Exception Handling
- Collections
- Generics
- Lambda Expressions
- Stream API