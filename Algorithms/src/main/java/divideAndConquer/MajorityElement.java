package divideAndConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Majority Element

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109


Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {
    // Solution 1: Boyer-Moore Voting Algorithm - O(n) time, O(1) space
    // This is the optimal solution for the follow-up question
    public int majorityElementOptimal(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        // Phase 1: Find potential candidate
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count = 1;
                }
            }
        }

        return candidate;
    }

    // Solution 2: HashMap approach - O(n) time, O(n) space
    public int majorityElementHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            // Early termination when majority is found
            if (countMap.get(num) > n / 2) {
                return num;
            }
        }

        // This should never be reached given the problem constraints
        return -1;
    }

    // Solution 3: Sorting approach - O(n log n) time, O(1) space
    public int majorityElementSorting(int[] nums) {
        Arrays.sort(nums);
        // The majority element will always be at the middle position
        return nums[nums.length / 2];
    }

    // Solution 4: Divide and Conquer - O(n log n) time, O(log n) space
    public int majorityElementDivideConquer(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private int majorityElementRec(int[] nums, int left, int right) {
        // Base case
        if (left == right) {
            return nums[left];
        }

        // Divide
        int mid = left + (right - left) / 2;
        int leftMajority = majorityElementRec(nums, left, mid);
        int rightMajority = majorityElementRec(nums, mid + 1, right);

        // Conquer
        if (leftMajority == rightMajority) {
            return leftMajority;
        }

        // Count occurrences of both candidates in current range
        int leftCount = countInRange(nums, leftMajority, left, right);
        int rightCount = countInRange(nums, rightMajority, left, right);

        return leftCount > rightCount ? leftMajority : rightMajority;
    }

    private int countInRange(int[] nums, int target, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) {
                count++;
            }
        }
        return count;
    }

    // Test method with examples
    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();

        // Test Case 1: [3,2,3]
        int[] nums1 = {3, 2, 3};
        System.out.println("Example 1: " + Arrays.toString(nums1));
        System.out.println("Boyer-Moore: " + solution.majorityElementOptimal(nums1));
        System.out.println("HashMap: " + solution.majorityElementHashMap(nums1));
        System.out.println("Sorting: " + solution.majorityElementSorting(nums1));
        System.out.println("Divide & Conquer: " + solution.majorityElementDivideConquer(nums1));
        System.out.println();

        // Test Case 2: [2,2,1,1,1,2,2]
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Example 2: " + Arrays.toString(nums2));
        System.out.println("Boyer-Moore: " + solution.majorityElementOptimal(nums2));
        System.out.println("HashMap: " + solution.majorityElementHashMap(nums2));
        System.out.println("Sorting: " + solution.majorityElementSorting(nums2));
        System.out.println("Divide & Conquer: " + solution.majorityElementDivideConquer(nums2));
        System.out.println();

        // Additional Test Case: Single element
        int[] nums3 = {1};
        System.out.println("Single element: " + Arrays.toString(nums3));
        System.out.println("Boyer-Moore: " + solution.majorityElementOptimal(nums3));

        // Performance comparison for large array
        System.out.println("\n=== Performance Analysis ===");
        System.out.println("1. Boyer-Moore Voting: O(n) time, O(1) space - OPTIMAL");
        System.out.println("2. HashMap: O(n) time, O(n) space");
        System.out.println("3. Sorting: O(n log n) time, O(1) space");
        System.out.println("4. Divide & Conquer: O(n log n) time, O(log n) space");
    }
}

/*
Algorithm Explanations:

1. Boyer-Moore Voting Algorithm (Optimal):
   - Maintains a candidate and count
   - If current element equals candidate, increment count
   - If different, decrement count
   - When count becomes 0, update candidate
   - The final candidate is guaranteed to be the majority element

2. HashMap Approach:
   - Count frequency of each element
   - Return element with count > n/2
   - Can terminate early when majority is found

3. Sorting Approach:
   - Sort the array
   - The majority element will always be at index n/2
   - Works because majority element appears more than n/2 times

4. Divide and Conquer:
   - Recursively find majority in left and right halves
   - If both halves have same majority, that's the answer
   - Otherwise, count occurrences of both candidates in current range

Time Complexities:
- Boyer-Moore: O(n) - Single pass
- HashMap: O(n) - Single pass with hash operations
- Sorting: O(n log n) - Due to sorting
- Divide & Conquer: O(n log n) - T(n) = 2T(n/2) + O(n)

Space Complexities:
- Boyer-Moore: O(1) - Only uses two variables
- HashMap: O(n) - Stores at most n/2 unique elements
- Sorting: O(1) - In-place sorting (ignoring recursion stack)
- Divide & Conquer: O(log n) - Recursion stack depth
*/