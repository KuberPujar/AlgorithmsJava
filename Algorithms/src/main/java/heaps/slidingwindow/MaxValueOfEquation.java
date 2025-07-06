package heaps.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;
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
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int maxValue = Integer.MIN_VALUE;

        for (int j = 0; j < points.length; j++) {
            // Remove points that are outside the window (x_j - x_i > k)
            while (!deque.isEmpty() && points[j][0] - points[deque.peekFirst()][0] > k) {
                deque.pollFirst();
            }

            if (!deque.isEmpty()) {
                int i = deque.peekFirst();
                // Calculate the equation value: y_i + y_j + (x_j - x_i)
                // Which can be rewritten as (y_i - x_i) + (y_j + x_j)
                int currentValue = (points[i][1] - points[i][0]) + (points[j][1] + points[j][0]);
                maxValue = Math.max(maxValue, currentValue);
            }

            // Maintain the deque in decreasing order of (y_i - x_i)
            while (!deque.isEmpty() &&
                    points[j][1] - points[j][0] >= points[deque.peekLast()][1] - points[deque.peekLast()][0]) {
                deque.pollLast();
            }

            deque.offerLast(j);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        MaxValueOfEquation solution = new MaxValueOfEquation();
        int[][] points = {{1, 3}, {2, 0}, {3, 10}, {4, 5}};
        int k = 2;
        int result = solution.findMaxValueOfEquation(points, k);
        System.out.println("Maximum value of the equation: " + result); // Expected output: 10
    }
}
