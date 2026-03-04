// https://leetcode.com/problems/repeated-string-match/
// Complexity - Medium
/*
Given two strings A and B, find the minimum number of times A has to be repeated such
that B is a substring of it. If no such solution, return -1.
Example 1:
Input: A = "abcd", B = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating A three times (“abcdabcdabcd”), 
B is a substring of it. B is not a substring of A repeated two times (“abcdabcd”).

Example 2:
Input: A = "a", B = "aa"
Output: 2
Explanation: We return 2 because by repeating A twice (“aa”), B is a substring of it.
B is not a substring of A repeated once (“a”).

Example 3:
Input: A = "abc", B = "wxyz"
Output: -1
Explanation: We return -1 because B is not a substring of any string that can be formed
by repeating A any number of times.

Note:
The length of A and B will be between 1 and 10000.
A and B consist of lowercase letters.
*/

/* Complexity Analysis:
 * Time Complexity: O(n * m) where n is the length of A and m is the length of B.
 * Space Complexity: O(n + m) for the StringBuilder and the repeated string.
 */

public class Q23RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Repeat a until length >= b.length()
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        // Check if b is now a substring
        if (sb.toString().contains(b)) return count;

        // Append one more copy of a to handle overlap eg - a = abcdabcd and b = cdabcdab
        sb.append(a);
        if (sb.toString().contains(b)) 
            return count + 1;

        return -1; // impossible
    }

    public static void main(String[] args) {
        Q23RepeatedStringMatch obj = new Q23RepeatedStringMatch();
        System.out.println(obj.repeatedStringMatch("abcd", "cdabcdab")); // 3
        System.out.println(obj.repeatedStringMatch("a", "aa")); // 2
        System.out.println(obj.repeatedStringMatch("abc", "wxyz")); // -1
    }
}
