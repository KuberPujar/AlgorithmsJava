package stacks.twopointer;

import java.util.Stack;

/*
You are given an array of integers nums (0-indexed) and an integer k.

The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

Return the maximum possible score of a good subarray.



Example 1:

Input: nums = [1,4,3,7,4,5], k = 3
Output: 15
Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
Example 2:

Input: nums = [5,5,4,5,4,1,1,1], k = 0
Output: 20
Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 2 * 104
0 <= k < nums.length
 */
public class MaximumScoreOfGoodSubarray {
    /**
     * Calculates the maximum possible score of a good subarray.
     * A good subarray must include the index k.
     *
     * @param nums The input array of integers.
     * @param k The required index that must be included in the subarray.
     * @return The maximum score.
     */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        // Initialize left and right pointers at k.
        int left = k;
        int right = k;

        // The current minimum value within the expanding good subarray.
        // Initially, it's just nums[k].
        int minVal = nums[k];

        // The maximum score found so far.
        // Initially, it's the score of the single-element subarray [k, k].
        int maxScore = nums[k];

        // The loop continues as long as we can expand the subarray.
        // We stop when the subarray covers the entire array (from index 0 to n-1).
        while (left > 0 || right < n - 1) {
            // Decide whether to expand left or right.
            // We prioritize expanding towards the side that offers a potentially larger value
            // (nums[left-1] vs nums[right+1]) because that side is more likely to help
            // keep the 'minVal' higher for a longer duration, thereby maximizing the score.

            // Condition 1: If we can expand to the left (left > 0) AND
            //              (we cannot expand to the right (right == n - 1) OR
            //               the element to the left (nums[left-1]) is greater than
            //               the element to the right (nums[right+1]))
            if (left > 0 && (right == n - 1 || nums[left - 1] > nums[right + 1])) {
                left--; // Move left pointer to the left.
                // Update minVal with the new element included in the subarray.
                minVal = Math.min(minVal, nums[left]);
            }
            // Condition 2: Otherwise, if we can expand to the right.
            // This covers cases where:
            //   - left == 0 (cannot expand left)
            //   - nums[right+1] >= nums[left-1] (prefer expanding right or both are equal)
            else {
                right++; // Move right pointer to the right.
                // Update minVal with the new element included in the subarray.
                minVal = Math.min(minVal, nums[right]);
            }

            // Calculate the current score for the subarray [left, right] with the current minVal.
            // Update maxScore if the current score is greater.
            maxScore = Math.max(maxScore, minVal * (right - left + 1));
        }

