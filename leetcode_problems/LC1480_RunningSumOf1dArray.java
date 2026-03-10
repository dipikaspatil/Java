import java.util.*;

/*
 Problem: Running Sum of 1d Array
 URL: https://leetcode.com/problems/running-sum-of-1d-array/description/

 Time Complexity:
 Space Complexity:
*/

public class LC1480_RunningSumOf1dArray {
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = nums[0];
        for(int i = 1; i < nums.length; ++i) {
            result[i] = result[i-1] + nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        LC1480_RunningSumOf1dArray sol = new LC1480_RunningSumOf1dArray();
        int[][] tests = {
            {1, 2, 3, 4},    // expected: [1,3,6,10]
            {1, 1, 1, 1, 1}  // expected: [1,2,3,4,5]
        };
        for (int[] t : tests) {
            try {
                int[] res = sol.runningSum(t);
                System.out.println(Arrays.toString(res));
            } catch (UnsupportedOperationException e) {
                System.out.println("Solution not implemented. Paste your solution into runningSum method.");
            }
        }
    }
}