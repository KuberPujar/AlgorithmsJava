package arrays.slidingwindow;
/*
A swap is defined as taking two distinct positions in an array and swapping the values in them.

A circular array is defined as an array where we consider the first element and the last element to be adjacent.

Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.



Example 1:

Input: nums = [0,1,0,1,1,0,0]
Output: 1
Explanation: Here are a few of the ways to group all the 1's together:
[0,0,1,1,1,0,0] using 1 swap.
[0,1,1,1,0,0,0] using 1 swap.
[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
There is no way to group all 1's together with 0 swaps.
Thus, the minimum number of swaps required is 1.
Example 2:

Input: nums = [0,1,1,1,0,0,1,1,0]
Output: 2
Explanation: Here are a few of the ways to group all the 1's together:
[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
[1,1,1,1,1,0,0,0,0] using 2 swaps.
There is no way to group all 1's together with 0 or 1 swaps.
Thus, the minimum number of swaps required is 2.
Example 3:

Input: nums = [1,1,0,0,1]
Output: 0
Explanation: All the 1's are already grouped together due to the circular property of the array.
Thus, the minimum number of swaps required is 0.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class MinimumSwapsToGrpAll1sTogetherII {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 1, 0, 0};
        System.out.println(minSwaps(nums));
    }

    private static int minSwaps(int[] nums) {
        int totalOnes = 0;
        for (int num : nums) {
            totalOnes += num;
        }

        // Handle edge case where all elements are 1
        if (totalOnes == 0 || totalOnes == nums.length) {
            return 0;
        }

        // Create a circular array by concatenating nums with itself
        int[] circular = new int[nums.length * 2];
        for (int i = 0; i < circular.length; i++) {
            circular[i] = nums[i % nums.length];
        }

        int minSwaps = Integer.MAX_VALUE;
        int windowOnes = 0;

        // Initialize first window
        for (int i = 0; i < totalOnes; i++) {
            windowOnes += circular[i];
        }
        minSwaps = totalOnes - windowOnes;

        // Slide the window through the circular array
        for (int i = totalOnes; i < circular.length; i++) {
            windowOnes += circular[i] - circular[i - totalOnes];
            minSwaps = Math.min(minSwaps, totalOnes - windowOnes);

            // Early exit if we find the best possible (0 swaps)
            if (minSwaps == 0) {
                return 0;
            }
        }

        return minSwaps;
    }
}
