# 📚 Day 09 — Java Collections Framework

> I explored the Java Collections Framework hands-on by working with List, Set, and Map structures.

---

## 🎯 Today's Goal

On Day 09, I worked on the Java Collections Framework.

I learned when to use each Collection structure, their core methods, and the differences between them.

In particular, I practiced with:

- `ArrayList`
- `LinkedList`
- `HashSet`
- `LinkedHashSet`
- `TreeSet`
- `HashMap`

---

## 🧠 Topics Covered

### 📋 List

#### ArrayList

`ArrayList` is a `List` implementation that keeps elements in order and provides access via index.

Core methods I learned:

```java
add()
get()
set()
remove()
contains()
size()
isEmpty()
clear()
```

Example:

```java
ArrayList<String> students = new ArrayList<>();

students.add("Ali");
students.add("Ayşe");
students.add("Simay");

System.out.println(students.get(2));
```

#### LinkedList

`LinkedList` stores elements using a linked node structure.

It's especially useful for operations at the beginning and end of the list.

Methods I learned:

```java
addFirst()
addLast()
getFirst()
getLast()
removeFirst()
removeLast()
```

Example:

```java
LinkedList<String> students = new LinkedList<>();

students.add("Ali");
students.add("Ayşe");

students.addFirst("Zeynep");
students.addLast("Simay");
```

#### ⚖️ ArrayList vs LinkedList

| Operation           | ArrayList       | LinkedList |
|----------------------|-----------------|------------|
| get(index)           | O(1)            | O(n)       |
| Insert at front       | O(n)            | O(1)       |
| Remove from front     | O(n)            | O(1)       |
| Index-based access     | O(1)            | O(n)       |
| Insert at end          | O(1) amortized  | O(1)       |

**The key difference I learned**

```text
Fast access by index
        ↓
    ArrayList

Frequent insert/remove at front or back
        ↓
    LinkedList
```

---

### 🧩 Set

The core property of Set structures:

**They do not accept duplicate elements.**

#### HashSet

`HashSet` does not keep duplicate elements.

The insertion order of elements is not guaranteed.

```java
HashSet<String> students = new HashSet<>();

students.add("Ali");
students.add("Ayşe");
students.add("Ali");
```

As a result, `Ali` only appears once.

**When to use**

```text
Unique elements
+
Order doesn't matter
        ↓
    HashSet
```

#### LinkedHashSet

`LinkedHashSet` prevents duplicates and preserves insertion order.

```java
LinkedHashSet<String> students = new LinkedHashSet<>();

students.add("Mehmet");
students.add("Ali");
students.add("Simay");
students.add("Ali");
```

Result:

```text
[Mehmet, Ali, Simay]
```

**When to use**

```text
Unique elements
+
Insertion order matters
        ↓
 LinkedHashSet
```

#### TreeSet

`TreeSet` prevents duplicates and keeps elements in their natural sort order.

```java
TreeSet<String> students = new TreeSet<>();

students.add("Simay");
students.add("Mehmet");
students.add("Ali");
students.add("Ayşe");
```

Result:

```text
[Ali, Ayşe, Mehmet, Simay]
```

**When to use**

```text
Unique elements
+
Needs to be sorted
        ↓
    TreeSet
```

#### 🗺️ Set Comparison

| Collection      | Duplicates | Order                |
|------------------|------------|------------------------|
| HashSet          | ❌         | Not guaranteed          |
| LinkedHashSet    | ❌         | Insertion order         |
| TreeSet          | ❌         | Sorted                  |

**Memory trick**

```text
HashSet
→ Unique

LinkedHashSet
→ Unique + Insertion Order

TreeSet
→ Unique + Sorted
```

---

### 🗂️ Map

#### HashMap

`HashMap` stores data as a:

```text
Key → Value
```

relationship.

For example:

```text
Simay → 95
Ali   → 80
Ayşe  → 90
```

In Java:

```java
HashMap<String, Integer> grades = new HashMap<>();
```

Here:

```text
String  → Key
Integer → Value
```

#### HashMap Methods

**put()**

Adds an element or updates the value of an existing key.

```java
grades.put("Simay", 95);
```

If the same key is used again:

```java
grades.put("Simay", 100);
```

the existing value gets updated.

**get()**

Returns the value for a given key.

```java
grades.get("Simay");
```

Result:

```text
95
```

**containsKey()**

Checks whether a given key exists in the Map.

```java
grades.containsKey("Simay");
```

**containsValue()**

Checks whether a given value exists in the Map.

```java
grades.containsValue(95);
```

**remove()**

Removes a specific key-value pair.

```java
grades.remove("Ali");
```

#### 🔄 HashMap Iteration

**keySet()**

To get only the keys:

