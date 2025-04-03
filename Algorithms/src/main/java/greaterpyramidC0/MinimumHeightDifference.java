package greaterpyramidC0;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumHeightDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heights = new int[n];

        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }

        System.out.println(findMinHeightDifference(heights));
    }

    public static int findMinHeightDifference(int[] heights) {
        // Sort the array to easily find adjacent elements with minimum difference
        Arrays.sort(heights);

        int minDiff = Integer.MAX_VALUE;

        // Check differences between consecutive elements
        for (int i = 0; i < heights.length - 1; i++) {
            int currentDiff = Math.abs(heights[i] - heights[i + 1]);
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
            }
        }

        // Also check the difference between first and last element (since they are in a circle)
        int circularDiff = Math.abs(heights[0] - heights[heights.length - 1]);
        if (circularDiff < minDiff) {
            minDiff = circularDiff;
        }

        return minDiff;
    }
}
