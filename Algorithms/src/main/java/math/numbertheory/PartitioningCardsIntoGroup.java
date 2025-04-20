package math.numbertheory;

import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array deck where deck[i] represents the number written on the ith card.

Partition the cards into one or more groups such that:

Each group has exactly x cards where x > 1, and
All the cards in one group have the same integer written on them.
Return true if such partition is possible, or false otherwise.



Example 1:

Input: deck = [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
Example 2:

Input: deck = [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.


Constraints:

1 <= deck.length <= 104
0 <= deck[i] < 104
 */
public class PartitioningCardsIntoGroup {
    public static void main(String[] args) {
        int[] deck = {1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println(hasGroupsSizeX(deck));

        int[] deck2 = {1, 1, 1, 2, 2, 2, 3, 3};
        System.out.println(hasGroupsSizeX(deck2));

        int[] deck3 = {1, 1, 1, 1, 2, 2, 2, 2};
        System.out.println(hasGroupsSizeX(deck3));

        int[] deck4 = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3};
        System.out.println(hasGroupsSizeX(deck4));

        int[] deck5 = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3};
        System.out.println(hasGroupsSizeX(deck5));

        int[] deck6 = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        System.out.println(hasGroupsSizeX(deck6));

        int[] deck7 = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4};
        System.out.println(hasGroupsSizeX(deck7));
    }

    private static boolean hasGroupsSizeX(int[] deck) {
        // Count frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : deck) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Find GCD of all frequencies
        int gcd = -1;
        for (int freq : frequencyMap.values()) {
            if (gcd == -1) {
                gcd = freq;
            } else {
                gcd = calculateGCD(gcd, freq);
            }
            // Early exit if GCD becomes 1
            if (gcd == 1) {
                return false;
            }
        }

        return gcd >= 2;
    }

    // Helper method to calculate GCD using Euclidean algorithm
    private static int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