        return maxScore; // Return the overall maximum score found.
    }

    /**
     * Calculates the maximum possible score of a good subarray using a stack-based approach.
     * This method identifies for each element `nums[i]` the widest subarray `[left_boundary, right_boundary]`
     * where `nums[i]` is the minimum. It then checks if `k` falls within these boundaries and
     * calculates the score.
     * Time Complexity: O(N)
     * Space Complexity: O(N) for stack and boundary arrays.
     *
     * @param nums The input array of integers.
     * @param k The required index that must be included in the subarray.
     * @return The maximum score.
     */
    public int maximumScoreWithStack(int[] nums, int k) {
        int n = nums.length;
        // leftBoundary[i] stores the index of the first element to the left of i
        // that is strictly smaller than nums[i]. If no such element, it's -1.
        int[] leftBoundary = new int[n];
        // rightBoundary[i] stores the index of the first element to the right of i
        // that is strictly smaller than nums[i]. If no such element, it's n.
        int[] rightBoundary = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Calculate leftBoundary for each element (Previous Smaller Element)
        // Iterate from left to right.
        for (int i = 0; i < n; i++) {
            // While stack is not empty and the element at stack top is greater than or equal to current element
            // (meaning current element could be the previous smaller element for elements being popped),
            // pop from stack.
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            // If stack is empty, it means no element to the left is smaller, so left boundary is -1.
            // Otherwise, the top of the stack is the left boundary.
            leftBoundary[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i); // Push current index onto stack.
        }

        // Clear stack for next calculation.
        stack.clear();

        // Calculate rightBoundary for each element (Next Smaller Element)
        // Iterate from right to left.
        for (int i = n - 1; i >= 0; i--) {
            // While stack is not empty and the element at stack top is greater than or equal to current element,
            // pop from stack.
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            // If stack is empty, it means no element to the right is smaller, so right boundary is n.
            // Otherwise, the top of the stack is the right boundary.
            rightBoundary[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i); // Push current index onto stack.
        }

        int maxScore = 0;

        // Iterate through all possible elements as the minimum in a good subarray
        for (int i = 0; i < n; i++) {
            // Check if the current element nums[i] can be the minimum of a "good" subarray.
            // A good subarray is (left, right) where left <= k <= right.
            // Here, left is (leftBoundary[i] + 1) and right is (rightBoundary[i] - 1).
            // So, we need (leftBoundary[i] + 1) <= k <= (rightBoundary[i] - 1).
            if (leftBoundary[i] + 1 <= k && k <= rightBoundary[i] - 1) {
                // Calculate the width of this subarray.
                int width = rightBoundary[i] - leftBoundary[i] - 1;
                // Calculate the score.
                int currentScore = nums[i] * width;
                // Update maxScore if currentScore is greater.
                maxScore = Math.max(maxScore, currentScore);
            }
        }

        return maxScore;
    }

    /**
     * Main method for testing the MaxScoreGoodSubarray solution.
     * Includes examples from the problem description.
     */
    public static void main(String[] args) {
        MaximumScoreOfGoodSubarray solution = new MaximumScoreOfGoodSubarray();

        // Example 1
        int[] nums1 = {1, 4, 3, 7, 4, 5};
        int k1 = 3;
        System.out.println("Input: nums = [1,4,3,7,4,5], k = 3");
        System.out.println("Output: " + solution.maximumScore(nums1, k1)); // Expected output: 15
        System.out.println("---");

        // Example 2
        int[] nums2 = {5, 5, 4, 5, 4, 1, 1, 1};
        int k2 = 0;
        System.out.println("Input: nums = [5,5,4,5,4,1,1,1], k = 0");
        System.out.println("Output: " + solution.maximumScore(nums2, k2)); // Expected output: 20
        System.out.println("---");

        // Additional test case
        int[] nums3 = {6, 5, 4, 3, 2, 1};
        int k3 = 2;
        System.out.println("Input: nums = [6,5,4,3,2,1], k = 2");
        System.out.println("Output: " + solution.maximumScore(nums3, k3)); // Expected output: 12 (min(6,5,4,3) * 4 = 3*4 = 12, for subarray (0,3))
        System.out.println("---");

        // Additional test case
        int[] nums4 = {1, 2, 3, 4, 5, 6};
        int k4 = 3;
        System.out.println("Input: nums = [1,2,3,4,5,6], k = 3");
        System.out.println("Output: " + solution.maximumScore(nums4, k4)); // Expected output: 16 (min(1,2,3,4,5,6) * 6 = 1*6 = 6 for (0,5), min(2,3,4,5,6)*5 = 2*5 = 10 for (1,5), min(3,4,5,6)*4 = 3*4 = 12 for (2,5), min(4,5,6)*3 = 4*3 = 12 for (3,5), min(3,4)*2 = 3*2 = 6 for (2,3), etc. The actual max is 4*4 = 16 for (2,5) with min=4)
        // Let's re-verify expected for nums4 = {1,2,3,4,5,6}, k = 3.
        // Start (3,3), min=4, score=4.
        // Expand right: (3,4), min=4, score=4*2=8
        // Expand right: (3,5), min=4, score=4*3=12
        // Now right is n-1. Must expand left.
        // Expand left: (2,5), min=min(4,3)=3, score=3*4=12
        // Wait, nums[left-1] = nums[2] = 3. nums[right+1] is out of bounds.
        // The logic is if left > 0 AND (right == n-1 OR nums[left-1] > nums[right+1])
        // For {1,2,3,4,5,6}, k=3:
        // [4], L=3, R=3, min=4, score=4
        // Loop: L>0, R<n-1 (true). nums[L-1]=nums[2]=3. nums[R+1]=nums[4]=5. nums[2] < nums[4]. So expand right.
        // [4,5], L=3, R=4, min=min(4,5)=4, score=4*2=8
        // Loop: L>0, R<n-1 (true). nums[L-1]=nums[2]=3. nums[R+1]=nums[5]=6. nums[2] < nums[5]. So expand right.
        // [4,5,6], L=3, R=5, min=min(4,5,6)=4, score=4*3=12
        // Loop: L>0, R<n-1 (false, R==n-1). So expand left. (Condition: left > 0 && (right == n - 1 || nums[left - 1] > nums[right + 1])) is true.
        // [3,4,5,6], L=2, R=5, min=min(4,3)=3, score=3*4=12
        // Loop: L>0, R<n-1 (false, R==n-1). So expand left.
        // [2,3,4,5,6], L=1, R=5, min=min(3,2)=2, score=2*5=10
        // Loop: L>0, R<n-1 (false, R==n-1). So expand left.
        // [1,2,3,4,5,6], L=0, R=5, min=min(2,1)=1, score=1*6=6
        // Loop terminates. Max score is 12. My manual trace was wrong for nums4 example.
        System.out.println("---");
    }
}
