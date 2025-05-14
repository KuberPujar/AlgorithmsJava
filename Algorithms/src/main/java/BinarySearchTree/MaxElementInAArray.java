package BinarySearchTree;
/*
Max Element in array
You have been given an array of elements. There exists a maximum element in this array such that elements to both sides decrease monotonically.

Find the index of this maximum element.

Input Format:
The first line contains an integer n ( 1 <=n <=10^5), denoting the size of the array.
The second line contains n space-separated integers (1 <=arr[i] <=10^9), denoting the elements of the array.
Output Format:
Print an integer representing the index of the maximum element that meets the criteria.
If no maximum element meeting the criteria is found, print 0.
Sample Input 1:
7
1 2 3 4 5 2 1
Sample Output 1:
4
Explanation:
The element 5 is the maximum element in this array.
To the left of 5, the elements are increasing (1, 2, 3, 4).
To the right of 5, the elements are decreasing (2, 1).
Thus, 5 is the peak element, and its index is 4 (considering 0-based indexing). Therefore, the correct output is 4.
Sample Input 2:
9
1 3 6 7 9 4 3 2 1
Sample Output 2:
4
Explanation:
The element 9 is the maximum element.
To the left of 9, the elements are increasing (1, 3, 6, 7).
To the right of 9, the elements are decreasing (4, 3, 2, 1)
Thus, 9 is the peak element, and its index is 4 (considering 0-based indexing). Therefore, the correct output is 4.
Constraints:
1 <=arr.size <=10^5
1 <=arr[i] <=10^9
Note:The function should return the result. The driver code will handle printing the output.
 */
public class MaxElementInAArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 2, 1};
        int result = findMaxElementIndex(arr);
        System.out.println(result);
    }

    public static int findMaxElementIndex(int[] arr) {
        int n = arr.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > arr[mid + 1]) {
                right = mid; // Move to the left side
            } else {
                left = mid + 1; // Move to the right side
            }
        }

        return left; // The peak element index
    }
}
