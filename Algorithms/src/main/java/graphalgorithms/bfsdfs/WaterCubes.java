package graphalgorithms.bfsdfs;

import java.util.Scanner;
import java.util.Stack;

/*
Water Cubes
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Input Format:

The first line contains an integer n, representing the number of vertical lines.
The second line contains n space-separated integers representing the heights of the vertical lines.
Output Format:

Return a single integer representing the maximum amount of water the container can store.
Example 1:
Input:
9
1 8 6 2 5 4 8 3 7
Output:
49
Explanation:
In this case, the maximum area of water that the container can contain is 49. The two lines contributing to this are the ones at indices 1 and 8 (heights 8 and 7), which span a width of 7 units, and the container holds the minimum height between the two (i.e., 7). So, the area is 7 * 7 = 49.

Example 2:
Input:
2
1 1
Output:
1
Explanation:
With only two lines of equal height, the maximum area is 1 * 1 = 1.
Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

Note:The function should return the result. The driver code will handle printing the output.
 */
public class WaterCubes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextInt();
        }
        System.out.println(maxArea(height));
        scanner.close();
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, height.length - 1});

        while (!stack.isEmpty()) {
            int[] indices = stack.pop();
            int left = indices[0];
            int right = indices[1];
            if (left >= right)
                continue;
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] < height[right]) {
                stack.push(new int[]{left + 1, right});
            } else {
                stack.push(new int[]{left, right - 1});
            }
        }
        return maxArea;
    }
}
