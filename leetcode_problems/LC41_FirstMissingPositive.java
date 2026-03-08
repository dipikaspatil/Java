import java.util.*;

// LC41_FirstMissingPositive.java
// Problem: First Missing Positive
// URL: https://leetcode.com/problems/first-missing-positive/?envType=problem-list-v2&envId=dsa-association-slope-hash
// Time Complexity: O(n) - we traverse the array a few times, but each traversal is O(n).
// Space Complexity: O(1) - we are modifying the input array in place and using only a constant amount of extra space.

/*
/*
Algorithm Steps
Step 1 — Place numbers at correct index

While traversing the array:

If nums[i] is between 1 and n
and nums[nums[i] - 1] != nums[i]

swap them

This keeps moving numbers to their correct positions.

Step 2 — Find first mismatch

After rearranging:

index : 0 1 2 3
value : 1 2 3 5

Check:

if nums[i] != i + 1
return i + 1

Step 3 — If everything is correct

Return:

n + 1
*/
public class LC41_FirstMissingPositive {
    // PASTE YOUR SOLUTION HERE
    public int firstMissingPositive(int[] nums) {
        // Place numbers at correct index
        for(int i = 0; i < nums.length; ++i) {
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int num = nums[i];
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
            }
            
        }

        // Find first mismatch
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.length + 1;
    }

    public static void main(String[] args) {
        LC41_FirstMissingPositive sol = new LC41_FirstMissingPositive();
        int[] tc1 = {1, 2, 0};
        int[] tc2 = {3, 4, -1, 1};

        System.out.println("Output tc1: " + sol.firstMissingPositive(tc1)); // expected 3
        System.out.println("Output tc2: " + sol.firstMissingPositive(tc2)); // expected 2
    }
}