package stacks;
/*
Can You Find The Magic Pattern?
Given an array of n integers nums, a magic pattern is a subsequence of three integers nums[i], nums[j], and nums[k] such that (i < j < k) and (nums[i] < nums[k] < nums[j]).
Return true if there is a magic pattern in nums, otherwise, return false.

Input Format:

The first line contains the integer n denoting the length of the array.
The second line contains n space-separated integers denoting the array nums.
Output Format:

A single Boolean (0 or 1) denoting if there is a magic pattern or not.
Sample Input 1:

4
1 2 3 4
Sample Output 1:

0
Explanation:

There is no magic pattern in the sequence.

Sample Input 2:

4
3 1 4 2
Sample Output 2:

1
Explanation:

There is a magic pattern in the sequence: ([1, 4, 2]).

Sample Input 3:

4
-1 3 2 0
Sample Output 3:

1
Explanation:

There are three magic patterns in the sequence: ([-1, 3, 2]), ([-1, 3, 0]), and ([-1, 2, 0]).

Constraints:

(n == nums.length)
(1 <= n <= 2 * 10^5)
(-10^9 <= nums[i] <= 10^9)
Note: The function should return the result.
 */
import java.util.Scanner;
import java.util.Stack;

public class MagicPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Calculate and print the result
        System.out.println(find132pattern(nums) ? 1 : 0);
    }

    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;

        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];

        // Precompute the minimum values up to each index
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        // Check for 132 pattern from the end of the array
        for (int j = nums.length - 1; j >= 0; j--) {
            // nums[j] must be greater than min[j] to be the '3' in '132'
            if (nums[j] > min[j]) {
                // Pop elements from stack that are <= min[j]
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                // If we find an element smaller than nums[j], we have our '2'
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }

        return false;
    }
}