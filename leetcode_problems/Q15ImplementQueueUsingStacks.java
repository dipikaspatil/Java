// https://leetcode.com/problems/implement-queue-using-stacks/description/
/*
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.

*/

/*
"Implementing a Queue using Stacks" - once you move elements to the second stack, you must leave them there. You only move elements from stack1 to stack2 when stack2 is completely empty. This ensures that the oldest elements always stay at the top of stack2 until they are all used up.

Push - add to stack1
Pop/Peek - if stack2 is empty - move all elements to stack2. It will reverse all elements and becomes indirect queue FIFO
If anything added into stack1 later, we can again peek or pop once when all stack2 elements are popped

Eg - push 1 , push 2, push 3 - stack1 [3,2,1]
pop() - reverse - stack2 - [1,2,3] and return 1
peek() - stack2[2,3] - return 2 - order is already reversed. so stack2 is ideally a queue
push 4 - stack1 = [4] - this 4 will be needed only after stack2 elements are consumed. so let it be in stack2 until pop comes with stack2 empty

*/

/* Complexity
Time Complexity: Push - O(1), Pop - Amortized O(1), Peek - Amortized O(1)
Space Complexity: O(n) where n is the number of elements in the queue

What is amartized time complexity? - 
Amortized time complexity is a way to analyze the average time taken per operation over a sequence of operations, even if some individual operations may take longer. 
In this case, while the pop and peek operations may take O(n) time in the worst case (when stack2 is empty and we need to move all elements from stack1 to stack2), 
the average time taken for each operation over a sequence of operations will be O(1) because 

-- > IMP ** each element is moved at most once from stack1 to stack2. ** IMP < -- 

Therefore, the total time taken for n operations will be O(n), leading to an amortized time complexity of O(1) per operation. **
*/

import java.util.ArrayDeque;
import java.util.Deque;

public class Q15ImplementQueueUsingStacks {
    private Deque<Integer> stack1 = new ArrayDeque<>();
    private Deque<Integer> stack2 = new ArrayDeque<>();

    public Q15ImplementQueueUsingStacks() {
        
    }
    
    public void push(int x) {
        stack1.push(x);
    }
    
    public int pop() {
        if (stack2.isEmpty()) {
            shuffleStack1Elements();
        }
        return stack2.pop();
    }
    
    public int peek() {
        if (stack2.isEmpty()) {
            shuffleStack1Elements();
        }
        return stack2.peek();
    }
    
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // Shuffle only if stack2 is empty
    private void shuffleStack1Elements() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    public static void main(String[] args) {
        Q15ImplementQueueUsingStacks obj = new Q15ImplementQueueUsingStacks();
        obj.push(1);
        obj.push(2);
        assert obj.peek() == 1; // 1
        assert obj.pop() == 1; // 1
        assert !obj.empty(); // false
    }

}