```java
for (String student : grades.keySet()) {
    System.out.println(student);
}
```

**values()**

To get only the values:

```java
for (Integer grade : grades.values()) {
    System.out.println(grade);
}
```

**entrySet()**

To get both key and value together:

```java
for (Map.Entry<String, Integer> entry : grades.entrySet()) {
    System.out.println(
        entry.getKey() + " → " + entry.getValue()
    );
}
```

**Summary**

```text
keySet()
    ↓
Key only

values()
    ↓
Value only

entrySet()
    ↓
Key + Value
```

---

## ⚠️ Important Java Detail

While working with `ArrayList<Integer>`, I learned an important detail about the `remove()` method.

```java
numbers.remove(25);
```

Here, `25` is interpreted as an `int` and the method tries to remove the element at index 25.

To remove the value `25` instead:

```java
numbers.remove(Integer.valueOf(25));
```

can be used.

This is an important example for understanding method overloading and the difference between `Integer` and `int` in Java.

---

## 💻 Practice Files

Throughout Day 09, I built the following practice applications:

**ArrayList — `ArrayListPractice.java`**
- `add()`
- `get()`
- `set()`
- `remove()`
- `contains()`
- `size()`
- `isEmpty()`
- `clear()`

**LinkedList — `LinkedListPractice.java`**
- `addFirst()`
- `addLast()`
- `getFirst()`
- `getLast()`
- `removeFirst()`
- `removeLast()`

**HashSet — `HashSetPractice.java`**
- Duplicate check
- `contains()`
- `remove()`

**LinkedHashSet — `LinkedHashSetPractice.java`**
- Duplicate check
- Insertion order

**TreeSet — `TreeSetPractice.java`**
- Duplicate check
- Natural ordering
- `contains()`
- `remove()`

**HashMap — `HashMapPractice.java`**
- `put()`
- `get()`
- `containsKey()`
- `containsValue()`
- `remove()`
- `keySet()`
- `values()`
- `entrySet()`

---

## 🚀 Mini Project — Student Management System

At the end of Day 09, I used different Collection structures together in a single application.

**`StudentManagementSystem.java`**

Structures used:

```java
ArrayList<String> students;
HashSet<String> uniqueStudents;
HashMap<String, Integer> grades;
```

**ArrayList** — to keep the list of students:

```text
Ali
Ayşe
Simay
Mehmet
```

**HashSet** — to prevent duplicate students:

```text
Ali
Ayşe
Simay
Mehmet
```

**HashMap** — to keep the student → grade relationship:

```text
Ali    → 80
Ayşe   → 90
Simay  → 95
Mehmet → 75
```

I also:

- Retrieved Simay's grade.
- Updated Simay's grade from 95 → 100.
- Iterated over all student-grade pairs using `entrySet()`.

---

## 🧠 Collection Selection Guide

I learned to first identify the requirement before choosing a Collection.

```text
Fast access by index
        ↓
    ArrayList

Frequent insert/remove at front or back
        ↓
    LinkedList

Unique + order doesn't matter
        ↓
    HashSet

Unique + insertion order
        ↓
 LinkedHashSet

Unique + sorted
        ↓
    TreeSet

Key → Value relationship
        ↓
    HashMap
```

---

## 📂 Day 09 Structure

```text
day09
│
├── ArrayListPractice.java
├── LinkedListPractice.java
├── HashSetPractice.java
├── LinkedHashSetPractice.java
├── TreeSetPractice.java
├── HashMapPractice.java
├── StudentListManager.java
├── NumberListPractice.java
├── StudentManagementSystem.java
└── README.md
```

---

## 🎯 Key Takeaways

By the end of Day 09, I:

- Learned the core structure of the Java Collections Framework.
- Learned the differences between List, Set, and Map.
- Learned the difference between ArrayList and LinkedList.
- Learned the differences between HashSet, LinkedHashSet, and TreeSet.
- Learned how to build Key-Value relationships with HashMap.
- Learned how to use for-each on Collections.
- Learned how to use `keySet()`, `values()`, and `entrySet()`.
- Learned how to choose a Collection based on the requirement.
- Practiced by combining different Collection structures in a single application.

---

## 📈 Day 09 Status

**Day 09 — Collections Framework ✅**

| Topic                      | Status |
|------------------------------|--------|
| ArrayList                    | ✅     |
| LinkedList                   | ✅     |
| HashSet                      | ✅     |
| LinkedHashSet                 | ✅     |
| TreeSet                       | ✅     |
| HashMap                       | ✅     |
| for-each                      | ✅     |
| Collection Selection          | ✅     |
| Mini Challenge                | ✅     |

---

⭐ Learning Java one concept at a time and building toward Spring Boot Backend Development.