// https://leetcode.com/problems/rotate-string/
// Complexity - Easy
/*
We are given two strings, A and B.
A shift on A consists of taking string A and moving the leftmost character to the rightmost position. 
For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. 
Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:
A and B will have length at most 100.
A and B consist of lowercase letters.
*/

/*
💡 Key Idea

If s can be rotated to form goal, then all possible rotations of s appear as substrings in s + s.

So you can check:

Are the lengths equal?

Is goal a substring of s + s?
*/

/*  Complexity:
Time complexity: O(n) where n is the length of the input strings s and goal.
We create a new string that is twice the length of s and check for the presence of goal in it.
Space complexity: O(n) where n is the length of the input string s.
We create a new string that is twice the length of s.
 */
public class Q22RorateString {
    public boolean rotateString(String s, String goal) {
        // If lengths differ, rotation is impossible
        if(s.length() != goal.length()) {
            return false;
        }

        // Concatenate string to itself
        String doubled = s + s;

        // Check if goal is a substring of the doubled string
        return doubled.contains(goal);
    }

    public static void main(String[] args) {
        Q22RorateString obj = new Q22RorateString();
        System.out.println(obj.rotateString("abcde", "cdeab")); // true
        System.out.println(obj.rotateString("abcde", "abced")); // false
    }
}
