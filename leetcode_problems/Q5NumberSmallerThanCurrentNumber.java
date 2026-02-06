/*
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

 

Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation: 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1). 
For nums[3]=2 there exist one smaller number than it (1). 
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]
Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]
 

Constraints:

2 <= nums.length <= 500
0 <= nums[i] <= 100
*/

/*
Approach: Count Sort
1. Get count of each number in the input array, store it in cnt_arr.
2. Get prefix sum of count, sum of all numbers before respective number.
3. Get result using prefix sum - ans[i] = cnt_arr[nums[i]-1]
Time Complexity: O(n + k) where n is the length of input array and k is the range of numbers in input array (0 to 100)
Space Complexity: O(n + k) where n is the length of input array and k is the range of numbers in input array (0 to 100) 

🔍 Example Walkthrough

Input: [8,1,2,2,3]

Frequency array (partial)
count[1]=1
count[2]=2
count[3]=1
count[8]=1

Prefix sum
count[1]=1
count[2]=3
count[3]=4
count[8]=5

Result

8 → count[7] = 4

1 → count[0] = 0

2 → count[1] = 1

2 → count[1] = 1

3 → count[2] = 3

Output: [4,0,1,1,3] ✅
*/
public class Q5NumberSmallerThanCurrentNumber {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt_arr = new int[101]; //0 <= nums[i] <= 100
        int[] ans = new int[nums.length];
        // Get count of numbers
        for(int i = 0; i < nums.length; ++i) {
            cnt_arr[nums[i]] += 1;
        }

        // Get prefix sum of count, sum of all numbers before respective number
        for(int i = 1; i < cnt_arr.length; ++i) {
            cnt_arr[i] += cnt_arr[i-1];
        }

        // Get result using prefix sum - ans[i] = cnt_arr[nums[i]-1]]
        for(int i = 0; i < ans.length; ++i) {
            if(nums[i] == 0) {
                continue;
            }
            ans[i] = cnt_arr[nums[i]-1];
        }

        return ans;
    }

    public static void main(String[] args) {
        Q5NumberSmallerThanCurrentNumber obj = new Q5NumberSmallerThanCurrentNumber();
        int[] nums = {8,1,2,2,3}; // Output: [4,0,1,1,3]
        int[] ans = obj.smallerNumbersThanCurrent(nums);
        for(int i : ans) {
            System.out.print(i + " ");
        }

        int[] nums2 = {5,0,10,0,10,6}; // Output: [2,0,4,0,4,3]
        int[] ans2 = obj.smallerNumbersThanCurrent(nums2);
        System.out.println();
        for(int i : ans2) {
            System.out.print(i + " ");
        }
    }
}
