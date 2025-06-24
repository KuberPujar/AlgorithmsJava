package stacks.dpbased;

import java.util.Stack;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class TrappingRainWater {
    /**
     * Calculates the amount of water that can be trapped after raining using a stack-based approach.
     * This method iterates through the array and uses a monotonic stack to find potential "wells"
     * and calculate the trapped water. The stack helps in finding the left boundary for a given
     * right boundary, and the computation of water relies on previously processed bars,
     * aligning with dynamic programming principles of building solutions from subproblems.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param height An array of non-negative integers representing the elevation map.
     * @return The total amount of water trapped.
     */
    public int trapWaterWithStack(int[] height) {
        int totalWater = 0;
        Stack<Integer> stack = new Stack<>(); // Stores indices of bars in decreasing order of height.

        for (int i = 0; i < height.length; i++) {
            // While the stack is not empty and the current bar (height[i]) is taller than
            // the bar at the index on top of the stack (height[stack.peek()]),
            // it means we've found a "well" and can calculate trapped water.
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int poppedIndex = stack.pop(); // This is the bar forming the "bottom" of the current well.

                // If the stack becomes empty after popping, it means there's no left boundary
                // (all previous bars were shorter or popped), so no water can be trapped with this 'bottom' bar.
                if (stack.isEmpty()) {
                    break;
                }

                // The new top of the stack is the left boundary of the well.
                int leftBoundaryIndex = stack.peek();
                // The width of the well is the distance between the current bar (right boundary)
                // and the left boundary, minus 1 (because width is distance between walls, not indices).
                int width = i - leftBoundaryIndex - 1;
                // The effective height for trapping water is the minimum of the two boundaries
                // (current bar height and left boundary height) minus the height of the popped bar (the "bottom").
                int effectiveHeight = Math.min(height[i], height[leftBoundaryIndex]) - height[poppedIndex];

                totalWater += width * effectiveHeight;
            }
            stack.push(i); // Push the current bar's index onto the stack. It might be a future left boundary.
        }

        return totalWater;
    }

    /**
     * Calculates the amount of water that can be trapped after raining using a dynamic programming approach.
     * This method pre-computes the maximum height to the left and right of each bar,
     * and then calculates the trapped water. This is a classic DP solution for this problem.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param height An array of non-negative integers representing the elevation map.
     * @return The total amount of water trapped.
     */
    public int trapWaterWithDP(int[] height) {
        int n = height.length;
        if (n <= 2) { // Need at least 3 bars to trap water between them
            return 0;
        }

        // leftMax[i] stores the maximum height of a bar to the left of or including index i.
        int[] leftMax = new int[n];
        // rightMax[i] stores the maximum height of a bar to the right of or including index i.
        int[] rightMax = new int[n];

        // Fill leftMax array from left to right.
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Fill rightMax array from right to left.
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int totalWater = 0;
        // Calculate trapped water for each bar (excluding the first and last, which can't trap water above them)
        for (int i = 0; i < n; i++) {
            // The water level at current bar is determined by the minimum of the max heights
            // to its left and right.
            // The amount of water trapped above the current bar is (water level - current bar height).
            int waterAtCurrentBar = Math.min(leftMax[i], rightMax[i]) - height[i];
            // Only add positive water amounts. If waterAtCurrentBar is negative, it means
            // the bar is taller than or equal to its boundaries, so no water is trapped there.
            totalWater += Math.max(0, waterAtCurrentBar);
        }

        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        // Example 1
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Total water trapped (DP): " + solution.trapWaterWithDP(height1));
        System.out.println("Total water trapped (Stack): " + solution.trapWaterWithStack(height1));

        // Example 2
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Total water trapped (DP): " + solution.trapWaterWithDP(height2));
        System.out.println("Total water trapped (Stack): " + solution.trapWaterWithStack(height2));
    }
}
