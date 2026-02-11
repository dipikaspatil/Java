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
