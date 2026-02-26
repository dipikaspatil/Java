// https://leetcode.com/problems/time-needed-to-buy-tickets/
/*
There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.

You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].

Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.

Return the time taken for the person initially at position k (0-indexed) to finish buying tickets.

 

Example 1:

Input: tickets = [2,3,2], k = 2

Output: 6

Explanation:

The queue starts as [2,3,2], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [3,2,1] at 1 second.
Continuing this process, the queue becomes [2,1,2] at 2 seconds.
Continuing this process, the queue becomes [1,2,1] at 3 seconds.
Continuing this process, the queue becomes [2,1] at 4 seconds. Note: the person at the front left the queue.
Continuing this process, the queue becomes [1,1] at 5 seconds.
Continuing this process, the queue becomes [1] at 6 seconds. The kth person has bought all their tickets, so return 6.
Example 2:

Input: tickets = [5,1,1,1], k = 0

Output: 8

Explanation:

The queue starts as [5,1,1,1], where the kth person is underlined.
After the person at the front has bought a ticket, the queue becomes [1,1,1,4] at 1 second.
Continuing this process for 3 seconds, the queue becomes [4] at 4 seconds.
Continuing this process for 4 seconds, the queue becomes [] at 8 seconds. The kth person has bought all their tickets, so return 8.
 

Constraints:

n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
*/

/*
This problem can be solved using queue. By saving array index in queue. But it will be time 
and space consuming. 

On the contrary, it can also be solved by using below logic - 

K = tickets[k] - tickets at K position
i = tickets[i] - tickets at K position

- any person who is ahead of K, will max K number of turns before it consumes all tickets
- any person who is ahead of K, will max K-1 number of turns before it consumes all tickets. Because last ticket of K will make it last round.
- Any person who is ahead or behind can consume max K seconds OR less. Less only if that person wants to buy less number of tickets than person at position K

- So total time is - 
For person ahead min(K, i) // whichever is less
For person behind min(K-1, i) // 1 less round than k, whichever is less
For person at k total K seconds

Note - There will be exact K rounds to buy K tickets 
*/
public class Q14TimeNeededToBuyTickets {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int result_time = 0;
        for (int i = 0; i < tickets.length; ++i) {
            if(i < k) {
                result_time += Math.min(tickets[i], tickets[k]);
            } else if (i == k) {
                result_time += tickets[k];
            } else {
                result_time += Math.min(tickets[i], tickets[k] - 1);
            }
        }

        return result_time;
    }

    public static void main(String[] args) {
        Q14TimeNeededToBuyTickets obj = new Q14TimeNeededToBuyTickets();
        assert obj.timeRequiredToBuy(new int[]{2,3,2}, 2) == 6; // 6
        assert obj.timeRequiredToBuy(new int[]{5,1,1,1}, 0) == 8; // 8
    }
    
}
