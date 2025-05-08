package dynamicprmng;

import java.util.Arrays;
import java.util.List;

/*
You're a daring jewel thief infiltrating the vault of the century, protected by a complex combination lock. Each dial holds a glittering gem, their values encoded in integers (represented by the nums array).
To crack the lock, you need to arrange the gems in a specific order: for each adjacent pair, either the first gem's value must divide evenly into the second's, or vice versa. But cracking such a combination requires more than just brute force!

You're given an array of nums containing the values of the vault's gems (n distinct positive integers between 1 and 10^9).
Your mission is to find the total number of possible "special" arrangements of these gems, where every pair satisfies the divisibility requirement (i.e., forms a harmonious sequence). Since the number of special arrangements can be huge, you need to return the answer modulo 10^9 + 7 to avoid numerical overflows.

Input/Output Format:
Input:
An array nums containing the gem values separated by spaces or commas.

Output:
Return a single integer representing the total number of special gem arrangements modulo 10^9 + 7.

Examples:
Input:
2 3 6 (Three gems in the vault)

Output:
2 (Only two arrangements work: [3 6 2] and [2 6 3])

Input:
1 4 3 (Another vault to plunder)

Output:
2 (Similar to the first example, only two special arrangements exist: [3 1 4] and [4 1 3])

Constraints:
2 <= n <= 14 (Number of gems in the vault)
1 <= nums[i] <= 10^9 (Individual gem value)
 */
public class SpecailArrangements {
    private static final int MOD = 1000000007;

    public static int countSpecialArrangements(List<Integer> nums) {
        int n = nums.size();
        // Convert List to array for easier access
        int[] numsArray = nums.stream().mapToInt(i -> i).toArray();

        // dp[mask][last] = number of ways when we've used numbers marked by 'mask' and last number was 'last'
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total = (total + dfs(numsArray, 1 << i, i, dp)) % MOD;
        }
        return total;
    }

    private static int dfs(int[] nums, int mask, int last, int[][] dp) {
        if (mask == (1 << nums.length) - 1) {
            return 1; // All numbers used - valid arrangement
        }

        if (dp[mask][last] != -1) {
            return dp[mask][last]; // Return memoized result
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) == 0) { // If number not used yet
                if (nums[last] % nums[i] == 0 || nums[i] % nums[last] == 0) {
                    res = (res + dfs(nums, mask | (1 << i), i, dp)) % MOD;
                }
            }
        }

        dp[mask][last] = res;
        return res;
    }

    public static void main(String[] args) {
        // Test cases using List<Integer>
        List<Integer> nums1 = List.of(2, 3, 6);
        System.out.println(countSpecialArrangements(nums1)); // Output: 2

        List<Integer> nums2 = List.of(1, 4, 3);
        System.out.println(countSpecialArrangements(nums2)); // Output: 2

        List<Integer> nums3 = List.of(1, 2, 4, 8);
        System.out.println(countSpecialArrangements(nums3)); // Output: 8

        List<Integer> nums4 = List.of(1, 2, 3);
        System.out.println(countSpecialArrangements(nums4)); // Output: 2
    }
}
