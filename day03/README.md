# 📅 Day 3 - Encapsulation & Static

## 📚 Topics Covered

- Encapsulation
- Access Modifiers (`public`, `private`)
- Getter & Setter Methods
- `this` Keyword Review
- Constructors
- Static Variables
- Static Methods
- Pass by Value
- Reference Types
- `==` vs `equals()`

---

## 💻 Practice Applications

### 🏦 Bank Account

A simple bank account simulation to practice encapsulation.

**Features**

- Create a bank account using a constructor
- Deposit money
- Withdraw money
- Prevent invalid transactions
- Read account information using getters

**Concepts Used**

- Encapsulation
- Private Fields
- Constructor
- Getter Methods
- Business Logic

---

### 👨‍💼 Employee

A class created to understand how static variables work.

**Features**

- Multiple employee objects
- Shared company name
- Display employee information

**Concepts Used**

- Static Variables
- Constructors
- Objects
- Class Members

---

### ⚖️ Equals Demo

Practice comparing objects and strings.

**Concepts Used**

- `==`
- `equals()`
- Reference Comparison
- Value Comparison

---

## 🧠 Key Concepts Learned

### Encapsulation

- Keep class fields private.
- Access data through getters.
- Modify data only through controlled methods.

Example:

```java
private double balance;

public double getBalance() {
    return balance;
}
```

Instead of allowing:

```java
account.setBalance(1000000);
```

Use business methods like:

```java
deposit();
withdraw();
```

---

### Static

A static variable belongs to the class, not to individual objects.

Example:

```java
private static String companyName = "Google";
```

All Employee objects share the same company name.

---

### `==` vs `equals()`

`==`

- Compares object references.

`equals()`

- Compares object contents.

---

## 🎯 What I Learned

Today I learned why encapsulation is one of the core principles of Object-Oriented Programming.

I understood when to use getters and setters, why not every field should have a setter, and how static variables are shared across all objects of the same class.

I also learned the difference between comparing object references (`==`) and comparing object values (`equals()`).

---

## 🚀 Next Goal

- Inheritance (`extends`)
- `super`
- Method Overriding
- Abstract Classes
- Interfaces

---

## 📝 Reflection

The biggest lesson today was understanding that not every variable should be directly accessible.

I realized that good object-oriented design is not only about writing code that works, but also about protecting data and exposing only the operations that make sense.

Learning how `static` variables belong to the class rather than individual objects helped me better understand memory management and object creation in Java.