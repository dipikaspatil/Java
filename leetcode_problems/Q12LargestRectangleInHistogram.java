// https://leetcode.com/problems/largest-rectangle-in-histogram/
/*
Problem: Given an array of integers heights representing the histogram's bar heights, return the area of the largest rectangle in the histogram.

Key Insight:
- Use a monotonic stack to efficiently find the largest rectangle.
- The stack maintains indices of bars in increasing order of their heights.
- When a bar with smaller height is encountered, calculate areas for bars in the stack.

Algorithm:
1. Initialize an empty stack and a variable to track max area.
2. Iterate through each bar in the histogram:
   - While stack is not empty and current height < stack top height:
     - Pop from stack and calculate area with popped height.
     - Update max area if calculated area is larger.
   - Push current index to stack.
3. After processing all bars, pop remaining elements from stack and calculate areas.

Time Complexity: O(n) - Each element is pushed and popped at most once.
Space Complexity: O(n) - Stack space.

Example:
Input: [2,1,5,6,2,3]
Output: 10 (rectangle formed by bars of height 5 and 6)
*/

// Basic logic is - 
// use monotonic stack to save bar in incresing height. Keep adding till we get bar > stack.peek bar.
// everytime we found bar smaller than stack.peek(). That's the right boundry of bar in stack. left boundry for that bar is - index of one element smaller than that .. i.e. immediate left element index in stack. This left index may not be immediate one i.e. for index 4 at stack peek. it may not be index 3. It is because there might be taller bars in between which are resolved earlier. Resolve bar in stack i.e check height and width i.e right - left - 1. Right = current index, left = index at left of element in stack.  
// Continue checking and resolving stack element until current element is smaller than stack element. ie until current element is right boundry for bar mentioned in stack. 
// then push that current element in stack. as current element acted as right bountry for stack element and resolved them but current element itself is not yet resolved.
// 2 catch points - 
// 1 - what stack get empty while resolving elementes in stack. How to find left boundry for last element in stack. Don't assume that last element from stack is the first element of array. It can be any inbetween element of array which has resolved all the elements to it's left before being added into stack. In such case all the element to the left of that last elements are certainly taller than last element OR it is the first element from array. In both the cases, safe way to calculate width is to use entire array length till current index. As for that last element popped from stack, current element is certainly smaller and act as right boundry and left boundry is all the way till start of array. so in this case width = current index.
// 2 - How to handle once array is finished but stack still has few elements. i.e. what if there are few last bars in stack for which there is no smaller bar in array to resolve them? i.e no right boundry available in array. But they can certainly form rectangle assuming end of array as right boundry. So we need to assume one last bar with height 0 which can resolve all elements from stack if there are any.

// eg - [6, 2, 5, 4, 5, 1, 6, 0]
// at 6 - push in stack {6}
// at 2 - pop 6, push 2 to stack {2}
// at 5 - push 5 to stack {2,5}
// at 4 - pop 5, push 4 to stack {2,4} 
// at 5 (index 4), push 5 {2,4,5}
// at 1 - pop 5, 4, 2 and push 1 {1}
// at 6 - push 6 {1,6}
// at 0 - virtually considered pop both 6 and 1

// at every stack pop - calculate width as - 
/*
if stack.isempty() after pop
    width = current index
else
    width = current index - stack.peek() - 1 // this peek is after pop so it's a left element in stack to the bar we are resolving
*/

// Stack stores array indices and not elements as we need these positions to calculate width

// Complexity 
// space - O(n)
// time - O(n) since every element is pushed and popped only once in stack. 

import java.util.ArrayDeque;
import java.util.Deque;

public class Q12LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = heights[0];

        for(int i = 0 ; i < heights.length; ++i) {
            result = calculateMaxArea(stack, heights, i, result);
            stack.push(i);
        }

        result = calculateMaxArea(stack, heights, heights.length, result); // corner case to process stack if not empty
        return result;
    }

    private int calculateMaxArea(
        Deque<Integer> stack,
        int[] heights,
        int i,
        int result
    ) {
        int maxArea = result;
        /* Using calculateHeight() for currentHeight is mandatory, 
        as for last case when array is finished but stack is not empty, 
        imaginary last index is introduced to process atll remaining elements if any. 
        last index = array.length so it will cause error if processed directly. 
        it's an imaginary index */
        int currentHeight = calculateHeight(heights, i); 
        
        while(!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
            int stkIndx = stack.pop();
            int height = calculateHeight(heights, stkIndx); // this is optional, we can also use heights[stkIndx]
            int width = calculateWidth(stack, i);
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    private int calculateHeight(int[] heights, int i) {
        if (i >= heights.length) {
            return 0; // this is mainly needed for last case, when array is finished but stack is not empty
        }

        return heights[i];
    }

    private int calculateWidth(Deque<Integer> stack, int i) {
        if(stack.isEmpty()) {
            return i; // current index
        }

        return i - stack.peek() - 1;
    }

    public static void main(String[] args) {
        Q12LargestRectangleInHistogram obj = new Q12LargestRectangleInHistogram();
        assert 10 == obj.largestRectangleArea(new int[]{2,1,5,6,2,3}); // 10
        assert 4 == obj.largestRectangleArea(new int[]{2,4}); // 4
        assert 12 == obj.largestRectangleArea(new int[]{6, 2, 5, 4, 5, 1, 6}); // 12

        System.out.println("All test cases passed!");
    }
}