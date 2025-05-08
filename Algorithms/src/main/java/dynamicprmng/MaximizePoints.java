package dynamicprmng;

import java.util.HashMap;
import java.util.Map;

/*
You're an adventurous spelunker exploring a treasure-filled cavern. Each chamber holds a precious gem marked with an integer value. Your goal? Maximize your haul by strategically collecting these gems!

Explore the cave, represented by an integer array nums. Each element nums[i] represents the value of a gem in the chamber i. You can claim a gem (delete nums[i]) and earn points equal to its value. But there's a catch! When you claim a gem: All gems in other chambers with values one less than your chosen gem (i.e., nums[j] = nums[i] - 1) crumble instantly and disappear. All gems in other chambers with values one more than your chosen gem (i.e., nums[j] = nums[i] + 1) also crumble and vanish. Your mission is to maximize the total points you earn by strategically claiming gems. You can claim any gem any number of times (until it's gone!).

Input/Output Format Examples:
Input:
[3, 4, 2] (Cave chambers with gem values)

Output:
6 (Maximum points earned by strategically claiming gems)

Explanation:
You can claim:

Gem 4 from chamber 1, earning 4 points and causing chambers 2 and 3 (containing gems 3 and 5) to crumble.
Gem 2 from the remaining chamber, earning 2 points.

Input:1
A single line containing an integer array nums separated by spaces or commas. Each element in nums is an integer between 1 and 10^4 (inclusive). The length of nums (n) must be between 1 and 2 * 10^4 (inclusive).

Output:
return a single integer representing the maximum number of points you can earn by claiming gems in the cave according to the described rules.

Constraints:
1 <= n <= 2 * 10^4

1 <= nums[i] <= 10^4 for all i in [0, n-1]
 */
public class MaximizePoints {
    public static void main(String[] args) {
        int[] nums = {3, 4, 2};
        System.out.println(maxPoints(nums)); // Output: 6
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println(maxPoints(nums2)); // Output: 9
        int[] nums3 = {5, 5, 5, 5};
        System.out.println(maxPoints(nums3)); // Output: 20
    }




    public static int maxPoints(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Find the maximum number in the array
        int maxNumber = 0;
        for (int num : nums) {
            if (num > maxNumber) {
                maxNumber = num;
            }
        }

        // Create an array to store the sum of values for each number
        int[] points = new int[maxNumber + 1];
        for (int num : nums) {
            points[num] += num;
        }

        // Handle base cases
        if (maxNumber == 1) {
            return points[1];
        }

        // Initialize DP array
        int[] dp = new int[maxNumber + 1];
        dp[0] = 0;
        dp[1] = points[1];

        // Fill DP array
        for (int i = 2; i <= maxNumber; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + points[i]);
        }

        return dp[maxNumber];
    }

    // Space optimized version without Map
    public static int deleteAndEarnOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Find the maximum number
        int maxNumber = 0;
        for (int num : nums) {
            if (num > maxNumber) {
                maxNumber = num;
            }
        }

        // Create points array
        int[] points = new int[maxNumber + 1];
        for (int num : nums) {
            points[num] += num;
        }

        // Handle edge cases
        if (maxNumber == 1) {
            return points[1];
        }

        // Space optimized DP
        int twoBack = 0;
        int oneBack = points[1];

        for (int i = 2; i <= maxNumber; i++) {
            int current = Math.max(oneBack, twoBack + points[i]);
            twoBack = oneBack;
            oneBack = current;
        }

        return oneBack;
    }
    }
