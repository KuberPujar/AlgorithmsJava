package arrays.twopointer;

import java.util.StringJoiner;

/*
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.



Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */
public class SortArrayByParity {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        int[] sortedArray = sortArrayByParity(nums);
        printArray(sortedArray);

    }

    private static int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] sortedArray = new int[n];
        int evenIndex = 0;
        int oddIndex = n - 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                sortedArray[evenIndex++] = nums[i];
            } else {
                sortedArray[oddIndex--] = nums[i];
            }
        }

        return sortedArray;
    }

    private static void printArray(int[] arr) {
        StringJoiner joiner = new StringJoiner(", ");
        for (int num : arr) {
            joiner.add(String.valueOf(num));
        }
        System.out.println("[" + joiner.toString() + "]");
    }
}
