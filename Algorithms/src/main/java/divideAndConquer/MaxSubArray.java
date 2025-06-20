package divideAndConquer;

import java.util.Arrays;

public class MaxSubArray {
    // Solution 1: Kadane's Algorithm - O(n) time, O(1) space (OPTIMAL)
    public int maxSubArrayKadane(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend existing subarray or start new subarray
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update global maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    // Solution 1b: Kadane's Algorithm with subarray tracking
    public class SubarrayResult {
        int maxSum;
        int startIndex;
        int endIndex;

        SubarrayResult(int sum, int start, int end) {
            this.maxSum = sum;
            this.startIndex = start;
            this.endIndex = end;
        }
    }

    public SubarrayResult maxSubArrayWithIndices(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (maxEndingHere < 0) {
                maxEndingHere = nums[i];
                tempStart = i;
            } else {
                maxEndingHere += nums[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new SubarrayResult(maxSoFar, start, end);
    }

    // Solution 2: Divide and Conquer - O(n log n) time, O(log n) space
    public int maxSubArrayDivideConquer(int[] nums) {
        return maxSubArrayRec(nums, 0, nums.length - 1);
    }

    private int maxSubArrayRec(int[] nums, int left, int right) {
        // Base case
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // Recursively find maximum subarray sum in left and right halves
        int leftMax = maxSubArrayRec(nums, left, mid);
        int rightMax = maxSubArrayRec(nums, mid + 1, right);

        // Find maximum subarray sum that crosses the middle
        int crossMax = maxCrossingSum(nums, left, mid, right);

        // Return maximum of the three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Find maximum sum for left part (including mid)
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        // Find maximum sum for right part (excluding mid)
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }

        // Return sum of left and right parts
        return leftSum + rightSum;
    }

    // Solution 3: Brute Force - O(n²) time, O(1) space
    public int maxSubArrayBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    // Solution 4: Dynamic Programming approach - O(n) time, O(n) space
    public int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // dp[i] = maximum sum ending at index i
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    // Helper method to print subarray
    private void printSubarray(int[] nums, int start, int end) {
        System.out.print("[");
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i]);
            if (i < end) System.out.print(",");
        }
        System.out.print("]");
    }

    // Test method with examples
    public static void main(String[] args) {
        MaxSubArray solution = new MaxSubArray();

        // Test Case 1: [-2,1,-3,4,-1,2,1,-5,4]
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Example 1: " + Arrays.toString(nums1));
        System.out.println("Kadane's Algorithm: " + solution.maxSubArrayKadane(nums1));
        System.out.println("Divide & Conquer: " + solution.maxSubArrayDivideConquer(nums1));
        System.out.println("Brute Force: " + solution.maxSubArrayBruteForce(nums1));
        System.out.println("Dynamic Programming: " + solution.maxSubArrayDP(nums1));

        // Show the actual subarray
        SubarrayResult result1 = solution.maxSubArrayWithIndices(nums1);
        System.out.print("Maximum subarray: ");
        solution.printSubarray(nums1, result1.startIndex, result1.endIndex);
        System.out.println(" with sum: " + result1.maxSum);
        System.out.println();

        // Test Case 2: [1]
        int[] nums2 = {1};
        System.out.println("Example 2: " + Arrays.toString(nums2));
        System.out.println("Kadane's Algorithm: " + solution.maxSubArrayKadane(nums2));
        System.out.println("Divide & Conquer: " + solution.maxSubArrayDivideConquer(nums2));
        System.out.println();

        // Test Case 3: [5,4,-1,7,8]
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("Example 3: " + Arrays.toString(nums3));
        System.out.println("Kadane's Algorithm: " + solution.maxSubArrayKadane(nums3));
        System.out.println("Divide & Conquer: " + solution.maxSubArrayDivideConquer(nums3));

        SubarrayResult result3 = solution.maxSubArrayWithIndices(nums3);
        System.out.print("Maximum subarray: ");
        solution.printSubarray(nums3, result3.startIndex, result3.endIndex);
        System.out.println(" with sum: " + result3.maxSum);
        System.out.println();

        // Edge Case: All negative numbers
        int[] nums4 = {-5, -2, -8, -1};
        System.out.println("All negative: " + Arrays.toString(nums4));
        System.out.println("Kadane's Algorithm: " + solution.maxSubArrayKadane(nums4));
        System.out.println("Divide & Conquer: " + solution.maxSubArrayDivideConquer(nums4));
        System.out.println();

        // Performance comparison
        System.out.println("=== Performance Analysis ===");
        System.out.println("1. Kadane's Algorithm: O(n) time, O(1) space - OPTIMAL");
        System.out.println("2. Divide & Conquer: O(n log n) time, O(log n) space");
        System.out.println("3. Dynamic Programming: O(n) time, O(n) space");
        System.out.println("4. Brute Force: O(n²) time, O(1) space");

        // Demonstration of the algorithm logic
        System.out.println("\n=== Kadane's Algorithm Step-by-Step ===");
        demonstrateKadane(nums1);
    }

    // Method to demonstrate Kadane's algorithm step by step
    private static void demonstrateKadane(int[] nums) {
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Step | Current | MaxEndingHere | MaxSoFar | Decision");
        System.out.println("-----|---------|---------------|----------|----------");

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        System.out.printf("  0  |   %2d    |      %2d       |    %2d    | Initialize\n",
                nums[0], maxEndingHere, maxSoFar);

        for (int i = 1; i < nums.length; i++) {
            int prevMaxEndingHere = maxEndingHere;
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);

            String decision = (nums[i] > prevMaxEndingHere + nums[i]) ?
                    "Start new" : "Extend existing";

            System.out.printf("  %d  |   %2d    |      %2d       |    %2d    | %s\n",
                    i, nums[i], maxEndingHere, maxSoFar, decision);
        }
    }
}

/*
Algorithm Explanations:

1. Kadane's Algorithm (Optimal):
   - At each position, decide whether to extend the existing subarray or start a new one
   - maxEndingHere = max(current_element, maxEndingHere + current_element)
   - Keep track of the global maximum seen so far
   - Time: O(n), Space: O(1)

2. Divide and Conquer:
   - Divide array into two halves
   - Maximum subarray is either:
     a) Entirely in left half
     b) Entirely in right half
     c) Crosses the middle point
   - Recursively solve for (a) and (b), compute (c) directly
   - Time: O(n log n), Space: O(log n)

3. Dynamic Programming:
   - dp[i] represents maximum sum ending at index i
   - dp[i] = max(nums[i], dp[i-1] + nums[i])
   - Answer is max(dp[0], dp[1], ..., dp[n-1])
   - Time: O(n), Space: O(n)

4. Brute Force:
   - Try all possible subarrays
   - For each starting position, try all ending positions
   - Time: O(n²), Space: O(1)

Key Insights:
- Kadane's algorithm is based on the observation that if the sum of elements
  up to the current position is negative, it's better to start fresh
- The divide and conquer approach is more subtle but helps understand the
  recursive structure of the problem
- Both optimal solutions (Kadane's and DP with O(1) space) have the same
  underlying logic but different implementations
*/
