package strings.prefixsum;
/*
You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:

floor[i] = '0' denotes that the ith tile of the floor is colored black.
On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles. Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.

Return the minimum number of white tiles still visible.



Example 1:


Input: floor = "10110101", numCarpets = 2, carpetLen = 2
Output: 2
Explanation:
The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
Example 2:


Input: floor = "11111", numCarpets = 2, carpetLen = 3
Output: 0
Explanation:
The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
Note that the carpets are able to overlap one another.


Constraints:

1 <= carpetLen <= floor.length <= 1000
floor[i] is either '0' or '1'.
1 <= numCarpets <= 1000
 */
public class MinimumWhiteTilesAfterCoveringWithCarpets {
    public static void main(String[] args) {
        String floor = "10110101";
        int numCarpets = 2;
        int carpetLen = 2;
        System.out.println(minimumWhiteTiles(floor, numCarpets, carpetLen));
    }

    private static int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        // Prefix sum array to count white tiles quickly
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + (floor.charAt(i - 1) == '1' ? 1 : 0);
        }

        // DP table: dp[i][j] = min white tiles visible for first i tiles using j carpets
        int[][] dp = new int[n + 1][numCarpets + 1];

        // Initialize DP table
        for (int i = 1; i <= n; i++) {
            dp[i][0] = prefix[i]; // No carpets used
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= numCarpets; j++) {
                // Option 1: Don't place carpet ending at i
                dp[i][j] = dp[i - 1][j] + (floor.charAt(i - 1) == '1' ? 1 : 0);

                // Option 2: Place carpet ending at i (if possible)
                if (i >= carpetLen) {
                    int covered = prefix[i] - prefix[i - carpetLen];
                    dp[i][j] = Math.min(dp[i][j], dp[i - carpetLen][j - 1]);
                } else {
                    // Can cover all tiles from start to i
                    dp[i][j] = Math.min(dp[i][j], 0);
                }
            }
        }

        return dp[n][numCarpets];
    }
}
