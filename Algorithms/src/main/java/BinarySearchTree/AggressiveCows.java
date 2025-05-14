package BinarySearchTree;
/*
Aggressive Cows
In this scenario, the objective is to strategically allocate k aggressive cows to stalls in a way that maximizes the minimum distance between any two cows. Given an array of n integers representing stall positions, along with the integer k indicating the number of aggressive cows, the challenge is to find an arrangement that ensures the largest possible gap between any two cows.

Input Format:

The first line contains two integers: n: The number of stalls and k: The number of aggressive cows.
The second line contains n space-separated integers representing the positions of the stalls, in ascending order.
Output Format:

Print a single integer representing the largest possible minimum distance between any two cows when placed optimally in the stalls.
Sample Input 1
5 3
1 2 4 8 9
Sample Output 1
3
Explanation:

We have 5 stalls at positions [1, 2, 4, 8, 9] and we need to place 3 cows.
To maximize the minimum distance between any two cows, the optimal placement is: Place the cows at positions 1, 4, and 8.
The minimum distance between any two cows in this arrangement is 3 (the distance between 1 and 4, or between 4 and 8).
Therefore, the largest possible minimum distance is 3.
Sample Input 2
5 3
10 1 2 7 5
Sample Output 2
4
Explanation:

The sorted positions are [1, 2, 5, 7, 10].
Place cows to maximize the minimum distance between any two cows
Option 1: Place cows at 1, 5, and 10. Distances: 5 - 1 = 4, 10 - 5 = 5 and Minimum distance in this arrangement is 4.
Option 2: Place cows at 1, 7, and 10. Distances: 7 - 1 = 6, 10 - 7 = 3 and Minimum distance in this arrangement is 3.
The optimal placement that maximizes the minimum distance is placing cows at 1, 5, and 10, which gives a minimum distance of 4.
Constraints:
2 <= n <= 10^5

2 <= k <= n

0 <= stalls[i] <= 10^9

Note:The function should return the result. The driver code will handle printing the output.
 */
public class AggressiveCows {
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int[] stalls = {1, 2, 4, 8, 9};
        System.out.println(maxMinDistance(stalls, n, k));
    }

    public static int maxMinDistance(int[] stalls, int n, int k) {
        // Sort the stalls array
        java.util.Arrays.sort(stalls);

        // Initialize binary search bounds
        int low = 1; // Minimum possible distance
        int high = stalls[n - 1] - stalls[0]; // Maximum possible distance

        // Binary search for the largest minimum distance
        while (low < high) {
            int mid = (low + high + 1) / 2; // Midpoint

            if (canPlaceCows(stalls, n, k, mid)) {
                low = mid; // If we can place cows with at least 'mid' distance, try for a larger distance
            } else {
                high = mid - 1; // Otherwise, try a smaller distance
            }
        }

        return low; // The largest minimum distance found
    }

    private static boolean canPlaceCows(int[] stalls, int n, int k, int minDist) {
        int count = 1; // Place the first cow in the first stall
        int lastPosition = stalls[0]; // Last position where a cow was placed

        for (int i = 1; i < n; i++) {
            if (stalls[i] - lastPosition >= minDist) { // Check if we can place a cow here
                count++;
                lastPosition = stalls[i]; // Update the last position where a cow was placed
                if (count == k) { // If we have placed all cows
                    return true;
                }
            }
        }

        return false; // Not enough cows could be placed with the given minimum distance
    }
}
