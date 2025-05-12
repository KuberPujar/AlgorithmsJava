package BinarySearchTree;

import java.util.Scanner;

/*
Peak Element
Imagine you're a coach of a competitive programming team, and you've been given an interesting problem to solve. Your task is to help your team members find the index of the greatest peak element in an array. A peak element in an array is an element that is greater than or equal to its neighbours. To clarify, an element at the edge of the array only needs one neighbour to be considered a peak. For example, in the array [5, 3, 4], both 5 and 4 are peak elements.

Input:

The first line contains an integer n denoting the number of elements in the array.
The second line contains n space-separated integers representing the elements of the array.
Output:

Print a single integer representing the index of the greatest peak element in the array
Examples:

Input 1:

 4
 1 2 3 1

Output 1:

2
Explanation:

In the array [1, 2, 3, 1], the element 3 is the greatest peak element as it is greater than its neighbors 2 and 1. The index of 3 is 2.
Input 2:

5
1 7 3 5 9

Output 2:

4
Explanation:

In the array [1, 7, 3, 5, 9], the element 9 is the greatest peak element as it is greater than its neighbors 5 and (there is no neighbor to the right). The index of 9 is 4.
Constraints:

1 <= n <= 10^5

0 < i < n

-104 <= nums[i] <= 104

Note:The function should return the result. The driver code will handle printing the output.
 */
public class PeakElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int maxPeakValue = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < n; i++) {
            if (isPeak(arr, i)) {
                if (arr[i] > maxPeakValue) {
                    maxPeakValue = arr[i];
                    maxIndex = i;
                }
            }
        }

        System.out.println(maxIndex);
    }

    private static boolean isPeak(int[] arr, int i) {
        int n = arr.length;
        boolean leftOk = (i == 0) || (arr[i] >= arr[i - 1]);
        boolean rightOk = (i == n - 1) || (arr[i] >= arr[i + 1]);
        return leftOk && rightOk;
    }
}
