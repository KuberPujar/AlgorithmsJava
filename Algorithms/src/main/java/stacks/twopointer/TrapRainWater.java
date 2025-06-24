package stacks.twopointer;

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
public class TrapRainWater {
    /**
     * Calculates the amount of water that can be trapped after raining using the two-pointer approach.
     * This method is generally preferred due to its O(1) space complexity.
     *
     * @param height An array of non-negative integers representing the elevation map.
     * @return The total amount of water trapped.
     */
    public int trap(int[] height) {
        // Handle edge cases: if the array is null or has less than 3 elements,
        // no water can be trapped.
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0; // Left pointer, starting from the beginning of the array.
        int right = height.length - 1; // Right pointer, starting from the end of the array.
        int totalWater = 0; // Accumulator for the total trapped water.

        // Initialize maximum heights encountered from the left and right sides.
        int leftMax = 0;
        int rightMax = 0;

        // The two-pointer approach iterates inward from both ends.
        // It processes the shorter bar's side because that side is guaranteed
        // to be the limiting factor for water trapping on that iteration.
        while (left < right) {
            // If the height at the left pointer is less than the height at the right pointer,
            // we process the left side.
            if (height[left] < height[right]) {
                // If the current bar on the left is taller than or equal to leftMax,
                // update leftMax. No water can be trapped above this bar yet.
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // If the current bar is shorter than leftMax, water can be trapped.
                    // The amount of water trapped at this position is leftMax - height[left].
                    totalWater += leftMax - height[left];
                }
                left++; // Move the left pointer to the right.
            } else {
                // If the height at the right pointer is less than or equal to the height at the left pointer,
                // we process the right side.
                // If the current bar on the right is taller than or equal to rightMax,
                // update rightMax. No water can be trapped above this bar yet.
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // If the current bar is shorter than rightMax, water can be trapped.
                    // The amount of water trapped at this position is rightMax - height[right].
                    totalWater += rightMax - height[right];
                }
                right--; // Move the right pointer to the left.
            }
        }

        return totalWater; // Return the total calculated trapped water.
    }

    /**
     * Calculates the amount of water that can be trapped after raining using a stack-based approach.
     * This method iterates through the array once and uses a stack to keep track of bars
     * that might form the left boundary of a potential "well".
     *
     * @param height An array of non-negative integers representing the elevation map.
     * @return The total amount of water trapped.
     */
    public int trapWithStack(int[] height) {
        int totalWater = 0;
        Stack<Integer> stack = new Stack<>(); // Stores indices of bars.

        for (int i = 0; i < height.length; i++) {
            // While the stack is not empty and the current bar is taller than
            // the bar at the index on top of the stack, we found a "well".
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int poppedIndex = stack.pop(); // The bottom of the well.

                // If the stack becomes empty after popping, it means there's no left boundary,
                // so no water can be trapped with the current popped bar.
                if (stack.isEmpty()) {
                    break;
                }

                int leftBoundaryIndex = stack.peek(); // The left boundary of the well.
                int width = i - leftBoundaryIndex - 1; // Distance between boundaries minus 1 for the bars themselves.
                // The effective height for trapping water is the minimum of the two boundaries
                // minus the height of the popped bar (the "bottom" of the well).
                int effectiveHeight = Math.min(height[i], height[leftBoundaryIndex]) - height[poppedIndex];

                totalWater += width * effectiveHeight;
            }
            stack.push(i); // Push the current bar's index onto the stack.
        }

        return totalWater;
    }

    /**
     * Main method for testing the TrappingRainWater solution.
     * Includes examples from the problem description, testing both two-pointer and stack solutions.
     */
    public static void main(String[] args) {
        TrapRainWater solution = new TrapRainWater();

        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Input: [0,1,0,2,1,0,1,3,2,1,2,1]");
        System.out.println("--- Two-Pointer Solution ---");
        System.out.println("Output: " + solution.trap(height1)); // Expected output: 6
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.trapWithStack(height1)); // Expected output: 6
        System.out.println("------------------------------------");

        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Input: [4,2,0,3,2,5]");
        System.out.println("--- Two-Pointer Solution ---");
        System.out.println("Output: " + solution.trap(height2)); // Expected output: 9
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.trapWithStack(height2)); // Expected output: 9
        System.out.println("------------------------------------");

        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Input: [1,2,3,4,5]");
        System.out.println("--- Two-Pointer Solution ---");
        System.out.println("Output: " + solution.trap(height3)); // Expected output: 0
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.trapWithStack(height3)); // Expected output: 0
        System.out.println("------------------------------------");

        int[] height4 = {5, 4, 3, 2, 1};
        System.out.println("Input: [5,4,3,2,1]");
        System.out.println("--- Two-Pointer Solution ---");
        System.out.println("Output: " + solution.trap(height4)); // Expected output: 0
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.trapWithStack(height4)); // Expected output: 0
        System.out.println("------------------------------------");

        int[] height5 = {5, 0, 0, 0, 5};
        System.out.println("Input: [5,0,0,0,5]");
        System.out.println("--- Two-Pointer Solution ---");
        System.out.println("Output: " + solution.trap(height5)); // Expected output: 15
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.trapWithStack(height5)); // Expected output: 15
        System.out.println("------------------------------------");
    }
}
