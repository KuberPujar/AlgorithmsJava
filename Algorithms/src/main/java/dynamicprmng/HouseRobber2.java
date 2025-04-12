package dynamicprmng;
/*
Max coins -II
Nitin is an explorer, and he has a map that consists of ( n ) number of houses. All houses at this place are arranged in a circle. Each house has a certain amount of money stashed.
The only constraint stopping Nitin from getting each of them is that adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money Nitin can rob tonight without alerting the police.

Note: Try to see if you can solve using brute force first then optimize your solution

Input Format:

The first line contains an integer ( n ) representing the number of houses.
The second line contains ( n ) space-separated integers representing the amount of money stashed in each house.
Output Format:

An integer representing the maximum amount of money Nitin can rob tonight without alerting the police.
Sample Input 1:

4
1 2 3 1
Sample Output 1:

4
Explanation 1:

Nitin can rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount = 1 + 3 = 4.

Sample Input 2:

5
2 7 9 3 1
Sample Output 2:

11
Explanation 2:

Nitin can rob house 1 (money = 2), rob house 3 (money = 9). Total amount = 2 + 9 = 11.

Sample Input 3:

7
6 7 1 3 8 2 4
Sample Output 3:

19
Explanation 3:

Nitin can rob house 2 (money = 7), house 5 (money = 8), and house 7 (money = 4). Total amount = 7 + 8 + 4 = 19.

Constraints:

( 1 <= nums.length <= 10^7 )
( 0 <= nums[i] <= 900 )
Note: The function should return the result.
 */
/*
Brute force solution

public class HouseRobberII {

    // Brute force recursive solution
    public static int robRecursive(int[] nums) {
        if (nums.length == 1) return nums[0];
        // We can either rob first house and skip last, or skip first and consider last
        return Math.max(
            helper(nums, 0, nums.length - 2),
            helper(nums, 1, nums.length - 1)
        );
    }

    private static int helper(int[] nums, int start, int end) {
        if (start > end) return 0;

        // Option 1: Rob current house and skip next
        int robCurrent = nums[start] + helper(nums, start + 2, end);

        // Option 2: Skip current house and consider next
        int skipCurrent = helper(nums, start + 1, end);

        return Math.max(robCurrent, skipCurrent);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(robRecursive(nums1)); // Output: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(robRecursive(nums2)); // Output: 11

        int[] nums3 = {6, 7, 1, 3, 8, 2, 4};
        System.out.println(robRecursive(nums3)); // Output: 19
    }
}
 */
public class HouseRobber2 {

    // Optimized DP solution
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        // Case 1: Rob first house and skip last
        int max1 = robHelper(nums, 0, nums.length - 2);

        // Case 2: Skip first house and consider last
        int max2 = robHelper(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    private static int robHelper(int[] nums, int start, int end) {
        int prev1 = 0; // dp[i-1]
        int prev2 = 0;  // dp[i-2]

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(rob(nums1)); // Output: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(rob(nums2)); // Output: 11

        int[] nums3 = {6, 7, 1, 3, 8, 2, 4};
        System.out.println(rob(nums3)); // Output: 19
    }
}