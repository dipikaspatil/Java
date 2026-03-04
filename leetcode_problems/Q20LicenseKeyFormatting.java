// https://leetcode.com/problems/license-key-formatting/
// Complexity - Easy
/*
You are given a license key represented as a string S which consists only alphanumeric character and dashes. 
The string is separated into N+1 groups by N dashes. Given a number K, we would want to reformat the strings 
such that each group contains exactly K characters, except for the first group which could be shorter than K, 
but still must contain at least one character.  
The dashes must be inserted between groups and will not be used as a separator for the groups.
Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4
Output: "5F3Z-2E9W"
Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

Example 2:
Input: S = "2-5g-3-J", K = 2
Output: "2-5G-3J"
Explanation: The string S has been split into three parts, each part has 2 characters except
the first part as it could be shorter as mentioned above.

Note that the dashes are not needed and can be removed.
Note:
The length of string S will not exceed 12,000, and K is a positive integer.
String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
String S is non-empty.
*/

/* Complexity:
Time complexity: O(n) where n is the length of the input string S. 
We need to iterate through the string once.
Space complexity: O(n) where n is the length of the input string S. 
We use a StringBuilder to store the result.
*/

public class Q20LicenseKeyFormatting {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder(s.length());
        int counter = 0;

        for(int i = s.length() - 1; i >= 0; --i) {
            Character c = s.charAt(i);
            if(c == '-') {
                continue;
            }

            if(counter == k) {
                sb.append("-");
                counter = 0;
            }

            sb.append(Character.toUpperCase(s.charAt(i)));

            ++counter;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Q20LicenseKeyFormatting obj = new Q20LicenseKeyFormatting();
        System.out.println(obj.licenseKeyFormatting("5F3Z-2e-9-w", 4)); // "5F3Z-2E9W"
        System.out.println(obj.licenseKeyFormatting("2-5g-3-J", 2)); // "2-5G-3J"
        System.out.println(obj.licenseKeyFormatting("--a-a-a--", 2)); // "A-AA"
    }   
}
