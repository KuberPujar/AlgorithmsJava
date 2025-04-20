package math.geometry;
/*
iven an array points where points[i] = [xi, yi] represents a point on the X-Y plane, return true if these points are a boomerang.

A boomerang is a set of three points that are all distinct and not in a straight line.



Example 1:

Input: points = [[1,1],[2,3],[3,2]]
Output: true
Example 2:

Input: points = [[1,1],[2,2],[3,3]]
Output: false


Constraints:

points.length == 3
points[i].length == 2
0 <= xi, yi <= 100
 */
public class Boomrang {
    public static void main(String[] args) {
        int[][] points1 = {{1, 1}, {2, 3}, {3, 2}};
        System.out.println(isBoomerang(points1)); // Output: true

        int[][] points2 = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(isBoomerang(points2)); // Output: false
    }

    private static boolean isBoomerang(int[][] points) {
        // Extract the three points
        int[] p1 = points[0];
        int[] p2 = points[1];
        int[] p3 = points[2];

        // Check if all points are distinct
        if ((p1[0] == p2[0] && p1[1] == p2[1]) ||
                (p1[0] == p3[0] && p1[1] == p3[1]) ||
                (p2[0] == p3[0] && p2[1] == p3[1])) {
            return false;
        }

        // Calculate the area of the triangle formed by the three points
        // Using the shoelace formula:
        // Area = 1/2 |(x1(y2-y3) + x2(y3-y1) + x3(y1-y2))|
        int area = p1[0] * (p2[1] - p3[1]) +
                p2[0] * (p3[1] - p1[1]) +
                p3[0] * (p1[1] - p2[1]);

        // If area is zero, points are collinear (not a boomerang)
        return area != 0;
    }
}
