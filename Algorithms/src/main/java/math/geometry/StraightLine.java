package math.geometry;
/*
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.





Example 1:



Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true
Example 2:



Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false


Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
 */
public class StraightLine {
    public static void main(String[] args) {
        int[][] coordinates1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        System.out.println(checkStraightLine(coordinates1)); // Output: true

        int[][] coordinates2 = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};
        System.out.println(checkStraightLine(coordinates2)); // Output: false
    }

  private static boolean checkStraightLine(int[][] coordinates) {
            // If there are only 2 points, they always form a straight line
            if (coordinates.length == 2) {
                return true;
            }

            // Get the first two points to calculate the reference slope
            int x0 = coordinates[0][0], y0 = coordinates[0][1];
            int x1 = coordinates[1][0], y1 = coordinates[1][1];

            // Calculate the differences between first two points
            int dx = x1 - x0;
            int dy = y1 - y0;

            // Check all subsequent points against the reference slope
            for (int i = 2; i < coordinates.length; i++) {
                int xi = coordinates[i][0], yi = coordinates[i][1];

                // Using cross product to avoid division and floating point precision issues
                // (y1 - y0)*(xi - x0) should equal (yi - y0)*(x1 - x0) for colinear points
                if (dy * (xi - x0) != dx * (yi - y0)) {
                    return false;
                }
            }

            return true;
        }
}
