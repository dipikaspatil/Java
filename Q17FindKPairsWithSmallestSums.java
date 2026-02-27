// 373. Find K Pairs with Smallest Sums
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
A pair (u, v) is defined as consisting of one element from the first array and one element from the second array.
Return the k pairs (u1, v1), (u2, v2), ...,
(u k, v k) with the smallest sums.

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
Input: nums1 = [1,1,2], nums2 = [1,2], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[1,2],[2,1],[2,2] 
Example 3:
Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence:
             [1,3],[2,3]
*/

/*
To solve this problem, we need min heap for sure as it will keep track of smallest.

What should be head structure ? 
- Out heap should sort based on sum of pair. But we also need pair itself in result.
- There can be duplicate numbers in array, so we cannot actually save numbers. We have to save coordinates.
- So in short, head will save int[] with 3 numbers - sum, i, j i.e. sum of pair and it's coordinates

How to add pairs in heap so that we can get exact k correct pairs without adding all pairs?
- On key point - our arrays are sorted.
- So nums1[0] and nums2[0] will be smallest - starting pair for heap
- We need to add in heap (expand) from smallest sum pair from heap and expand by 1 i.e. [i+1,j] and [i,j+1]
- For next turn - heap will give us global smallest and we will again expand from there

Do we need set to track visited pairs? To avoid duplicates
- Yes, that is because, when we expand with smallest from heap, it is possible that it's neighbour is already added in heap. 
eg - 
Heap top element    Neigbors added to heap
[i,j] ->            [i+1,j] [i, j+1]
[i,j+1] ->          [i,j+2], [i+1,j+1]
[i+1,j] ->          [i+1,j+1], [i+2, j+2] --> here [i+1,j+1] is duplicate so we don't want to add it again in heap

- Easiest structure to keep track of duplicated or visited pair is set<String> - String will hold co-ordinates "i-j"

Example - 
nums1 = [1,7,11], nums2 = [2,4,6], k = 3

smallest pair - [1,2] Heap - [1+2, 0, 0] [sum, i, j] - Result = [1,2]

- Expand from smallest from heap - [1,2] -> [1,4] [7,2] - Heap [1+4, 0, 1] [7+2, 1, 0] - Result = [1,2]
- Expand from smallest from heap - [1,4] -> [1,6] [7,4] - Heap [1+6, 0, 2] [7+2, 1, 0] [7+4, 1, 1] - Result = [1,2] [1,4]
- Expand from smallest from heap - [1,6] -> [7,6] - Heap [7+2, 1, 0] [7+4, 1, 1] [7+6, 1, 2] - Result = [1,2] [1,4] [1,6] - done found 3 smallest pair in result
*/

/*
Complexity Analysis
Time Complexity: O(k log k) where k is the number of pairs we need to find. 
This is because we are adding pairs to the min heap and performing operations on it. 
Every time we poll from the heap, it takes O(log k) time and we may do this up to k times in the worst case.

Space Complexity: O(k) for the min heap and the set that we are using to store the pairs.   
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Q17FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // priority queue will store array of 3 numbers - [sum, i, j] 
        // sum = nums[i] + nums[j], i - index of nums1 and j - index of nums2
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[0],b[0])
        ); // we can also use (a,b) -> a[0] - b[0] But for Integer.MAX_VALUE Integer overflow can happen. So risky.

        Set<String> set = new HashSet<>(); // to store coordinates "i-j" i - index of nums1 and j - index of nums2

        List<List<Integer>> result = new ArrayList<>();

        // Add smallest pair in heap i.e. nums1[0], nums2[0] as arrays are sorted
        minHeap.offer(new int[]{nums1[0]+nums2[0], 0, 0});

        // Add coordinates in set
        set.add("0-0");

        while(!minHeap.isEmpty() && result.size() < k) {
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Expand based coordinates of element popped from heap with smallest sum
            if(i+1 < nums1.length && !set.contains((i+1) + "-" + j)) {
                minHeap.offer(new int[]{nums1[i+1]+nums2[j], i+1, j});
                set.add((i+1) + "-" + j);
            }

            if(j+1 < nums2.length && !set.contains(i + "-" + (j+1))) {
                minHeap.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
                set.add(i + "-" + (j+1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Q17FindKPairsWithSmallestSums obj = new Q17FindKPairsWithSmallestSums();
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        assert obj.kSmallestPairs(nums1, nums2, k).equals(Arrays.asList(Arrays.asList(1,2), Arrays.asList(1,4), Arrays.asList(1,6))); // Output: [[1,2],[1,4],[1,6]]

        int[] nums3 = {1,1,2};
        int[] nums4 = {1,2};
        int k2 = 2;
        assert obj.kSmallestPairs(nums3, nums4, k2).equals(Arrays.asList(Arrays.asList(1,1), Arrays.asList(1,1))); // Output: [[1,1],[1,1]]

        int[] nums5 = {1,2};
        int[] nums6 = {3};
        int k3 = 3;
        assert obj.kSmallestPairs(nums5, nums6, k3).equals(Arrays.asList(Arrays.asList(1,3), Arrays.asList(2,3))); // Output: [[1,3],[2,3]] 
    }
}

/* Can we avoid set completely?
Yes, we can. It is called K way merged. 
We can add all pairs with nums1 and first element of nums2. For eny element of nums1, smallest sum will always be with first element of nums2

so while expanding, we just have to increase i, j+1
*/

/*
public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // priority queue will store array of 3 numbers - [sum, i, j] 
        // sum = nums[i] + nums[j], i - index of nums1 and j - index of nums2
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[0],b[0])
        ); // we can also use (a,b) -> a[0] - b[0] But for Integer.MAX_VALUE Integer overflow can happen. So risky.

        List<List<Integer>> result = new ArrayList<>();

        // Initialize heap with nums[i], 0
        for(int i = 0; i < Math.min(k, nums1.length); ++i) {
            minHeap.offer(new int[]{nums1[i]+nums2[0], i, 0});
        }

        while(!minHeap.isEmpty() && result.size() < k) {
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // Expand only in the direction of j+1 as i+1 is already added
            if(j+1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
            }
        }

        return result;
    }
*/