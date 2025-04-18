package arrays.geometry;
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

**************
Explanation:

    Edge Case Handling:

        If there are only 2 points, they always form a straight line (return true immediately).

    Slope Calculation:

        We use the first two points to determine the expected slope of the line.

        Calculate the differences in x (dx) and y (dy) coordinates between these points.

    Collinearity Check:

        For each subsequent point, we verify if it lies on the same line by checking the cross product relationship:

            (y - y0) * dx == (x - x0) * dy

        This avoids division and potential floating-point precision issues.

        If any point fails this check, we return false immediately.

    Return Result:

        If all points satisfy the collinearity condition, return true.

Key Points:

    Uses integer arithmetic to avoid floating-point precision problems

    Efficient O(n) time complexity where n is number of points

    O(1) space complexity

    Handles vertical lines (where dx would be 0) and horizontal lines (where dy would be 0) automatically

Example Walkthrough:

Example 1: [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]

    First two points: dx = 1, dy = 1

    Check point [3,4]: (4-2)1 == (3-1)1 → 2 == 2 ✔

    Check point [4,5]: (5-2)1 == (4-1)1 → 3 == 3 ✔

    Continue for all points - all satisfy → return true

Example 2: [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]

    First two points: dx = 1, dy = 1

    Check point [3,4]: (4-1)1 == (3-1)1 → 3 == 2 ❌

    Immediately return false
 */
public class CheckIfItIsAStraightLine {
    public static void main(String[] args) {
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        System.out.println(checkStraightLine(coordinates));
    }

    private static boolean checkStraightLine(int[][] coordinates) {
        // If there are only 2 points, they always form a straight line
        if (coordinates.length == 2) {
            return true;
        }

        // Get the first two points to determine the expected slope
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int x1 = coordinates[1][0], y1 = coordinates[1][1];

        // Calculate the differences between first two points
        int dx = x1 - x0;
        int dy = y1 - y0;

        // Check all subsequent points
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];

            // Using cross product to check collinearity
            // (y - y0) * dx should equal (x - x0) * dy
            if (dy * (x - x0) != dx * (y - y0)) {
                return false;
            }
        }

        return true;
    }
}
