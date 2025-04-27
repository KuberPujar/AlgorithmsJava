package dynamicprmng;

import java.util.HashMap;
import java.util.Map;

/*
Stolen Gifts
A thief wants to steal gifts from a shop where gifts are arranged in a linear order. The thief's safety mechanism triggers an alarm unless he steals a precise number of gifts quantified by value, not by count. The task is to determine the number of unique ways the thief can combine the gifts to achieve exactly k value without triggering the alarm.

Objective:
Calculate the number of distinct combinations of gifts that sum up to exactly k.

Input Format:

The input consists of three lines:
  1. An integer n, the number of gifts.
  2. An integer k, the exact total value of gifts the thief must steal.
  3. A list of n integers, each representing the value of a gift.
Output Format:

An integer representing the number of unique combinations of gifts that sum up to exactly k.
Sample Inputs and Outputs:
Example 1:
Input:

5 4
1 2 1 2 3
Output:

3
Explanation: There are three unique ways to achieve a total gift value of 3:

Using the gifts with values [1, 2, 1]
Using the gifts with values [1, 3]
Using the gifts with values [2, 2]
Note:
The combination [2, 2] is counted as one unique way even though the values come from different positions in the array.

Example 2:
Input:

3 2
1 2 2
Output:

1
Explanation:
There is only one unique way to achieve a total gift value of 2using the gift with value[2]. The gift value of 2 appears twice in the list but contributes to only one unique combination for the sum.

Note:
Uniqueness: Each combination should be counted once, irrespective of the permutation of the values or if the same value appears multiple times in the gift list.
Combination, not Permutation: The order in which gifts are selected does not matter; only the sum is important.
Constraints:

1 <= n <= 100 where n is the number of gifts.

0 <= gifts[i] <= 1000 where gifts[i] is the value of the i-th gift.

1 <= k <= 1000 where k is the exact value of gifts the thief needs to steal to avoid triggering the alarm.

Note: The function should return the result.
 */
public class StolenGifts {
    public static void main(String[] args) {
        int n = 5, k = 4;
        int[] gifts = {1, 2, 1, 2, 3};
        System.out.println(countCombinations(gifts, n, k)); // Output: 3
    }

    public static int countCombinations(int[] gifts,int n, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int gift : gifts) {
            freq.put(gift, freq.getOrDefault(gift, 0) + 1);
        }

        int[] dp = new int[k + 1];
        dp[0] = 1; // Base case: one way to make sum 0 (select no gifts)

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int gift = entry.getKey();
            int count = entry.getValue();
            for (int i = k; i >= gift; i--) {
                for (int j = 1; j <= count && i - j * gift >= 0; j++) {
                    dp[i] += dp[i - j * gift];
                }
            }
        }

        return dp[k];
    }
}
