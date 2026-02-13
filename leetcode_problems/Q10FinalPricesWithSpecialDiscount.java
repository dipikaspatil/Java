
/*
📝 Summary — Final Prices with Special Discount (Monotonic Stack)

Problem:
For each price, find the first price to its right that is ≤ current price and apply it as a discount.

Key Idea:
Use a monotonic increasing stack to keep track of unresolved elements (indices whose discount hasn’t been found).

Why Stack:

Stack stores indices of unresolved elements.
LIFO order ensures nearest unresolved element is resolved first.
Resolved elements are popped; they never see future prices.
Queue or brute-force scanning would either break this order or be inefficient.

Algorithm:

Initialize stack (indices) and result = prices.clone().
Traverse prices left → right:
While stack not empty and prices[i] <= prices[stack.peek()]:
Pop index, apply discount: result[stack.pop()] -= prices[i].
Push i to stack.
Return result.

Complexity:

Time: O(n) → each element pushed and popped at most once i.e O(2n) → O(n)
Space: O(n) → stack + result array

Mental Invariant:

At any moment, stack contains unresolved elements in order, and most recent unresolved element must be resolved first.
*/
/*
✅ Mental shortcut
Use stack when you want to resolve previous elements efficiently based on current element, especially if the “nearest unresolved first” matters.
*/

import java.util.ArrayDeque;
import java.util.Deque;

class Q10FinalPricesWithSpecialDiscount {
    public int[] finalPrices(int[] prices) {
        int[] result = prices.clone();
        /* Stack to save indices of unresolved prices. 
        Resolve most recent first as compared to current element 
        eg - for [8,4,6,2,3] when at 2, resolve 6 first. 
        Otherwise it will break for example like [10, 6, 8, 7, 5]
        */
        Deque<Integer> stack = new ArrayDeque<>(); 

        for(int i = 0 ; i < prices.length; ++i) {
            applyDiscount(prices, result, stack, i);
            stack.push(i); // discount is applied to previous elements in stack. vurrent element is unresolved
        }
        return result;
    }

    private int[] applyDiscount(
        int[] prices,
        int[] result,
        Deque<Integer> stack,
        int i
    ) {
        while(!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
            // apply discount
            int stk_indx = stack.pop();
            result[stk_indx] -= prices[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Q10FinalPricesWithSpecialDiscount solution = new Q10FinalPricesWithSpecialDiscount();
        int[] prices = {8, 4, 6, 2, 3};
        int[] finalPrices = solution.finalPrices(prices); // Output should be [4, 2, 4, 2, 3]
        for (int price : finalPrices) {
            System.out.print(price + " ");
        }
        System.out.println();

        prices = new int[]{10, 6, 8, 7, 5};
        finalPrices = solution.finalPrices(prices); // Output should be [4, 1, 1, 2, 5]
        for (int price : finalPrices) {
            System.out.print(price + " ");
        }
        System.out.println();
    }
}