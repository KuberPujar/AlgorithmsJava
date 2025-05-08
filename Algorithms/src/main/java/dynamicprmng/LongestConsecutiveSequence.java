package dynamicprmng;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Longest Consecutive Sequence
You are given an array of integers. Your task is to find the length of the longest consecutive sequence of numbers in this array.

Input Format:
The first line contains an integer n, representing the size of the array.
The second line contains n space-separated integers, representing the elements of the array.
Output Format:
Print a single integer, which is the length of the longest consecutive sequence of numbers in the array.
Constraints:
1 <= n <= 10^5
-10^9 <= {Elements of the array} <= 10^9
Example:
Input:
6
100 4 200 1 3 2
Output:
4
Explanation:
In the given array [100, 4, 200, 1, 3, 2], the longest consecutive sequence is [1, 2, 3, 4], which has a length of 4.

Note: The function should return the result.
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(arr));
        int[] arr2 = {1, 2, 0, 1};
        System.out.println(longestConsecutive(arr2));
        int[] arr3 = {0, 0, 1, 1, 2, 2};
        System.out.println(longestConsecutive(arr3));
    }
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Sort the array to bring consecutive numbers together
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each number is at least a sequence of length 1

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            // If current number is consecutive to previous number
            if (nums[i] == nums[i-1] + 1) {
                dp[i] = dp[i-1] + 1;
                maxLength = Math.max(maxLength, dp[i]);
            }
            // Handle duplicate numbers (they don't break the sequence)
            else if (nums[i] == nums[i-1]) {
                dp[i] = dp[i-1]; // Carry forward the same sequence length
            }
        }

        return maxLength;
    }

    // Alternative DP approach using HashMap (more efficient than sorting)
    public static int longestConsecutiveHashMap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int currentLength = left + right + 1;

                map.put(num, currentLength);
                map.put(num - left, currentLength);
                map.put(num + right, currentLength);

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }
}
