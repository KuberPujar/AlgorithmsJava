package arrays.dpbased;

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
        int[] piles = {2, 7, 9, 4, 4};
        System.out.println(stoneGameII(piles)); // Output: 10
        piles = new int[]{1, 2, 3, 4, 5, 100};
        System.out.println(stoneGameII(piles)); // Output: 104
    }

    private static int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][][] dp = new int[n][n + 1][2]; // dp[i][m][0] for Alice, dp[i][m][1] for Bob

        // Initialize the DP table
        for (int i = 0; i < n; i++) {
            for (int m = 0; m <= n; m++) {
                Arrays.fill(dp[i][m], -1);
            }
        }

        return dfs(piles, 0, 1, 0, dp);
    }

    private static int dfs(int[] piles, int index, int m, int player, int[][][] dp) {
        if (index >= piles.length) {
            return 0;
        }

        if (dp[index][m][player] != -1) {
            return dp[index][m][player];
        }

        int maxStones = (player == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentSum = 0;

        for (int x = 1; x <= 2 * m; x++) {
            if (index + x > piles.length) {
                break;
            }

            currentSum += piles[index + x - 1];

            if (player == 0) { // Alice's turn - maximize her stones
                int next = dfs(piles, index + x, Math.max(m, x), 1, dp);
                maxStones = Math.max(maxStones, currentSum + next);
            } else { // Bob's turn - minimize Alice's stones (since Bob plays optimally)
                int next = dfs(piles, index + x, Math.max(m, x), 0, dp);
                maxStones = Math.min(maxStones, next);
            }
        }

        dp[index][m][player] = maxStones;
        return maxStones;
    }

    //optimized approach
    public int stoneGameIII(int[] piles) {
        int totalPiles = piles.length;
        int[] suffixSums = new int[totalPiles + 1];
        for (int i = totalPiles - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + piles[i];
        }
        return maxStonesAliceCanGet(suffixSums, 1, 0, new int[totalPiles][totalPiles + 1]);
    }

    private int maxStonesAliceCanGet(int[] suffixSums, int m, int currentPile, int[][] memo) {
        int totalPiles = suffixSums.length - 1;

        if (currentPile >= totalPiles) return 0;

        if (currentPile + 2 * m >= totalPiles) {
            return suffixSums[currentPile];
        }

        if (memo[currentPile][m] != 0) return memo[currentPile][m];

        int maxStones = 0;

        for (int x = 1; x <= 2 * m; x++) {
            int currentStones = suffixSums[currentPile] - maxStonesAliceCanGet(suffixSums, Math.max(m, x), currentPile + x, memo);
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[currentPile][m] = maxStones;
        return maxStones;
    }
}
