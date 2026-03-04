## Array

### 1. Declaration & Initialization

#### 1D array
int[] arr = new int[5];                // size 5, default 0
int[] arr2 = {1,2,3,4,5};             // initialize with values

#### 2D array
int[][] matrix = new int[3][4];       // 3 rows, 4 cols
int[][] grid = {{1,2,3},{4,5,6},{7,8,9}}; 

#### ArrayList (dynamic array)
ArrayList<Integer> list = new ArrayList<>();
list.add(10);      // append
list.get(0);       // access
list.set(0, 20);   // modify
list.size();       // length

### 2. Traversal

#### 1D array
for(int i = 0; i < arr.length; i++)
    System.out.println(arr[i]);

for(int num : arr)
    System.out.println(num);

#### 2D array
for(int i = 0; i < matrix.length; i++){
    for(int j = 0; j < matrix[0].length; j++)
        System.out.print(matrix[i][j] + " ");
    System.out.println();
}

### 3. Common Operations
import java.util.Arrays;

#### Sorting
Arrays.sort(arr);                   // ascending
Arrays.sort(arr, Collections.reverseOrder()); // descending (Integer[])

#### Searching
int idx = Arrays.binarySearch(arr, 5); // index or negative if not found

#### Copy / Clone
int[] copy = arr.clone();
int[] copy2 = Arrays.copyOf(arr, arr.length);

#### Fill / Initialize
Arrays.fill(arr, 7);                 // fill all with 7

#### Compare
boolean eq = Arrays.equals(arr, arr2);

#### Print
System.out.println(Arrays.toString(arr));
System.out.println(Arrays.deepToString(matrix)); // for 2D

### 4. Prefix Sum & Sliding Window

#### Prefix sum
int[] prefix = new int[arr.length];
prefix[0] = arr[0];
for(int i = 1; i < arr.length; i++)
    prefix[i] = prefix[i-1] + arr[i];

#### Sliding window sum of size k
int k = 3;
int windowSum = 0;
for(int i = 0; i < k; i++)
    windowSum += arr[i];
int maxSum = windowSum;
for(int i = k; i < arr.length; i++){
    windowSum += arr[i] - arr[i-k];
    maxSum = Math.max(maxSum, windowSum);
}

### 5. Two Pointers & Reverse

#### Reverse 1D array
int left = 0, right = arr.length-1;
while(left < right){
    int temp = arr[left]; arr[left++] = arr[right]; arr[right--] = temp;
}

#### Two pointers example (sorted array sum)
int target = 10;
int i = 0, j = arr.length-1;
while(i < j){
    int sum = arr[i] + arr[j];
    if(sum == target) break;
    else if(sum < target) i++;
    else j--;
}

### 6. Rotate Array

#### Rotate right k steps
k = k % arr.length;
reverse(arr, 0, arr.length-1);
reverse(arr, 0, k-1);
reverse(arr, k, arr.length-1);

void reverse(int[] a, int start, int end){
    while(start < end){
        int t = a[start]; a[start++] = a[end]; a[end--] = t;
    }
}


## 🔹 What is a Stack?

**LIFO** → **Last In, First Out**

* Operations happen at the **top** of the stack

### Common Uses

* Parentheses checking
* Next Greater / Smaller Element
* Undo / Backtracking
* Expression evaluation
* Monotonic stack problems

---

## 🔹 Stack Implementation in Java

### Using `Stack` class

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
```

### Using `Deque` (✅ Preferred)

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
```

✅ Faster and **recommended over `Stack`**

---

## 🔹 Core Stack Operations

| Operation | Stack             | Deque             |
| --------- | ----------------- | ----------------- |
| Push      | `stack.push(x)`   | `stack.push(x)`   |
| Pop       | `stack.pop()`     | `stack.pop()`     |
| Peek      | `stack.peek()`    | `stack.peek()`    |
| Empty     | `stack.isEmpty()` | `stack.isEmpty()` |
| Size      | `stack.size()`    | `stack.size()`    |

---

## 🔹 Basic Stack Template

```java
for (int x : arr) {
    stack.push(x);
}

while (!stack.isEmpty()) {
    int top = stack.pop();
}
```

---

## 🔹 Stack with Characters

```java
Stack<Character> stack = new Stack<>();

stack.push('(');
char ch = stack.pop();
```

---

## 🔹 Parentheses Validation (Classic)

