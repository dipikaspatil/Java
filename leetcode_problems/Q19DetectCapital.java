// https://leetcode.com/problems/detect-capital/
// Complexity - Easy
/*
Given a word, you need to judge whether the usage of capitals in it is right or not. We define the usage of capitals in a word to be right when one of the following cases holds:
1. All letters in this word are capitals, like "USA".
2. All letters in this word are not capitals, like "leetcode".
3. Only the first letter in this word is capital, like "Google". Otherwise, we define that this word doesn't use capitals in a right way.   
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
*/
/* Complexity: 
Time complexity: O(n) where n is the length of the input word. We need to iterate through the word once to count the uppercase letters.

Space complexity: O(1) as we only use a constant amount of extra space.
*/

public class Q19DetectCapital {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        int countUppercase = 0;

        // count uppercase letters
        for(int i = 0; i < word.length(); ++i) {
            if(Character.isUpperCase(word.charAt(i))) {
                ++countUppercase;
            }
        }

        // valid if all lowercase, all uppercase, 
        // or only first letter is uppercase
        return countUppercase == 0 ||
        countUppercase == n ||
        (countUppercase == 1 && Character.isUpperCase(word.charAt(0)));
    }
    
    public static void main(String[] args) {
        Q19DetectCapital obj = new Q19DetectCapital();
        System.out.println(obj.detectCapitalUse("USA")); // true
        System.out.println(obj.detectCapitalUse("FlaG")); // false
    }
}
