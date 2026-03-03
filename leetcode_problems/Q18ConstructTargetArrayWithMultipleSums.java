// https://leetcode.com/problems/construct-target-array-with-multiple-sums/description/
// Hard problem
/*
You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
let x be the sum of all elements currently in your array.
Choose index i, such that 0 <= i < n and set the value of arr at index i to x.
You may repeat this procedure as many times as needed.
Return true if it is possible to construct the target array from arr, otherwise return false.

Example 1:
Input: target = [9,3,5]
Output: true

Example 2:
Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from arr.

Example 3:
Input: target = [8,5]
Output: true
*/

/*
We need to think reverse 
instead of [1,1,1] -> [9,3,5], we need to think [9,3,5] -> [1,1,1]

So we need to observe few importatn points -
[1,1,1] -> [1,3,1] -> [1,3,5] -> [9,3,5]

in reverse order - 
[9,3,5] -> [1,3,5] -> [1,3,1] -> [1,1,1]

To get previous step - 

largest of current step is sum of previous step, keeping remaining elements constant
eg - 9 is [9,3,5] is sum of previous step - [1,3,5]

So what element was there instead of 9 in previous step ? - say x
Let's take total of all elements as total

total - largest = sum of remaining elements for previous step - say rest

so largest = rest + x
x = largest - rest

for above example [9,3,5]

total = 17
rest = 17 - 9 = 8
x = 9 - 8 = 1

previous step = [1,3,5]

Assume that we have just 2 elements 
[100000, 1]

100000 - will be largest for all time and it be reduced in every step as
largest - rest --> largest - rest -> largest - rest .....
it's final step will be largest % rest

So for optimised version - 
x = largest % rest

Complete logic - 
- use max heap to get max element quickly
- add all elements ot heap and get it's total
- while(!heap.isEmpty()) {
    largest = heap.pop();

    if (largest == 1) {
        return true; // all elements in heap are 1. Initial input array has 1 <= target[i] <= 109
    }

    rest = total - largest

    if (rest == 1) {
        return true; // this is edge case. It is only possible when there is [largest, 1] and in this case, we can always reach to [1,1]
    }

    if (rest >= largest) {
        return false; // this is not possible. largest is always > rest as largest is formed using rest + x and this x >= 1.
    }

    mod = largest % rest i.e. previous step element

    if (mod == 0) {
        return false; // largest is always > rest as largest is formed using rest + x and this x >= 1. So if we get 0 it means somewhere 
        // down the line we will get [0] But our initial stage has all 1. MEans this array is created using [1,1,1] and rules of problem
    }

    }

    return true;
}


*/

// Complexity Analysis
/*
Time Complexity: O(n log m) where n is the number of elements in target and m is the maximum element in target. 
This is because in worst case we can have all elements of target as m and we will be doing m operations to reduce it to 1. 
Each operation involves popping from the max heap which takes O(log n) time and we may do this up to m times in the worst case.
Space Complexity: O(n) for the max heap.   
*/


import java.util.PriorityQueue;

public class Q18ConstructTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>((a,b) -> Long.compare(b,a));
        long total = 0;

        for(int num : target) {
            maxHeap.offer((long) num);
            total += num;
        }

        while(!maxHeap.isEmpty()) {
            long largest = maxHeap.poll();
            if(largest == 1) {
                return true; // all elements of heap are 1. Required state.
            }

            long rest = total - largest;

            if (rest == 0) { // only one element in array, but it's not 1 test case [2]
                return false;
            }

            if (rest == 1) {
                return true; // this is edge case. It is only possible when there is [largest, 1] and in this case, we can always reach to [1,1]
            }

            if(rest >= largest) {
                return false; // this is not possible. largest is always > rest as largest is formed using rest + x and this x >= 1.
            }

            long prev = largest % rest;

            if (prev == 0) { // case of rest = 1 is already handled above
                return false; // largest is always > rest as largest is formed using rest + x and this x >= 1. So if we get 0 it means somewhere 
        // down the line we will get [0] But our initial stage has all 1. MEans this array is created using [1,1,1] and rules of problem
            }

            maxHeap.offer(prev);
            total = rest + prev; // total of current step is rest + prev as largest is replaced by prev in current step
        }

        return true;
    }

    public static void main(String[] args) {
        Q18ConstructTargetArrayWithMultipleSums obj = new Q18ConstructTargetArrayWithMultipleSums();
        int[] target1 = {9,3,5};
        //System.out.println(obj.isPossible(target1));
        assert obj.isPossible(target1) == true; // Output: true

        int[] target2 = {1,1,1,2};
        //System.out.println(obj.isPossible(target2));
        assert obj.isPossible(target2) == false; // Output: false

        int[] target3 = {8,5};
        assert obj.isPossible(target3) == true; // Output: true

        int[] target4 = {2};
        assert obj.isPossible(target4) == false; // Output: false
    }
}


