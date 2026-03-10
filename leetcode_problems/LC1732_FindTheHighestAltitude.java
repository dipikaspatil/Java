import java.util.*;

/*
Problem: Find the Highest Altitude
URL: https://leetcode.com/problems/find-the-highest-altitude/

Time Complexity:
Space Complexity:
*/

public class LC1732_FindTheHighestAltitude {
    public static int largestAltitude(int[] gains) {
        int currentAltitude = 0;
        int maxAltitude = 0;

        for(int gain : gains) {
            currentAltitude += gain;
            maxAltitude = Math.max(currentAltitude, maxAltitude);
        }

        return maxAltitude;
    }

    public static void main(String[] args) {
        int[] gains1 = {-5, 1, 5, 0, -7}; // Example 1 -> expected 1
        int[] gains2 = {-4, -3, -2, -1, 4, 3, 2}; // Example 2 -> expected 0

        System.out.println("Output 1: " + largestAltitude(gains1));
        System.out.println("Output 2: " + largestAltitude(gains2));
    }
}