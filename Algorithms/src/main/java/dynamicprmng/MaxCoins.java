package dynamicprmng;
/*
MAX COINS -I
Nitin is an explorer, and he has a map that consists of n houses along a coastline of a beach. Each house has a certain amount of money stashed. The only constraint stopping Nitin from getting each of them is that adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money in each house, return the maximum amount of money Nitin can rob tonight without alerting the police.

Note: Try to see if you can solve using brute force first then optimize your solution

Input Format:

The first line contains an integer N representing the number of houses.
The second line contains N space-separated integers representing the amount of money in each house.
Output Format:

A single integer denoting the maximum amount of money Nitin can rob without alerting the police.
Sample Input 1:

4
1 2 3 1
Sample Output 1:

4
Explanation:

Nitin can rob the 1st and 3rd houses, obtaining a total of 1 + 3 = 4.

Sample Input 2:

5
2 7 9 3 1
Sample Output 2:

12
Explanation:

Nitin can rob the 1st, 3rd, and 5th houses, obtaining a total of 2 + 9 + 1 = 12.

Constraints:

(1 <= nums.length <= 10^7)
(0 <= nums[i] <= 400)
Note: The function should return the result.
 */
public class MaxCoins {

    // Optimized DP solution
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    // Space optimized DP solution (O(1) space)
    public static int robOptimized(int[] nums) {
        int prev1 = 0; // dp[i-1]
        int prev2 = 0;  // dp[i-2]

        for (int num : nums) {
            int current = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(rob(nums1)); // Output: 4
        System.out.println(robOptimized(nums1)); // Output: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(rob(nums2)); // Output: 12
        System.out.println(robOptimized(nums2)); // Output: 12
    }
}