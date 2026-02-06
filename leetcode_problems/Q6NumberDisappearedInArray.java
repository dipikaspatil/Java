/*
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

 

Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:

Input: nums = [1,1]
Output: [2]
 

Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
 

Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
*/

/*
1. Create an array of size n to mark presence of numbers in input array.
2. Mark presence of each number in input array in the seen array.
3. Get numbers which are not present in input array.
Time Complexity: O(n)
Space Complexity: O(n)
*/

/* Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
1. Mark presence of each number in input array by negating the value at index of that number.
2. Get numbers which are not present in input array, those will be the numbers at index which value is positive.
Time Complexity: O(n)
Space Complexity: O(1)

// Step 1: mark visited indices
for (int i = 0; i < nums.length; i++) {
    int idx = Math.abs(nums[i]) - 1;
    if (nums[idx] > 0) {
        nums[idx] = -nums[idx];
    }
}

// Step 2: collect missing numbers
for (int i = 0; i < nums.length; i++) {
    if (nums[i] > 0) {
        result.add(i + 1);
    }
}

return result;
*/
import java.util.ArrayList;
import java.util.List;

public class Q6NumberDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] seen = new int[nums.length];
        List<Integer> ans = new ArrayList<>();

        // Get count of numbers
        for(int i = 0; i < nums.length; ++i) {
            seen[nums[i]-1] += 1;
        }

        // Get numbers which are not present in input array
        for(int i = 0; i < seen.length; ++i) {
            if(seen[i] == 0) {
                ans.add(i+1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q6NumberDisappearedInArray obj = new Q6NumberDisappearedInArray();
        int[] nums = {4,3,2,7,8,2,3,1}; // Output: [5,6]
        List<Integer> ans = obj.findDisappearedNumbers(nums);
        for(int i : ans) {
            System.out.print(i + " ");
        }
    }
}
