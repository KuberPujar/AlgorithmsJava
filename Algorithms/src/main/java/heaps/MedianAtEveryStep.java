package heaps;

import java.util.*;

/*
Median at every step
Given an input stream of N integers. The task is to insert these numbers into a new stream and find the median of the stream formed by each insertion of X to the new stream.

Input:

The first line contains an integer N, representing the number of integers in the input stream.
The second line contains N space-separated integers representing the elements X[] of the stream.
Output:

Return the median at each insertion of the number as an integer rounded to the previous number.
Example 1

Input :
N = 4,
X[] = [5,15,1,3]

Output :
[5,10,5,4]

Explanation:
Flow in stream : 5, 15, 1, 3
5 goes to stream --> median 5 (5)
15 goes to stream --> median 10 (5,15)
1 goes to stream --> median 5 (5,15,1)
3 goes to stream --> median 4 (5,15,1 3)
Example 2

Input:
N = 3,
X[] = [5,10,15]

Output:
5 7 10

Explanation:
Flow in stream: 5, 10, 15
5 goes to stream --> median 5 (5)
10 goes to stream --> median 7.5 (5,10)
15 goes to stream --> median 10 (5,10,15)
Constraints

1 <= N <= 10^6
1 <= x <= 10^6
Note:The function should return the result.
 */
public class MedianAtEveryStep {
    /**
     * Finds median at every step of inserting elements into a stream
     * Uses two heaps approach for O(log n) insertion and O(1) median finding
     *
     * @param n Number of elements
     * @return int of medians at each step (rounded down)
     */
    public static List<Integer> findMedianAtEachStep(int n,int[] x) {
        List<Integer> result = new ArrayList<>();
        // Max heap for smaller half (stores smaller elements)
       PriorityQueue<Integer> maxHeap= new PriorityQueue<>(Collections.reverseOrder());
        // Min heap for larger half (stores larger elements)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = x[i];

            // Insert element into appropriate heap
            insertElement(maxHeap, minHeap, num);

            // Balance heaps to maintain size property
            balanceHeaps(maxHeap, minHeap);

            // Calculate and add median
            int median = getMedian(maxHeap, minHeap);
            result.add(median);
        }

        return result;
    }



    /**
     * Inserts element into the appropriate heap
     */
    private static void insertElement(PriorityQueue<Integer> maxHeap,
                                      PriorityQueue<Integer> minHeap, int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
    }

    /**
     * Balances the heaps to ensure size difference is at most 1
     */
    private static void balanceHeaps(PriorityQueue<Integer> maxHeap,
                                     PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Calculates median from the two heaps (rounded down)
     */
    private static int getMedian(PriorityQueue<Integer> maxHeap,
                                 PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            // Even number of elements - average and round down
            return (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    // Test method
    public static void main(String[] args) {
        // Test Example 1
        int[] x1 = {5, 15, 1, 3};
        List<Integer> result1 = findMedianAtEachStep(4, x1);
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(x1));
        System.out.println("Output: " + result1);
        System.out.println("Expected: [5, 10, 5, 4]");
        System.out.println();

        // Test Example 2
        int[] x2 = {5, 10, 15};
        List<Integer> result2 = findMedianAtEachStep(3, x2);
        System.out.println("Example 2:");
        System.out.println("Input: " + Arrays.toString(x2));
        System.out.println("Output: " + result2);
        System.out.println("Expected: [5, 7, 10]");
        System.out.println();

        // Additional test case
        int[] x3 = {1, 2, 3, 4, 5};
        List<Integer> result3 = findMedianAtEachStep(5, x3);
        System.out.println("Additional test:");
        System.out.println("Input: " + Arrays.toString(x3));
        System.out.println("Output: " + result3);
        System.out.println();

        // Edge case - single element
        int[] x4 = {42};
        List<Integer> result4 = findMedianAtEachStep(1, x4);
        System.out.println("Single element test:");
        System.out.println("Input: " + Arrays.toString(x4));
        System.out.println("Output: " + result4);
    }
}