```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((c == ')' && top != '(') ||
                (c == '}' && top != '{') ||
                (c == ']' && top != '[')) {
                return false;
            }
        }
    }
    return stack.isEmpty();
}
```

---

## 🔹 Monotonic Stack (🔥 VERY IMPORTANT)

### 🔼 Monotonic Increasing Stack

* Keeps elements in **increasing order**
* Used for **Next Smaller Element**

```java
Stack<Integer> stack = new Stack<>();

for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && stack.peek() > arr[i]) {
        stack.pop();
    }
    stack.push(arr[i]);
}
```

---

### 🔽 Monotonic Decreasing Stack

* Keeps elements in **decreasing order**
* Used for **Next Greater Element**

```java
Stack<Integer> stack = new Stack<>();

for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && stack.peek() < arr[i]) {
        stack.pop();
    }
    stack.push(arr[i]);
}
```

---

## 🔹 Next Greater Element (Template)

```java
int[] nge = new int[n];
Stack<Integer> stack = new Stack<>();

for (int i = n - 1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() <= nums[i]) {
        stack.pop();
    }
    nge[i] = stack.isEmpty() ? -1 : stack.peek();
    stack.push(nums[i]);
}
```

---

## 🔹 Stack with Indices (Very Common)

```java
Stack<Integer> stack = new Stack<>();

for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        stack.pop();
    }
    stack.push(i);
}
```

---

## 🔹 Convert Stack to Array / List

```java
List<Integer> list = new ArrayList<>(stack);
```

---

## 🔹 Common Stack Problems (LeetCode)

* Valid Parentheses
* Next Greater Element I / II
* Daily Temperatures
* Min Stack
* Evaluate Reverse Polish Notation
* Largest Rectangle in Histogram
* Remove All Adjacent Duplicates

---

## 🔹 Time & Space Complexity

* Push / Pop / Peek → **O(1)**
* Extra space → **O(n)**

---

## 🔥 Interview Tips

* If a problem mentions **next**, **previous**, or **nearest** → think **stack**
* If **order matters** → use a **monotonic stack**
* Prefer **Deque** over `Stack`
* Use **indices** when results depend on positions

## 1️⃣ What is a Heap?

A heap is a specialized tree-based data structure that satisfies the heap property:

- Max-Heap: Parent ≥ children → largest element at the root.
- Min-Heap: Parent ≤ children → smallest element at the root.

Properties:

- Complete binary tree → all levels are fully filled except possibly the last, filled left to right.
- Efficient for quickly finding min/max.
- Commonly used in priority queues, scheduling, and graph algorithms (like Dijkstra’s).

### Heap in Java

Java doesn’t have a Heap class, but PriorityQueue implements a heap internally.

By default, PriorityQueue is a min-heap.

For max-heap, we use a custom comparator: (a, b) -> b - a.

```java

PriorityQueue<Integer> minHeap = new PriorityQueue<>();

PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

```

- offer() - add O(log n)
- poll() - remove O(log n)
- peek() - O(1)
- size() - O(1)
- isEmpty() - O(1)

Build heap from n elements - O(n)

### Use Cases of Heaps

- Priority Queue: Task scheduling, CPU job queues
- Kth largest/smallest element
- Heap sort
- Graph algorithms: Dijkstra, Prim

💡 Tip: Think of a heap as a special tree where the top is always the “priority” element, and every insert or remove automatically reorganizes to maintain the heap property. In Java, PriorityQueue handles this for you.

```java
(a, b) -> b - a // lambda expression, which is a Comparator.
```

- Comparator is what defines the ordering in the heap.
- The lambda is shorthand for:

```java
new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return b - a;
    }
}
```

### How the Comparator Works

- compare(a, b) returns:
    - negative → a comes before b
    - 0 → a equals b
    - positive → a comes after b
- b - a makes the largest number come first, so the largest element is at the top of the heap.

```java
a = 3, b = 7
compare(a, b) = 7 - 3 = 4 → positive → 3 comes after 7 → 7 is "higher priority"
```

This effectively turns the default min-heap into a max-heap.

## 1️⃣ What is a Lambda Expression?

A lambda expression in Java is a short way to write an implementation of a functional interface.

- Functional Interface: An interface with exactly one abstract method (e.g., Comparator, Runnable, Callable).
- Lambda allows us to pass behavior as a parameter, instead of writing a full anonymous class.

Syntax:

```java
(parameters) -> expression
```

OR 

```java
(parameters) -> { statements; }
```

