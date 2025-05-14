package heaps;

import java.util.*;

/*
You are given an input stream arr of n integers. For every element in the stream, you are required to print the Kth smallest element so far.
If the Kth smallest element does not exist at any point (i.e., fewer than k elements have been processed), print -1.

Use a heap to maintain the stream efficiently.

Input Format:

First line contains two space-separated(Driver code is already giving space) integers: n and k.
Second line contains n space-separated integers representing the stream arr.
Output Format:

Return n space-separated integers where the i-th integer represents the Kth smallest element from the first i elements of the stream, or -1 if it doesn't exist.
Sample Input:

6 4
1 2 3 4 5 6
Sample Output:

-1 -1 -1 4 4 4
Explanation:

First 3 elements (1,2,3): Not enough elements to determine the 4th smallest → -1
At 4th element: [1,2,3,4] → 4th smallest is 4
At 5th element: [1,2,3,4,5] → 4th smallest is 4
At 6th element: [1,2,3,4,5,6] → 4th smallest is 4
Constraints:

1 ≤ n, k ≤ 10⁵
1 ≤ arr[i] ≤ 10⁵
Note :The function should return the result.
 */
public class KthSmallestElement {
    /**
     * Finds the Kth smallest element for each element in an input stream.
     *
     * For every element in the stream, this method determines the Kth smallest
     * element encountered so far. If fewer than k elements have been processed,
     * -1 is recorded for that point in the stream.
     *
     * @param k   The Kth smallest element to find.
     * @param arr The input stream of integers.
     * @return A list of integers, where the i-th integer is the Kth smallest
     * element from the first i elements of the stream, or -1 if it
     * doesn't exist at that point.
     */
    public static List<Integer> findKthSmallest(int k, int[] arr) {
        // List to store the results for each step of the stream.
        List<Integer> results = new ArrayList<>();

        // Max-heap to store the k smallest elements encountered so far.
        // The largest of these k smallest elements will be at the top (peek).
        // If the heap size is k, peek() gives the kth smallest.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Process each number in the input stream.
        for (int num : arr) {
            // Add the current number to the max-heap.
            maxHeap.offer(num);

            // If the heap size exceeds k, remove the largest element (the root of the max-heap).
            // This ensures the heap maintains only the k smallest elements seen so far.
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }

            // After processing the current number and adjusting the heap:
            // If the heap's size is exactly k, it means we have processed at least k elements,
            // and the top of the max-heap is the kth smallest element.
            if (maxHeap.size() == k) {
                results.add(maxHeap.peek());
            } else {
                // If the heap's size is less than k, it means we haven't processed
                // enough elements yet to determine the kth smallest.
                results.add(-1);
            }
        }

        return results;
    }

    /**
     * Main method for testing purposes.
     * This part would typically be handled by the driver code in a competitive programming setup.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read n (number of elements in the stream) and k
        // System.out.println("Enter n and k (space-separated):"); // Example prompt
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[n];
        // System.out.println("Enter " + n + " space-separated integers for the stream:"); // Example prompt
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        List<Integer> resultList = findKthSmallest(k, arr);

        // Print the results space-separated
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print(resultList.get(i) + (i == resultList.size() - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }
}
