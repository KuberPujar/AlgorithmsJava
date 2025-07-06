package heaps.slidingwindow;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.



Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]


Constraints:

1 <= k <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
 */
public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new double[0];

        // Comparator to handle duplicates by comparing indices when values are equal
        Comparator<Integer> comparator = (a, b) ->
                nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;

        // Left tree (max heap functionality) stores smaller half
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        // Right tree (min heap functionality) stores larger half
        TreeSet<Integer> right = new TreeSet<>(comparator);

        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // Remove the element leaving the window (sliding window left boundary)
            if (i >= k) {
                int toRemove = i - k;
                if (!left.remove(toRemove)) {
                    right.remove(toRemove);
                }
            }

            // Add new element to right set first
            right.add(i);

            // Move smallest from right to left to maintain balance
            left.add(right.pollFirst());

            // Ensure left is not larger than right
            if (left.size() > right.size()) {
                right.add(left.pollFirst());
            }

            // Calculate median when window is full
            if (i >= k - 1) {
                if (k % 2 == 1) {
                    result[i - k + 1] = (double) nums[right.first()];
                } else {
                    result[i - k + 1] = ((double) nums[left.first()] + nums[right.first()]) / 2.0;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        double[] result = swm.medianSlidingWindow(nums, k);
        for (double median : result) {
            System.out.printf("%.5f ", median);
        }
        System.out.println();
    }
}
