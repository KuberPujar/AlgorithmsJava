package queues.priorityq;

import java.util.PriorityQueue;

/*
You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.

Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.

It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.



Example 1:

Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
Output: 4
Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
Example 2:

Input: points = [[0,0],[3,0],[9,2]], k = 3
Output: 3
Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.


Constraints:

2 <= points.length <= 105
points[i].length == 2
-108 <= xi, yi <= 108
0 <= k <= 2 * 108
xi < xj for all 1 <= i < j <= points.length
xi form a strictly increasing sequence.
 */
public class MaxValueOfEquation {
    /**
     * Finds the maximum value of the equation yi + yj + |xi - xj| given the constraints.
     *
     * The equation can be rewritten. Since the points are sorted by x-coordinates and we consider i < j,
     * xi < xj, so |xi - xj| becomes xj - xi.
     * The equation is yi + yj + xj - xi, which can be grouped as (xj + yj) + (yi - xi).
     *
     * For each point `j`, we want to find a point `i` (where i < j) that maximizes `yi - xi`
     * while satisfying the constraint `xj - xi <= k`. This is equivalent to `xi >= xj - k`.
     *
     * This creates a sliding window problem. As we iterate through each point `j`, we need to
     * find the maximum `yi - xi` from all previous points `i` that are still in the valid
     * window (i.e., their x-coordinate `xi` is not too far away).
     *
     * A PriorityQueue (as a max-heap) is ideal for efficiently tracking the maximum `yi - xi`
     * within this sliding window.
     *
     * Time Complexity: O(n * log n), where n is the number of points. Each point is added
     * to the priority queue once, and each removal is O(log n).
     * Space Complexity: O(n) in the worst case for the priority queue.
     *
     * @param points A 2D array of points, sorted by their x-coordinate.
     * @param k      The maximum allowed difference between x-coordinates.
     * @return The maximum value of the equation.
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        // The priority queue will store pairs of [value, x_coordinate].
        // The value is `yi - xi`. We use a max-heap to keep the largest values at the top.
        // The lambda (a, b) -> b[0] - a[0] sorts by the value in descending order.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int maxVal = Integer.MIN_VALUE;

        // Iterate through each point, treating it as point 'j'.
        for (int[] point : points) {
            int xj = point[0];
            int yj = point[1];

            // 1. Maintain the sliding window:
            // Remove points from the heap that are no longer valid for the current point 'j'.
            // A point 'i' is invalid if its x-coordinate `xi` is less than `xj - k`.
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] < xj - k) {
                maxHeap.poll();
            }

            // 2. Calculate the maximum value with the current point 'j':
            // If the heap is not empty, its top element is the best point 'i' in the window.
            if (!maxHeap.isEmpty()) {
                int[] bestPreviousPoint = maxHeap.peek();
                int yi_minus_xi = bestPreviousPoint[0];
                maxVal = Math.max(maxVal, xj + yj + yi_minus_xi);
            }

            // 3. Add the current point to the heap for future calculations.
            // We add the value `yj - xj` and the coordinate `xj`.
            maxHeap.offer(new int[]{yj - xj, xj});
        }

        return maxVal;
    }

    /**
     * Main method to test the findMaxValueOfEquation function.
     */
    public static void main(String[] args) {
        MaxValueOfEquation solution = new MaxValueOfEquation();

        // Example 1
        int[][] points1 = {{1, 3}, {2, 0}, {5, 10}, {6, -10}};
        int k1 = 1;
        System.out.println("Max value for points1: " + solution.findMaxValueOfEquation(points1, k1)); // Expected: 4

        // Example 2
        int[][] points2 = {{0, 0}, {3, 0}, {9, 2}};
        int k2 = 3;
        System.out.println("Max value for points2: " + solution.findMaxValueOfEquation(points2, k2)); // Expected: 3
    }
}
