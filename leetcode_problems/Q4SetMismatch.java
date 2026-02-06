/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

 

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]

*/
import java.util.Arrays;

class Q4SetMismatch {
    public int[] findErrorNums(int[] nums) {
       int[] seen = new int[nums.length + 1];
       Arrays.fill(seen, -1);
       seen[0] = 0;

       int[] ans = new int[2];

       // Find the duplicate number
       for (int num : nums) {
        if(seen[num] == 1) {
            ans[0] = num;
        } else {
            seen[num] = 1;
        }
       }

       // Find the missing number
       for(int i = 1 ; i < seen.length; ++i) {
        if(seen[i] == -1) {
            ans[1] = i;
            break;
        }
       }

       return ans;
    }

    public static void main(String[] args) {
        Q4SetMismatch obj = new Q4SetMismatch();
        int[] nums = {1,2,2,4};
        int[] ans = obj.findErrorNums(nums);
        System.out.println(ans[0] + " " + ans[1]);
    }

}