package stacks.dpbased;
/*
You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.

In one operation you can choose any subarray from initial and increment each value by one.

Return the minimum number of operations to form a target array from initial.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: target = [1,2,3,2,1]
Output: 3
Explanation: We need at least 3 operations to form the target array from the initial array.
[0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
[1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
[1,2,2,2,1] increment 1 at index 2.
[1,2,3,2,1] target array is formed.
Example 2:

Input: target = [3,1,1,2]
Output: 4
Explanation: [0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
Example 3:

Input: target = [3,1,5,4,2]
Output: 7
Explanation: [0,0,0,0,0] -> [1,1,1,1,1] -> [2,1,1,1,1] -> [3,1,1,1,1] -> [3,1,2,2,2] -> [3,1,3,3,2] -> [3,1,4,4,2] -> [3,1,5,4,2].


Constraints:

1 <= target.length <= 105
1 <= target[i] <= 105
 */
public class MinNoOfIncrementsOnSubArraysToFormTargetArray {
    /**
     * Calculates the minimum number of operations to form the target array from an initial
     * array of all zeros. In one operation, any subarray can be incremented by one.
     *
     * This method uses a greedy approach. It observes that each time a target element `target[i]`
     * is strictly greater than the previous element `target[i-1]`, we must initiate
     * `target[i] - target[i-1]` new increment operations starting at index `i`. These new
     * operations contribute to the total count. Decreases in height do not require new operations
     * as they are covered by operations that started earlier and spanned across these points.
     *
     * Time Complexity: O(N) - single pass through the array.
     * Space Complexity: O(1) - constant extra space.
     *
     * @param target The integer array to form.
     * @return The minimum number of operations.
     */
    public int minOperations(int[] target) {
        if (target == null || target.length == 0) {
            return 0;
        }

        // The first element `target[0]` always requires `target[0]` operations
        // assuming `initial[0]` starts at 0. These operations can extend to the right.
        int operations = target[0];

        // Iterate from the second element
        for (int i = 1; i < target.length; i++) {
            // If the current element is greater than the previous element,
            // the difference `target[i] - target[i-1]` represents new "levels" of increment
            // operations that must begin at or before index `i` to reach this height.
            // These are new operations that were not sufficient from the previous point.
            if (target[i] > target[i - 1]) {
                operations += (target[i] - target[i - 1]);
            }
            // If target[i] <= target[i-1], no new operation needs to start at 'i'
            // to reach target[i]. The height is already covered or needs to be
            // lower, which is naturally handled by operations that started earlier
            // and simply don't extend past this point at their full height.
        }

        return operations;
    }

    public static void main(String[] args) {
        MinNoOfIncrementsOnSubArraysToFormTargetArray solution = new MinNoOfIncrementsOnSubArraysToFormTargetArray();

        // Example 1
        int[] target1 = {1, 2, 3, 2, 1};
        System.out.println("Minimum operations for target1: " + solution.minOperations(target1)); // Output: 3

        // Example 2
        int[] target2 = {3, 1, 1, 2};
        System.out.println("Minimum operations for target2: " + solution.minOperations(target2)); // Output: 4

        // Example 3
        int[] target3 = {3, 1, 5, 4, 2};
        System.out.println("Minimum operations for target3: " + solution.minOperations(target3)); // Output: 7
    }
}
