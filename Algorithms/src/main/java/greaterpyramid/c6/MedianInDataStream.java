package greaterpyramid.c6;

import java.util.*;

/*
Median in a data stream
Given that n integers are read from a data stream. Your task is to find the median of the elements read so far. Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the floor of the average of the two middle values.

Example 1

Input

arr = [5,3,8]
Output

[5,4,5]
Explanation

After reading first element of stream, Median of [5] is 5 After reading second element of stream, Median of [5, 3] = floor((5+3)/2)=4 After reading third element of stream, Median of [5,3,8] = 5 ,it is the middle value of the sorted array So the output will be 5 4 5.

Example 2:

Input:

arr = [1,2,3]
Output:

[1,1,2]
Constraints:

1 <= n <= 10000
0 <= arr[i] <= 100000
 */
public class MedianInDataStream {
    // Max heap to store the smaller half of numbers
    private PriorityQueue<Integer> maxHeap;
    // Min heap to store the larger half of numbers
    private PriorityQueue<Integer> minHeap;

    public MedianInDataStream() {
        // Max heap for smaller half (largest element at top)
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Min heap for larger half (smallest element at top)
        minHeap = new PriorityQueue<>();
    }

    public void addNumber(int num) {
        // Add to max heap first
        maxHeap.offer(num);

        // Balance the heaps - move largest from maxHeap to minHeap
        minHeap.offer(maxHeap.poll());

        // If minHeap has more elements than maxHeap, rebalance
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public int findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            // Odd number of elements, median is top of maxHeap
            return maxHeap.peek();
        } else {
            // Even number of elements, median is floor of average of two middle elements
            return (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    public static List<Integer> getMedianStream(int[] arr) {
        MedianInDataStream mds = new MedianInDataStream();
        List<Integer> result = new ArrayList<>();

        for (int num : arr) {
            mds.addNumber(num);
            result.add(mds.findMedian());
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Example 1
        int[] arr1 = {5, 3, 8};
        List<Integer> result1 = getMedianStream(arr1);
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Output: " + result1);
        System.out.println("Expected: [5, 4, 5]");
        System.out.println();

        // Test Example 2
        int[] arr2 = {1, 2, 3};
        List<Integer> result2 = getMedianStream(arr2);
        System.out.println("Example 2:");
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Output: " + result2);
        System.out.println("Expected: [1, 1, 2]");
        System.out.println();

        // Additional test case
        int[] arr3 = {10, 20, 30, 40, 50};
        List<Integer> result3 = getMedianStream(arr3);
        System.out.println("Additional Test:");
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Output: " + result3);
        System.out.println();

        // Step by step explanation for Example 1
        System.out.println("Step by step for Example 1 [5, 3, 8]:");
        MedianInDataStream mds = new MedianInDataStream();

        mds.addNumber(5);
        System.out.println("After adding 5: Median = " + mds.findMedian() + " (sorted: [5])");

        mds.addNumber(3);
        System.out.println("After adding 3: Median = " + mds.findMedian() + " (sorted: [3, 5])");

        mds.addNumber(8);
        System.out.println("After adding 8: Median = " + mds.findMedian() + " (sorted: [3, 5, 8])");
    }
}

// Alternative implementation using a single method approach
class MedianFinder {
    public static List<Integer> findMedianInStream(List<Integer> stream) {

        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : stream) {
            // Add number to appropriate heap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }

            // Calculate median
            int median;
            if (maxHeap.size() > minHeap.size()) {
                median = maxHeap.peek();
            } else if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
            } else {
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            }

            result.add(median);
        }

        return result;
    }
}
