// https://leetcode.com/problems/daily-temperatures/description/
/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
*/

import java.util.ArrayDeque;
import java.util.Deque;

public class Q11DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        
        Deque<Integer> stack = new ArrayDeque<>(); // save array indices

        for(int i = 0; i < temperatures.length; ++i) {
            // Check if temeprature at i th position is greter that the one available in stack
            countDayToGetWarmerTemperature(
                stack,
                temperatures,
                i,
                result
            );
            stack.push(i); // event though i is used to resolve stack element, i iteslf is not yet resolved. So push the same in stack
        }

        return result;

    }

    private int[] countDayToGetWarmerTemperature(
        Deque<Integer> stack,
        int[] temperatures,
        int i,
        int[] result
    ) {
        // Note - stack only stores indices
        while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
            // resolve stack element
            int stk_indx = stack.pop();
            result[stk_indx] = i - stk_indx;
        }

        return result;
    }

    public static void main(String[] args) {
        Q11DailyTemperatures solution = new Q11DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solution.dailyTemperatures(temperatures); // Output should be [1,1,4,2,1,1,0,0]
        for (int res : result) {
            System.out.print(res + " ");
        }
        System.out.println();

        temperatures = new int[]{30, 40, 50, 60};
        result = solution.dailyTemperatures(temperatures); // Output should be [1,1,1,0]
        for (int res : result) {
            System.out.print(res + " ");
        }
        System.out.println();

        temperatures = new int[]{30, 60, 90};
        result = solution.dailyTemperatures(temperatures); // Output should be [1,1,0]
        for (int res : result) {
            System.out.print(res + " ");
        }
        System.out.println();
    }
}
