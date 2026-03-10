import java.util.*;

/*
Problem: Ways to Make a Fair Array
URL: https://leetcode.com/problems/ways-to-make-a-fair-array/?envType=problem-list-v2&envId=dsa-association-slope-prefix-sum

Time Complexity: O(n)
Space Complexity:
*/

/*
Prefix sum idea (the trick)

We track four things while scanning:

leftEven
leftOdd
rightEven
rightOdd

Initially we compute:

rightEven = sum of all even indices
rightOdd  = sum of all odd indices

While iterating

For index i, imagine we remove nums[i].

Then:

Elements BEFORE i
Stay same index.
leftEven
leftOdd


Elements AFTER i
Their parity switches.

even → odd
odd → even

So:

newEven = leftEven + rightOdd
newOdd  = leftOdd + rightEven
*/


public class LC1664_WaysToMakeAFairArray {
    public static int waysToMakeFair(int[] nums) {
        int rightEven = 0; 
        int rightOdd = 0;
        int leftEven = 0;
        int leftOdd = 0;
        int count = 0;

        for(int i = 0; i < nums.length; ++i) {
            if(i % 2 == 0) rightEven += nums[i];
            else rightOdd += nums[i];
        }

        for(int i = 0; i < nums.length; ++i) {
            if(i % 2 == 0) rightEven -= nums[i];
            else rightOdd -= nums[i];

            if(leftOdd + rightEven == rightOdd + leftEven) {
                ++count;
            }

            if(i % 2 == 0) leftEven += nums[i];
            else leftOdd += nums[i];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 1, 6, 4}; // expected 1
        int[] test2 = {1, 1, 1};    // expected 3

        System.out.println(waysToMakeFair(test1));
        System.out.println(waysToMakeFair(test2));
    }
}