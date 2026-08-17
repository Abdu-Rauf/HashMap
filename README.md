# MyHashMap

A custom Java hash map implementation built with separate chaining using linked lists. It supports generic key/value pairs, resizing, iteration over keys, and basic dictionary operations.

## Project Overview

This project implements a simplified `HashMap`-like data structure in the package `com.aamir`.

Key components:
- `MyHashMap<K, V>`: the main custom hash map implementation
- `ListNode<K, V>`: node used for chaining entries within each bucket
- `Main`: example usage and demonstration
- `BenchMark`: performance benchmarking against `java.util.HashMap`
- `MyHashMapTest`: JUnit tests for core behavior

## Requirements

- **JDK**: 11 or higher
- **Maven**: 3.6 or higher

## Features

- Generic key/value support
- Hash bucket indexing with power-of-two capacity rounding
- Separate chaining collision handling
- `put`, `get`, `remove`, `containsKey` operations
- Automatic resize when load exceeds 75%
- Iterable keys for traversal
- Null-key protection with `NullPointerException`

## Complexity

| Operation | Average Case | Worst Case |
|-----------|--------------|-----------|
| `put(K, V)` | O(1) | O(n) |
| `get(K)` | O(1) | O(n) |
| `remove(K)` | O(1) | O(n) |
| `containsKey(K)` | O(1) | O(n) |

Average case assumes uniform hash distribution and no excessive collisions. Worst case occurs when all keys hash to the same bucket (all collisions).

## Design Decisions

- **Bucket Index Computation**: Uses bitwise AND (`hash & (capacity - 1)`) instead of modulo (`%`) for faster bucket indexing. This requires power-of-two capacity but avoids modulo's performance penalty and potential negative-index bugs.

- **Simple Chaining**: Deliberately uses basic separate chaining with linked lists instead of advanced techniques like hash spreading (`h ^ (h >>> 16)`) or tree-based fallbacks (Java 8+ red-black trees). This keeps the implementation clear and educational while explaining the performance gap vs. standard HashMap.

- **Load Factor 0.75**: Matches `java.util.HashMap`'s default load factor, providing a proven balance between memory usage and lookup speed. Triggers automatic resizing when `size / capacity > 0.75`.

- **Power-of-Two Capacity**: Capacity is always rounded up to the nearest power of two, enabling fast bitwise bucket indexing and making resize operations straightforward.

## Example Usage

```java
MyHashMap<String, Integer> map = new MyHashMap<>(4);

map.put("Apple", 5);
map.put("Banana", 3);

System.out.println(map.get("Apple"));           // 5
System.out.println(map.containsKey("Banana")); // true
System.out.println(map.remove("Banana"));      // 3
```

## Project Structure

```text
myhash/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── aamir/
│   │               ├── BenchMark.java
│   │               ├── ListNode.java
│   │               ├── Main.java
│   │               └── MyHashMap.java
│   └── test/
│       └── java/
│           └── com/
│               └── aamir/
│                   └── MyHashMapTest.java
└── target/
```

## Setup

1. Install Maven from the official download page:
   https://maven.apache.org/download.cgi#Maven
2. Make sure Maven is available in your PATH.
3. Open a terminal in the project folder.

## Run the Application

```bash
mvn compile
java -cp target/classes com.aamir.Main
```

## Run the Tests

```bash
mvn test
```

## Run the Benchmark

The `BenchMark` class compares `MyHashMap` performance against `java.util.HashMap` for PUT, GET, and REMOVE operations with 100,000 randomly-generated keys:

```bash
mvn clean compile
java -cp target/classes com.aamir.BenchMark
```

### Benchmark Results

Latest verified run on JDK 11 with 100,000 operations and 5 warmup iterations:

```
PUT Operations:
  MyHashMap put: 50 ms
  java.util.HashMap put: 26 ms

GET Operations:
  MyHashMap get: 18 ms
  java.util.HashMap get: 12 ms

REMOVE Operations:
  MyHashMap remove: 23 ms
  java.util.HashMap remove: 17 ms

```

**Analysis**: MyHashMap is approximately 1.5–2× slower on PUT/REMOVE and ~1.5× slower on GET compared to the standard `java.util.HashMap`. This performance gap is expected and primarily due to:
- Absence of hash spreading and bit manipulation optimizations
- Use of simple linked-list chaining instead of tree-based fallback for long chains

The benchmark uses fixed-seed random key generation for reproducibility.

## Notes

- This implementation is designed as a learning project to understand hash map internals.
- The default constructor creates a capacity of 16 buckets.
- Capacity is always maintained as a power of two for efficient bitwise indexing.

