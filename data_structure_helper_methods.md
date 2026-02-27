## Queue (Queue<Integer>) — FIFO

```java
Queue<Integer> queue = new ArrayDeque<>();
```

Methods:

- offer() → enqueue (add to back)
- poll() → dequeue (remove from front)
- peek() → front element
- isEmpty() → check if empty
- size() → number of elements

## Stack (Deque<Integer>) — LIFO

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Methods:
- push() → push to top
- pop() → pop from top
- peek() → top element
- isEmpty() → check if empty
- size() → number of elements

## Double-Ended Queue (Deque<Integer>) — Deque

```java
Deque<Integer> deque = new ArrayDeque<>();
```

### Front operations:
- enqueueFront() → add to front (addFirst)
- dequeueFront() → remove from front (pollFirst)
- peekFront() → front element (peekFirst)

### Back operations:
- enqueueBack() → add to back (addLast)
- dequeueBack() → remove from back (pollLast)
- peekBack() → back element (peekLast)

### Utilities:
- isEmpty() → check if empty
- size() → number of elements

## Heap
- default heap is min-heap

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


