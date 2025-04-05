package stacks;
/*
Maximum Possible Breadth of Ramp
A ramp in an integer array nums is a pair ((i, j)) for which (i < j) and (nums[i] <= nums[j]). The breadth of such a ramp is (j - i). Given an integer array nums, return the maximum breadth of a ramp in nums.
If there is no ramp in nums, return 0.

Input Format:

The first line contains an integer n denoting the length of the array.
The second line contains n space-separated integers denoting the array nums.
Output Format:

A single integer denoting the maximum breadth of a ramp.
Sample Input 1:

6
6 0 8 2 1 5
Sample Output 1:

4
Explanation:

The maximum breadth ramp is achieved at ((i, j) = (1, 5)): (nums[1] = 0) and (nums[5] = 5).

Sample Input 2:

10
9 8 1 0 1 9 4 0 4 1
Sample Output 2:

7
Explanation:

The maximum breadth ramp is achieved at ((i, j) = (2, 9)): (nums[2] = 1) and (nums[9] = 1).

Constraints:

(2 <= nums.length <= 5*10^4)
(0 <= nums[i] <= 5 *10^4)
Note: The function should return the result.
 */

import java.util.Scanner;
import java.util.Stack;

public class MaxRampBreadth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Calculate and print the result
        System.out.println(maxWidthRamp(nums));
    }

    public static int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int maxWidth = 0;

        // Build a stack of potential starting indices in decreasing order of values
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                stack.push(i);
            }
        }

        // Scan from the end to find the maximum width ramp
        for (int j = nums.length - 1; j >= 0; j--) {
            while (!stack.isEmpty() && nums[j] >= nums[stack.peek()]) {
                maxWidth = Math.max(maxWidth, j - stack.pop());
            }
            if (stack.isEmpty()) break;
        }

        return maxWidth;
    }
}
