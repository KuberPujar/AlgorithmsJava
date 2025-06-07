package graphalgorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/*
You're the architect of Harmony Hills, a quaint utopia where neighbors strive for peaceful coexistence. Each resident is represented by a number (in nums) reflecting their desired level of social interaction (the larger the number, the more social). Your goal? Build vibrant communities within this diverse population by grouping residents into "continuous" neighborhoods where everyone feels comfortable.

You're given an array nums representing the social preferences of Harmony Hills residents (positive integers between 1 and 10^9).
A "continuous" neighborhood is a group of residents where the difference in desired social interaction between any two residents is at most 2 (i.e., |resident1 preference - resident2 preference| <= 2). Your mission is to find the total number of possible continuous neighborhoods you can create within Harmony Hills. Remember, the more harmonious neighborhoods, the happier the residents!

Input/Output Format:

Input:

An array nums containing the social preferences of residents separated by spaces or commas.

Output:

Return a single integer representing the total number of possible continuous neighborhoods in Harmony Hills.

Examples:

Input:

5 4 2 4 (Resident preferences in Harmony Hills)
Output:

8 (As in the original example, there are 8 possible neighborhoods catering to everyone's social needs.)

Input:

1 2 3 (Another peaceful community)
Output:

6 (Similar to the first example, 6 neighborhoods can be formed to ensure everyone feels comfortable.)
Constraints:

1 <= n <= 10^5 (Number of residents in Harmony Hills)
1 <= nums[i] <= 10^9 (Individual resident's social preference)
 */
public class ContinousNieghborhoods { /**
 * Finds the total number of continuous neighborhoods in Harmony Hills.
 * A continuous neighborhood is a subarray where the difference between
 * max and min elements is at most 2.
 *
 * @param nums array of resident social preferences
 * @return total number of possible continuous neighborhoods
 */
public static int countContinuousNeighborhoods(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }

    int n = nums.length;
    int totalNeighborhoods = 0;

    // For each starting position
    for (int start = 0; start < n; start++) {
        int min = nums[start];
        int max = nums[start];

        // Extend the subarray as far as possible
        for (int end = start; end < n; end++) {
            // Update min and max for current subarray
            min = Math.min(min, nums[end]);
            max = Math.max(max, nums[end]);

            // If difference is within limit, it's a valid neighborhood
            if (max - min <= 2) {
                totalNeighborhoods++;
            } else {
                // Can't extend further from this start position
                break;
            }
        }
    }

    return totalNeighborhoods;
}

    /**
     * Optimized version using sliding window with deques for better performance
     * on larger inputs.
     */
    public static int countContinuousNeighborhoodsOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int totalNeighborhoods = 0;

        for (int start = 0; start < n; start++) {
            // Deques to maintain min and max in current window
            Deque<Integer> minDeque = new ArrayDeque<>();
            Deque<Integer> maxDeque = new ArrayDeque<>();

            for (int end = start; end < n; end++) {
                // Maintain min deque (increasing order)
                while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[end]) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(end);

                // Maintain max deque (decreasing order)
                while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[end]) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(end);

                // Check if current subarray is valid
                int min = nums[minDeque.peekFirst()];
                int max = nums[maxDeque.peekFirst()];

                if (max - min <= 2) {
                    totalNeighborhoods++;
                } else {
                    break;
                }
            }
        }

        return totalNeighborhoods;
    }

    /**
     * Helper method to parse input string and convert to integer array
     */
    public static int[] parseInput(String input) {
        String[] parts = input.trim().split("[\\s,]+");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "5 4 2 4",
                "1 2 3",
                "1",
                "1 1 1 1",
                "10 1 5 3 8"
        };

        System.out.println("=== Harmony Hills Continuous Neighborhoods ===\n");

        for (String testCase : testCases) {
            int[] nums = parseInput(testCase);
            int result = countContinuousNeighborhoods(nums);

            System.out.println("Input: " + testCase);
            System.out.println("Resident preferences: " + Arrays.toString(nums));
            System.out.println("Total continuous neighborhoods: " + result);

            // Verify with optimized version
            int optimizedResult = countContinuousNeighborhoodsOptimized(nums);
            System.out.println("Optimized result: " + optimizedResult);
            System.out.println("Results match: " + (result == optimizedResult));
            System.out.println();
        }

        // Example of how to use with Scanner for interactive input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter resident preferences (space or comma separated): ");
        String userInput = scanner.nextLine();

        try {
            int[] userNums = parseInput(userInput);
            int userResult = countContinuousNeighborhoods(userNums);
            System.out.println("Your Harmony Hills can form " + userResult + " continuous neighborhoods!");
        } catch (Exception e) {
            System.out.println("Invalid input format. Please use numbers separated by spaces or commas.");
        }

        scanner.close();
    }
}

/*
 * Algorithm Explanation:
 *
 * The problem asks for the number of subarrays where max - min <= 2.
 *
 * Approach 1 (Simple):
 * - For each starting position, extend the subarray as far as possible
 * - Keep track of min and max in the current subarray
 * - Count valid subarrays until the constraint is violated
 * - Time: O(n²), Space: O(1)
 *
 * Approach 2 (Optimized):
 * - Uses deques to maintain min/max efficiently in sliding windows
 * - Still O(n²) worst case but with better practical performance
 *
 * Example walkthrough for [5,4,2,4]:
 * Start=0: [5], [5,4], [5,4,2] are valid (max-min = 0,1,3) - 2 valid
 * Start=1: [4], [4,2], [4,2,4] are valid (max-min = 0,2,2) - 3 valid
 * Start=2: [2], [2,4] are valid (max-min = 0,2) - 2 valid
 * Start=3: [4] is valid (max-min = 0) - 1 valid
 * Total: 2 + 3 + 2 + 1 = 8
 */