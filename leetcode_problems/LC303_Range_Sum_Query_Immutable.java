import java.util.*;

/*
Problem: Range Sum Query - Immutable
URL: https://leetcode.com/problems/range-sum-query-immutable/

Time Complexity:
Space Complexity:
*/

/* Key trick:
Prefix sum category of problems.
sum(left, right) =
prefix[right + 1] - prefix[left]
*/


public class LC303_Range_Sum_Query_Immutable {

    // NumArray class for Range Sum Query - Immutable
    static class NumArray {
        private int[] prefix;

        public NumArray(int[] nums) {
            prefix = new int[nums.length+1];

            for(int i = 0 ; i < nums.length; ++i) {
                prefix[i+1] = prefix[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }

    public static void main(String[] args) {
        // Example tests from problem
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        try {
            NumArray obj = new NumArray(nums);
            System.out.println(obj.sumRange(0, 2)); // expected 1
            System.out.println(obj.sumRange(2, 5)); // expected -1
            System.out.println(obj.sumRange(0, 5)); // expected -3
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}