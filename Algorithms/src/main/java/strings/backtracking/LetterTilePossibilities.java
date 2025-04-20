package strings.backtracking;
/*
You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.



Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1


Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
 */
public class LetterTilePossibilities {
    public static void main(String[] args) {
        String tiles = "AAB";
        System.out.println(numTilePossibilities(tiles)); // Output: 8
    }

    private static int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        for (char c : tiles.toCharArray()) {
            count[c - 'A']++;
        }
        return backtrack(count);
    }

    private static int backtrack(int[] count) {
        int sum = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                // Take this letter
                sum++; // Count this single-letter sequence
                count[i]--;

                // Recurse for longer sequences
                sum += backtrack(count);

                // Backtrack
                count[i]++;
            }
        }

        return sum;
    }
}
