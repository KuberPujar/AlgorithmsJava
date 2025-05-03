package dynamicprmng;
/*
Alice has coins of different denominations and he has to pay an amount to buy an item.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.

Example:

Input:

amount = 5, coins = [1,2,5]

Output:
4

Constraints:

1 <= coins.length <= 300

1 <= coins[i] <= 5000

All the values of coins are unique.

0 <= amount <= 5000

Note: The function should return the result.
 */
public class MinimumCoinsRequired_2 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Number of combinations: " + numCombinations(coins, amount)); // Expected Output: 4

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Number of combinations: " + numCombinations(coins2, amount2)); // Expected Output: 0
    }

    public static int numCombinations(int[] coins,int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // Base case: one way to make amount 0

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