### Basic Examples
Example 1: Runnable (no parameters, no return)

```java
Runnable r = () -> System.out.println("Hello, world!");
r.run(); // prints "Hello, world!"
```

- No parameters → ()
- Single statement → no {} needed

### Example 2: Comparator (parameters, return value)

```java
Comparator<Integer> cmp = (a, b) -> a - b; // min-heap order
```

Equivalent using anonymous class:

```java
Comparator<Integer> cmp = new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return a - b;
    }
};
```

✅ Much shorter and cleaner!

### Example 3: Lambda with multiple statements

```java
Comparator<Integer> cmp = (a, b) -> {
    System.out.println("Comparing " + a + " and " + b);
    return b - a; // max-heap
};
```

- Multiple statements → use {}
- Must include explicit return if the lambda returns a value

### Lambda in Heaps

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

- (a, b) → two integers to compare
- b - a → defines ordering in the heap (max-heap)

Without lambda, you’d need a full anonymous class:

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
    public int compare(Integer a, Integer b) {
        return b - a;
    }
});
```
- Lambda makes it 1 line instead of 6+ lines.

### Rules of Lambda Expressions

1. Functional Interface Only: Can only implement interfaces with 1 abstract method.
2. Parameter types can often be omitted: Java can infer them.
```java
(a, b) -> a + b
```

3. Single statement → return implicit:
```java
(a, b) -> a - b  // returns a-b automatically
```

4. Multiple statements → need {} and return:

```java
(a, b) -> {
    int diff = a - b;
    return diff;
}
```

### Benefits of Lambdas

- Less boilerplate code
- More readable & expressive
- Lets you pass behavior to methods (e.g., sorting, filtering, heaps)
- Works well with Streams API

### Example with Stream API

```java
List<Integer> numbers = Arrays.asList(5, 3, 8, 1);
numbers.sort((a, b) -> b - a); // sorts descending
System.out.println(numbers);   // [8, 5, 3, 1]
```

## String

### Creating Strings
```java
String s1 = "Hello";                 // String literal (preferred)
String s2 = new String("Hello");     // Creates new object
```

### String Immutability
```java
String s = "Hi";
s.concat(" there");
System.out.println(s); // "Hi" (unchanged)
```

### Common String Methods
```java
# Length & Access
s.length();          // int
s.charAt(0);         // char

# Comparison
s.equals("Hi");              // content comparison
s.equalsIgnoreCase("hi");
s == "Hi";                   // reference comparison (avoid)
s.compareTo("Hello");        // lexicographic

# Substrings
s.substring(1);      // from index
s.substring(1, 3);   // [1, 3)

# Searching
s.contains("i");
s.indexOf("i");
s.lastIndexOf("i");
s.startsWith("H");
s.endsWith("i");

# Case Conversion
s.toUpperCase();
s.toLowerCase();

# Replace & Remove
s.replace("i", "a");
s.replaceAll("\\d", "");   // regex
s.replaceFirst("i", "a");
s.trim();                  // removes leading/trailing spaces

# Split & Join
String[] parts = s.split(" ");
String joined = String.join("-", parts);

# String Concatenation
String a = "Hello";
String b = "World";

String c = a + " " + b;         // simple
String d = a.concat(" ").concat(b);

# ⚠️ In loops, avoid + → use StringBuilder.

# StringBuilder & StringBuffer

StringBuilder sb = new StringBuilder("Hi");
sb.append(" there");
sb.insert(2, "!");
sb.delete(2, 3);
sb.reverse();

String result = sb.toString();

# StringBuilder → fast, not thread-safe

# StringBuffer → thread-safe, slower

# Checking Empty / Blank
s.isEmpty();   // length == 0
s.isBlank();   // Java 11+, whitespace only

# Null-Safe Pattern
if ("Hi".equals(s)) { ... }   // avoids NullPointerException

# Converting To / From String
# To String

String.valueOf(10);
String.valueOf(true);
String.valueOf(obj);

# From String
int n = Integer.parseInt("123");
double d = Double.parseDouble("3.14");

# Escape Characters
"\n"  // newline
"\t"  // tab
"\""  // double quote
"\\"  // backslash

# Useful Regex Examples
s.matches("\\d+");       // only digits
s.replaceAll("\\s+", " "); // normalize spaces

```

### Performance Tips ⚡

- Use String literals when possible
- Use StringBuilder in loops
- Avoid unnecessary new String(...)