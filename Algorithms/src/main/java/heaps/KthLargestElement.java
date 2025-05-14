package heaps;

import java.util.PriorityQueue;

/*
Kth Largest Element
You have an array of strings arr where each string represents an integer without leading zeros. Given an integer k, find the string that represents the kth largest integer in the array.

Remember that duplicates are counted separately. For example, if arr is ["1", "3", "5"], the first largest integer is "5", the second largest is also "3", and the third largest is "1". Return the string for the kth largest integer.

Example 1:
Input:

arr = ["1", "2", "3"], k = 2
Output:

2
Explanation:

1st largest is "3", 2nd largest is "2". Return "2".
Example 2:
Input:

arr = ["12","2","21","1"], k = 3
Output:

2
Explanation:

The numbers in arr sorted in non-decreasing order are ["1","2","12","21"].
The 3rd largest integer in nums is "2".
Constraints:

1 <= k <= arr.length <= 104
1 <= arr[i].length <= 100
arr[i] consists of only digits.
arr[i] will not have any leading zeros.
The function should return the result.
 */
public class KthLargestElement {
    public static String findKthLargest(String[] arr, int k) {
        // Create a min heap to store the k largest elements.
        PriorityQueue<String> minHeap = new PriorityQueue<>(k, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length(); // Compare lengths
            }
            return a.compareTo(b); // If lengths are equal, compare lexicographically
        });

        // Iterate through the array and add elements to the heap.
        for (String num : arr) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the smallest element if the heap size exceeds k
            }
        }

        // The top of the heap now contains the kth largest element.
        return minHeap.peek();
    }

    public static void main(String[] args) {
        String[] arr1 = {"1", "2", "3"};
        int k1 = 2;
        System.out.println("Example 1: " + findKthLargest(arr1, k1)); // Output: 2

        String[] arr2 = {"12", "2", "21", "1"};
        int k2 = 3;
        System.out.println("Example 2: " + findKthLargest(arr2, k2)); // Output: 2
    }
}
