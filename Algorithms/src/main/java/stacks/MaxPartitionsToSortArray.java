package stacks;
/*
Max Partitions To Make Array Sorted
You are given an integer array arr of length n that represents a permutation of the integers in the range ([0, n - 1]).
We split arr into some number of partitions, and individually sort each partition. After concatenating them, the result should equal the sorted array.

Return the largest number of partitions we can make to sort the array.

Input Format:

The first line contains an integer n denoting the length of the array.
The second line contains n space-separated integers denoting the array arr.
Output Format:

A single integer denoting the largest number of partitions.
Sample Input 1:

5
4 3 2 1 0
Sample Output 1:

1
Explanation:

Splitting into two or more partitions will not return the required result. For example, splitting into ([4, 3], [2, 1, 0]) will result in ([3, 4, 0, 1, 2]), which isn't sorted.

Sample Input 2:

5
1 0 2 3 4
Sample Output 2:

4
Explanation:

We can split into two partitions, such as ([1, 0], [2, 3, 4]). However, splitting into ([1, 0], [2], [3], [4]) is the highest number of partitions possible.

Constraints:

(n == arr.length)
(1 <= n <= 10)
(0 <= arr[i] < n)
All the elements of arr are unique.
Note: The function should return the result.
 */
import java.util.Scanner;
import java.util.Stack;

public class MaxPartitionsToSortArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Calculate and print the result
        System.out.println(maxPartitions(arr));
    }

    public static int maxPartitions(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int maxPartitions = 0;

        for (int num : arr) {
            if (stack.isEmpty() || num > stack.peek()) {
                stack.push(num);
            } else {
                int currentMax = stack.pop();
                while (!stack.isEmpty() && num < stack.peek()) {
                    stack.pop();
                }
                stack.push(currentMax);
            }

            // The size of the stack represents the number of partitions
            maxPartitions = stack.size();
        }

        return maxPartitions;
    }
}