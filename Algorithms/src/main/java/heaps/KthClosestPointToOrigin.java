package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
K Closest Points to Origin
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

Example:

 Input: points = [[1,3],[-2,2]], k = 1

Output: [[-2,2]]

Explantion:

The distance between (1, 3) and the origin is sqrt(10). The distance between (-2, 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin. We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Input: points = [[3,3],[5,-1],[-2,4]], k = 2

Output:

[[3,3],[-2,4]]

Constraints:

1 <= k <= points.length <= 104
-104 <= xi, yi <= 104
The function should return the result
 */
public class KthClosestPointToOrigin {
    public static int[][] kClosest(int[][] points, int k) {
        // Create a max heap to store the k closest points.  It's a max heap
        // because we want to keep the largest distance points out.
        // Create a max-heap based on squared distance from origin
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract elements from the heap to form the result
        List<int[]> resultList = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            resultList.add(maxHeap.poll());
        }

        // Convert the list to a 2D array
        int[][] result = new int[resultList.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage:
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] result1 = kClosest(points1, k1);
        System.out.print("K closest points to origin for points1: ");
        for (int[] point : result1) {
            System.out.print("[" + point[0] + "," + point[1] + "] ");
        }
        System.out.println(); // Add a newline for better formatting

        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] result2 = kClosest(points2, k2);
        System.out.print("K closest points to origin for points2: ");
        for (int[] point : result2) {
            System.out.print("[" + point[0] + "," + point[1] + "] ");
        }
        System.out.println();
    }
}
