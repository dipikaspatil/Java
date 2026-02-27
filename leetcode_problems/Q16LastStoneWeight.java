// https://leetcode.com/problems/last-stone-weight/
/*
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

 

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
Example 2:

Input: stones = [1]
Output: 1
*/

/*
We are adding stones in max heap and taking 2 stones at a time
Check their weight and if not same add their difference to heap. Continue process till heap is empty or have only 1 stoen left
*/

/*
Complexity Analysis
Time Complexity: O(n log n) where n is the number of stones. This is because we are adding all the stones to the max heap and then performing operations on it. 
Every time we poll from the heap, it takes O(log n) time and we may do this up to n times in the worst case.
Space Complexity: O(n) for the max heap that we are using to store the stones.
*/

import java.util.PriorityQueue;

public class Q16LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);

        // Add all stones to max heap
        for(int stone : stones) {
            maxHeap.offer(stone);
        }

        while(maxHeap.size() > 1) {
            int heaviestStone = maxHeap.poll();
            int secondStone = maxHeap.poll();

            if(heaviestStone != secondStone) {
                maxHeap.offer(heaviestStone - secondStone);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();

    }

    public static void main(String[] args) {
        Q16LastStoneWeight obj = new Q16LastStoneWeight();
        int[] stones = {2,7,4,1,8,1};
        assert obj.lastStoneWeight(stones) == 1; // Output: 1

        int[] stones2 = {1};
        assert obj.lastStoneWeight(stones2) == 1; // Output: 1
    }
}
