package hashtable.simplehashing;

import java.util.HashSet;

/*
You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.

Letters are case sensitive, so "a" is considered a different type of stone from "A".



Example 1:

Input: jewels = "aA", stones = "aAAbbbb"
Output: 3
Example 2:

Input: jewels = "z", stones = "ZZ"
Output: 0


Constraints:

1 <= jewels.length, stones.length <= 50
jewels and stones consist of only English letters.
All the characters of jewels are unique.
 */
public class JewelsAndStones {
    public static int numJewelsInStones(String jewels, String stones) {
        // Use a HashSet to store jewel types for O(1) lookup
        HashSet<Character> jewelSet = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewelSet.add(c);
        }

        // Count how many stones are jewels
        int count = 0;
        for (char c : stones.toCharArray()) {
            if (jewelSet.contains(c)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Example 1
        String jewels1 = "aA";
        String stones1 = "aAAbbbb";
        System.out.println("Output: " + numJewelsInStones(jewels1, stones1)); // Output: 3

        // Example 2
        String jewels2 = "z";
        String stones2 = "ZZ";
        System.out.println("Output: " + numJewelsInStones(jewels2, stones2)); // Output: 0
    }
}
