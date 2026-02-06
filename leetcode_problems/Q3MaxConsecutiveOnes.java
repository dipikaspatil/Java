/*
Given a binary array nums, return the maximum number of consecutive 1's in the array.

 

Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2
 
Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
*/

class Q3MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int curr_cnt = 0;
        int max_cnt = 0;

        for (int i = 0; i < nums.length; ++i) {
            if(nums[i] == 1) {
                curr_cnt += 1;
            } else {
                max_cnt = Math.max(max_cnt, curr_cnt);
                curr_cnt = 0;
            }
        }

        return Math.max(curr_cnt, max_cnt);
    }
    
    public static void main(String[] args) {
        Q3MaxConsecutiveOnes solution = new Q3MaxConsecutiveOnes();
        int[] nums = {1,1,0,1,1,1};
        int result = solution.findMaxConsecutiveOnes(nums);
        System.out.println(result); // Output: 3
    }
}