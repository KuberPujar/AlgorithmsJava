package heaps.divideandconquer;

import java.util.Arrays;/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).



Example 1:


Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.


Constraints:

1 <= k <= points.length <= 104
-104 <= xi, yi <= 104
 */
public class KClosestPointToOrigin {

        public int[][] kClosest(int[][] points, int k) {
            quickSelect(points, 0, points.length - 1, k);
            return Arrays.copyOfRange(points, 0, k);
        }

        private void quickSelect(int[][] points, int left, int right, int k) {
            if (left >= right) {
                return;
            }
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == k) {
                return;
            } else if (pivotIndex < k) {
                quickSelect(points, pivotIndex + 1, right, k);
            } else {
                quickSelect(points, left, pivotIndex - 1, k);
            }
        }

        private int partition(int[][] points, int left, int right) {
            int[] pivot = points[right];
            int pivotDist = squaredDistance(pivot);
            int i = left;
            for (int j = left; j < right; j++) {
                if (squaredDistance(points[j]) < pivotDist) {
                    swap(points, i, j);
                    i++;
                }
            }
            swap(points, i, right);
            return i;
        }

        private int squaredDistance(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }

        private void swap(int[][] points, int i, int j) {
            int[] temp = points[i];
            points[i] = points[j];
            points[j] = temp;
        }


    public static void main(String[] args) {
        KClosestPointToOrigin solution = new KClosestPointToOrigin();
        int[][] points = {{1, 3}, {-2, 2}};
        int k = 1;
        int[][] result = solution.kClosest(points, k);
        for (int[] point : result) {
            System.out.println(Arrays.toString(point));
        }
    }
}
