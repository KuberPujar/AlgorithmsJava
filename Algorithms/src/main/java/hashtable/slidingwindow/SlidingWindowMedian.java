package hashtable.slidingwindow;

import java.util.*;

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
            if (nums == null || nums.length == 0 || k <= 0) {
                return new double[0];
            }

            double[] result = new double[nums.length - k + 1];
            // Use two TreeSets to maintain the lower and higher halves
            TreeSet<Integer> left = new TreeSet<>((a, b) ->
                    nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b);
            TreeSet<Integer> right = new TreeSet<>((a, b) ->
                    nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b);

            for (int i = 0; i < nums.length; i++) {
                // Add to the appropriate heap
                if (left.isEmpty() || nums[i] <= nums[left.last()]) {
                    left.add(i);
                } else {
                    right.add(i);
                }

                // Balance the heaps
                balanceHeaps(left, right);

                // If window is formed, calculate median
                if (i >= k - 1) {
                    result[i - k + 1] = getMedian(nums, left, right, k);

                    // Remove the element going out of window
                    int toRemove = i - k + 1;
                    if (!left.remove(toRemove)) {
                        right.remove(toRemove);
                    }

                    // Balance again after removal
                    balanceHeaps(left, right);
                }
            }

            return result;
        }

        private void balanceHeaps(TreeSet<Integer> left, TreeSet<Integer> right) {
            while (left.size() > right.size() + 1) {
                right.add(left.pollLast());
            }
            while (right.size() > left.size()) {
                left.add(right.pollFirst());
            }
        }

        private double getMedian(int[] nums, TreeSet<Integer> left, TreeSet<Integer> right, int k) {
            if (k % 2 == 1) {
                return (double) nums[left.last()];
            } else {
                return ((double) nums[left.last()] + nums[right.first()]) / 2.0;
            }
        }

        // Alternative solution using PriorityQueues (slower but simpler)
        public double[] medianSlidingWindowPQ(int[] nums, int k) {
            double[] result = new double[nums.length - k + 1];
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            Map<Integer, Integer> delayed = new HashMap<>();

            int balance = 0; // Tracks the balance between heaps

            for (int i = 0; i < nums.length; i++) {
                // Add to appropriate heap
                if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                    maxHeap.offer(i);
                    balance++;
                } else {
                    minHeap.offer(i);
                    balance--;
                }

                // Balance the heaps
                while (balance > 1) {
                    minHeap.offer(maxHeap.poll());
                    balance -= 2;
                }
                while (balance < 0) {
                    maxHeap.offer(minHeap.poll());
                    balance += 2;
                }

                // Remove out-of-window elements (lazy removal)
                while (!maxHeap.isEmpty() && maxHeap.peek() <= i - k) {
                    delayed.put(maxHeap.peek(), delayed.getOrDefault(maxHeap.peek(), 0) + 1);
                    maxHeap.poll();
                    balance--;
                }
                while (!minHeap.isEmpty() && minHeap.peek() <= i - k) {
                    delayed.put(minHeap.peek(), delayed.getOrDefault(minHeap.peek(), 0) + 1);
                    minHeap.poll();
                    balance++;
                }

                // Remove delayed elements from heap tops
                while (!maxHeap.isEmpty() && delayed.containsKey(maxHeap.peek())) {
                    delayed.put(maxHeap.peek(), delayed.get(maxHeap.peek()) - 1);
                    if (delayed.get(maxHeap.peek()) == 0) {
                        delayed.remove(maxHeap.peek());
                    }
                    maxHeap.poll();
                }
                while (!minHeap.isEmpty() && delayed.containsKey(minHeap.peek())) {
                    delayed.put(minHeap.peek(), delayed.get(minHeap.peek()) - 1);
                    if (delayed.get(minHeap.peek()) == 0) {
                        delayed.remove(minHeap.peek());
                    }
                    minHeap.poll();
                }

                // Calculate median if window is formed
                if (i >= k - 1) {
                    if (k % 2 == 1) {
                        result[i - k + 1] = nums[maxHeap.peek()];
                    } else {
                        result[i - k + 1] = ((double) nums[maxHeap.peek()] + nums[minHeap.peek()]) / 2.0;
                    }
                }
            }

            return result;
        }

        public static void main(String[] args) {
            SlidingWindowMedian solution = new SlidingWindowMedian();

            // Example 1
            int[] nums1 = {1,3,-1,-3,5,3,6,7};
            int k1 = 3;
            System.out.println(Arrays.toString(solution.medianSlidingWindow(nums1, k1)));
            // Output: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

            // Example 2
            int[] nums2 = {1,2,3,4,2,3,1,4,2};
            int k2 = 3;
            System.out.println(Arrays.toString(solution.medianSlidingWindow(nums2, k2)));
            // Output: [2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0]
        }
}
