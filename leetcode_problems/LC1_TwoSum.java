import java.util.*;

// LC1_TwoSum.java
// Problem: Two Sum
// URL: https://leetcode.com/problems/two-sum/submissions/1938187322/?envType=problem-list-v2&envId=dsa-association-slope-hash
//
// Time Complexity: O(n) - we traverse the array once, where n is the number of elements in the array.
// Space Complexity: O(n) - we use a hash map to store the elements and their indices, which can take up to O(n) space.


public class LC1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; ++i) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i],i);
        }

        return new int[0]; // Return empty array if no solution is found, though the problem guarantees one solution exists.
    }

    public static void main(String[] args) {
        LC1_TwoSum sol = new LC1_TwoSum();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        print(sol.twoSum(nums1, target1)); // expected [0,1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        print(sol.twoSum(nums2, target2)); // expected [1,2]
    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}