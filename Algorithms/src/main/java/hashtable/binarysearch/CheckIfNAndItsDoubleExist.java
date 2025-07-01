package hashtable.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]


Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.


Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103
 */
public class CheckIfNAndItsDoubleExist {
    public static boolean checkIfExist(int[] arr) {
        // Step 1: Store all elements in a HashSet for quick lookup
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        // Step 2: Sort the array for binary search
        Arrays.sort(arr);

        // Step 3: For each element, check for double and half using binary search
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            // Check for double
            if (Arrays.binarySearch(arr, num * 2) >= 0) {
                // Make sure it's not the same index (in case of zero, there must be at least two zeros)
                if (num != 0 || (num == 0 && set.size() < arr.length)) {
                    if (num != 0 || countZeros(arr) > 1) {
                        return true;
                    }
                }
            }
            // Check for half (only if num is even)
            if (num % 2 == 0 && Arrays.binarySearch(arr, num / 2) >= 0) {
                if (num != 0 || countZeros(arr) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper to count zeros in the array
    private static int countZeros(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num == 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 2, 5, 3};
        System.out.println("Output: " + checkIfExist(arr1)); // true

        int[] arr2 = {3, 1, 7, 11};
        System.out.println("Output: " + checkIfExist(arr2)); // false

        int[] arr3 = {0, 0};
        System.out.println("Output: " + checkIfExist(arr3)); // true
    }
}
