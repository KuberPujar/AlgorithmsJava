package dynamicprmng;

import java.util.Arrays;

/*
Alice has coins of different denominations and he has to pay an amount to buy an item.
Return the fewest number of coins that Alice needs to make up that amount.
 If that amount of money cannot be made up by any combination of the coins, return -1.
 You may assume that you have an infinite number of each kind of coin.

Constraints:

1 <= coins.length <= 12

1 <= coins[i] <= 2^31 - 1

0 <= amount <= 10^4

Example:

Input:
coins = [1,2,5], amount = 11

Output:
3

Input:
coins = [2], amount = 3

Output:

-1

Note: The function should return the result.
 */
public class MinimumCoinsRequired_1 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins required: " + minCoins(coins, amount)); // Expected Output: 3

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Minimum coins required: " + minCoins(coins2, amount2)); // Expected Output: -1
    }

    private static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
