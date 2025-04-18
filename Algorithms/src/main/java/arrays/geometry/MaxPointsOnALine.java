package arrays.geometry;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.



Example 1:


Input: points = [[1,1],[2,2],[3,3]]
Output: 3
Example 2:


Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4


Constraints:

1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
 */
public class MaxPointsOnALine {
    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(maxPoints(points));
    }

    private static int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int maxPoints = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 1; // Count the current point itself
            int vertical = 0;  // Count vertical lines

            for (int j = i + 1; j < points.length; j++) {
                // Check for duplicate points
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    duplicate++;
                    continue;
                }

                // Handle vertical lines (infinite slope)
                if (points[i][0] == points[j][0]) {
                    vertical++;
                    continue;
                }

                // Calculate the slope as a reduced fraction to avoid floating point precision issues
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int gcd = gcd(dx, dy);
                String slope = (dy / gcd) + "/" + (dx / gcd);

                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
            }

            // Find the maximum points in current iteration
            int currentMax = vertical;
            for (int count : slopeCount.values()) {
                if (count > currentMax) {
                    currentMax = count;
                }
            }
            currentMax += duplicate;

            // Update global maximum
            if (currentMax > maxPoints) {
                maxPoints = currentMax;
            }
        }

        return maxPoints;
    }

    // Helper method to calculate greatest common divisor using Euclidean algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
