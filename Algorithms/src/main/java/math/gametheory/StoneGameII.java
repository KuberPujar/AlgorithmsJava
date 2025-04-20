package math.gametheory;

import java.util.Arrays;

/*
Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.



Example 1:

Input: piles = [2,7,9,4,4]

Output: 10

Explanation:

If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
So we return 10 since it's larger.

Example 2:

Input: piles = [1,2,3,4,5,100]

Output: 104



Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 104
 */
public class StoneGameII {
    public static void main(String[] args) {
        int[] piles1 = {2, 7, 9, 4, 4};
        System.out.println(stoneGameII(piles1)); // Output: 10

        int[] piles2 = {1, 2, 3, 4, 5, 100};
        System.out.println(stoneGameII(piles2)); // Output: 104
    }

    private static int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        int[][] memo = new int[n][n + 1];

        // Calculate suffix sums
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Initialize memoization table with -1
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(piles, 0, 1, suffixSum, memo);
    }

    private static int dfs(int[] piles, int index, int M, int[] suffixSum, int[][] memo) {
        if (index >= piles.length) {
            return 0;
        }

        // If we've already computed this state, return it
        if (memo[index][M] != -1) {
            return memo[index][M];
        }

        int maxStones = 0;
        int total = suffixSum[index];

        // Try all possible X from 1 to 2M
        for (int X = 1; X <= 2 * M && index + X <= piles.length; X++) {
            // Current player takes X piles
            int current = suffixSum[index] - suffixSum[index + X];
            // Recursively compute what's left for the other player
            int nextM = Math.max(M, X);
            int remaining = dfs(piles, index + X, nextM, suffixSum, memo);
            // The current player gets current + (total - what the other player gets)
            maxStones = Math.max(maxStones, current + (total - current - remaining));
        }

        memo[index][M] = maxStones;
        return maxStones;
    }
}
