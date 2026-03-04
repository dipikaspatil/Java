// https://leetcode.com/problems/repeated-substring-pattern/
// Complexity - Easy
/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters 
only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

/*
💡 Clever Trick (O(n))

You can solve this in linear time using a surprising insight:

If you double the string (i.e., s + s) and then remove the first and last characters, the original string will be present as a substring only if s can be formed by repeating a smaller substring.
*/

/* Complexity:
Time complexity: O(n) where n is the length of the input string s. 
We create a new string that is twice the length of s and check for the presence of s in it.
Space complexity: O(n) where n is the length of the input string s.
We create a new string that is twice the length of s.
*/

public class Q21RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        // Double the string
        String doubled = s + s;
        // Remove first and last character
        String cut = doubled.substring(1, doubled.length()-1);
        // Check if original appears in the modified doubled string
        return cut.contains(s);
    }

    public static void main(String[] args) {
        Q21RepeatedSubstringPattern obj = new Q21RepeatedSubstringPattern();
        System.out.println(obj.repeatedSubstringPattern("abab")); // true
        System.out.println(obj.repeatedSubstringPattern("aba")); // false
        System.out.println(obj.repeatedSubstringPattern("abcabcabcabc")); // true
    }
}
